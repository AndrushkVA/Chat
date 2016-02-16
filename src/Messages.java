import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ֲÿקוסכאג on 14.02.2016.
 */

public class Messages {
    private List<Message> messages;

    public Messages() {
        messages = new ArrayList<Message>();
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public long createID() {
        long id;
        if (messages.size() > 0) {
            id = messages.get(messages.size() - 1).getId() + 1;
        } else{
            id = 1;
        }
        return id;
    }

    public void inputMessage(PrintStream ps) throws Exception {
        Scanner sc = new Scanner(System.in);
        Date date = new Date();

        long id;
        String author;
        String message;
        long millis;

        id = createID();
        System.out.println("Enter your name:");
        author = sc.nextLine();
        if (author.length() == 0){
            throw new Exception("You did not entered a name!");
        }
        System.out.println("Enter your message:");
        message = sc.nextLine();
        if (message.length() == 0){
            throw new Exception("You did not enter a message!");
        }
        millis = date.getTime();

        addMessage(new Message(id, author, message, String.valueOf(millis)));
        ps.append("Message added:\nId: " + String.valueOf(id) + "\nAuthor: " + author + "\nMessage: " + message + "\nDate: " + String.valueOf(millis));
    }

    public void deleteMessageByID(long id, PrintStream ps) throws Exception {
        for (Message ms : messages) {
            if (id == ms.getId()) {
                messages.remove(ms);
                ps.append("Message the given id was successfully removed");
                return;
            }
        }
        throw new Exception("Messages with this id does not exist!");
    }

    public void showHistoryMessages(List<Message> list, PrintStream ps) throws Exception {
        if (list.size() == 0){
            throw new Exception("Message history is empty!");
        }
        ps.append("Finded messages:\n");
        for (Message ms : list) {
            System.out.println(ms.toChronologicString());
            ps.append(ms.toChronologicString() + "\n");
        }
    }

    public List<Message> searchMessagesByAuthor(String author) throws Exception {
        List<Message> findedMessges = new ArrayList<Message>();
        int count = 0;
        for (Message item : messages) {
            if (author.toLowerCase().equals(item.getAuthor().toLowerCase())){
                findedMessges.add(item);
            }
            else{
                count++;
            }
        }
        if (count == messages.size()){
            throw new Exception("This author does not exist!");
        }
        return findedMessges;
    }

    public List<Message> searchMessagesByKeyword(String keyword) throws Exception {
        List<Message> findedMessges = new ArrayList<Message>();
        int count = 0;
        for (Message item : messages) {
            if (item.getMessage().toLowerCase().indexOf(keyword.toLowerCase()) >= 0){
                findedMessges.add(item);
            }
            else{
                count++;
            }
        }
        if (count == messages.size()){
            throw new Exception("This word does not exist!");
        }
        return findedMessges;
    }

    public List<Message> viewHistoryACertainPeriod(String dateFrom, String dateBefore) throws Exception {
        List<Message> findedMessges = new ArrayList<Message>();
        String stringDateFormat = "yyyy-MM-dd,HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(stringDateFormat, Locale.US);
        Date dateF = format.parse(dateFrom);
        Date dateB = format.parse(dateBefore);
        long milF = dateF.getTime();
        long milB = dateB.getTime();
        int count = 0;
        for (Message item : messages) {
            if (milF <= Long.parseLong(item.getTimestamp()) && Long.parseLong(item.getTimestamp()) <= milB) {
                findedMessges.add(item);
            }
            else count++;
        }
        if (count == messages.size()){
            throw new Exception("Posts in this period not found!");
        }
        return findedMessges;
    }

    public void readJSON(File file, PrintStream ps) throws IOException {
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader(file));
        if (file.length() > 4) {
            messages = gson.fromJson(br, new TypeToken<ArrayList<Message>>() {}.getType());
            ps.println("File has been successfully read");
            System.out.println(messages.toString());
        } else {
            System.out.println("JSON-file is empty");
            ps.append("JSON-file is empty");
        }
    }

    public void writeJSON(File file, PrintStream ps) throws FileNotFoundException {
        Gson gson = new Gson();
        PrintStream psf = new PrintStream(file);
        psf.append(gson.toJson(messages));
        ps.append("Information recorded\n");
    }
}