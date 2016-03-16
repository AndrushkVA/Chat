package com.bsu.slava.up;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.bsu.slava.up.MessageUtils.*;

public class Messages {
    private static final DateFormat TIMESTAMP = new SimpleDateFormat(DATE_FORMAT);
    private List<Message> messages;

    public Messages() {
        messages = new ArrayList<>();
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public String date(String timestamp) {
        return TIMESTAMP.format(Long.parseLong(timestamp));
    }

    public void inputMessage(PrintStream logger) throws Exception {
        Scanner sc = new Scanner(System.in);
        Date date = new Date();

        String id = String.valueOf(UUID.randomUUID());
        System.out.println("Enter your name:");
        String author = sc.nextLine();
        if (author.length() == 0) {
            throw new Exception("You did not entered a name!");
        }
        System.out.println("Enter your message:");
        String message = sc.nextLine();
        if (message.length() == 0) {
            throw new Exception("You did not enter a message!");
        }
        long millis = date.getTime();

        addMessage(new Message(id, author, message, String.valueOf(millis)));
        logger.append("Message added:\nId: ").append(String.valueOf(id)).append("\nAuthor: ").append(author).append("\nMessage: ").append(message).append("\nDate: ").append(String.valueOf(millis));
    }

    public void deleteMessageByID(String id, PrintStream logger) throws Exception {
        for (Message ms : messages) {
            if (id.equals(ms.getId())) {
                messages.remove(ms);
                logger.append("Message the given id was successfully removed");
                return;
            }
        }
        throw new Exception("Messages with this id does not exist!");
    }

    public void showHistoryMessages(List<Message> list, PrintStream logger) throws Exception {
        if (list.size() == 0) {
            throw new Exception("Messages not found!");
        } else {
            logger.append("Found messages:\n");
            for (Message ms : list) {
                System.out.println(ms.toShow());
                logger.append(ms.toShow()).append("\n");
            }
        }
    }

    public List<Message> searchMessagesByAuthor(String author) throws Exception {
        List<Message> findedMessges = new ArrayList<>();
        for (Message item : messages) {
            if (author.toLowerCase().equals(item.getAuthor().toLowerCase())) {
                findedMessges.add(item);
            }
        }
        if (findedMessges.isEmpty()) {
            throw new Exception("This author does not exist!");
        }
        return findedMessges;
    }

    public List<Message> searchMessagesByKeyword(String keyword) throws Exception {
        List<Message> findedMessges = new ArrayList<>();
        for (Message item : messages) {
            String message = item.getMessage().toLowerCase();
            if (message.contains(keyword.toLowerCase())) {
                findedMessges.add(item);
            }
        }
        if (findedMessges.isEmpty()) {
            throw new Exception("This word does not exist!");
        }
        return findedMessges;
    }

    public List<Message> searchMessagesByRegex(String regex) throws Exception {
        List<Message> findedMessges = new ArrayList<>();
        Pattern p = Pattern.compile(regex);
        Matcher m;
        for (Message item : messages) {
            m = p.matcher(item.getMessage());
            if (m.matches()) {
                findedMessges.add(item);
            }
        }
        if (findedMessges.isEmpty()) {
            throw new Exception("This regex does not exist!");
        }
        return findedMessges;
    }

    public List<Message> searchMessagesForCertainPeriod(String dateFrom, String dateBefore) throws Exception {
        List<Message> findedMessges = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        Date dateF = format.parse(dateFrom);
        Date dateB = format.parse(dateBefore);
        long timeFrom = dateF.getTime();
        long timeBefore = dateB.getTime();
        for (Message item : messages) {
            if (timeFrom <= Long.parseLong(item.getTimestamp()) && Long.parseLong(item.getTimestamp()) <= timeBefore) {
                findedMessges.add(item);
            }
        }
        if (findedMessges.isEmpty()) {
            throw new Exception("Posts in this period not found!");
        }
        return findedMessges;
    }

    public void readJSON(File file, PrintStream logger) throws IOException {
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader(file));
        if (file.length() > 4) {
            messages = gson.fromJson(br, new TypeToken<ArrayList<Message>>() {
            }.getType());
            logger.println("File has been successfully read");
            System.out.println(messages.toString());
        } else {
            System.out.println("JSON-file is empty");
            logger.append("JSON-file is empty");
        }
    }

    public void writeJSON(File file, PrintStream logger) throws FileNotFoundException {
        Gson gson = new Gson();
        PrintStream psf = new PrintStream(file);
        psf.append(gson.toJson(messages));
        logger.append("Information was recorded\n");
    }
}