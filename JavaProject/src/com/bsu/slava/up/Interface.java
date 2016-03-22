package com.bsu.slava.up;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static com.bsu.slava.up.MessageUtils.DELIMETER;
import static com.bsu.slava.up.MessageUtils.OPERATIONS_MESSAGE;

public class Interface {


    public void show(PrintStream logger) throws Exception {
        Messages messages = new Messages();
        Scanner sc = new Scanner(System.in);
        logger.println(OPERATIONS_MESSAGE);

        while (true) {

            System.out.println(OPERATIONS_MESSAGE);
            int answer = Integer.parseInt(sc.nextLine());
            if (answer < 1 || answer > 10) {
                throw new Exception("Such variants of the answer does not exist!");
            }
            switch (answer) {
                case 1:
                    loadFile(logger, messages, sc);
                    break;
                case 2:
                    saveFile(logger, messages, sc);
                    break;
                case 3:
                    addMessage(logger, messages);
                    break;
                case 4:
                    deleteMessage(logger, messages, sc);
                    break;
                case 5:
                    showHistory(logger, messages);
                    break;
                case 6:
                    searchMessagesByAuthor(logger, messages, sc);
                    break;
                case 7:
                    searchMessagesByKeyword(logger, messages, sc);
                    break;
                case 8:
                    searchMessagesByRegex(logger, messages, sc);
                    break;
                case 9:
                    searchMessagesForCertainPeriod(logger, messages, sc);
                    break;
                case 10:
                    logger.append("This user has decided to quit the application");
                    System.out.println("Do not forget to save your message history!\n" +
                            "You sure you want to exit the program?(y/n)");
                    String ans = sc.nextLine();
                    switch (ans) {
                        case "y":
                            System.out.println("\nBye-bye!");
                            return;
                    }
                    break;
            }
        }
    }

    private void searchMessagesForCertainPeriod(PrintStream logger, Messages messages, Scanner sc) throws Exception {
        List<Message> resultSearch;
        System.out.print("Enter the period of \"yyyy:MM:dd HH:mm:ss\" \nFrom:");
        String dateFrom = sc.nextLine();
        System.out.print("Before:");
        String dateBefore = sc.nextLine();
        System.out.println();
        logger.append("The user wants to find a message from ").append(dateFrom).append(" before ").append(dateBefore).append("\n");
        resultSearch = messages.searchMessagesForCertainPeriod(dateFrom, dateBefore);
        messages.showHistoryMessages(resultSearch, logger);
        logger.append(DELIMETER);
    }

    private void searchMessagesByRegex(PrintStream logger, Messages messages, Scanner sc) throws Exception {
        List<Message> resultSearch;
        System.out.println("Enter a regular expression:");
        String regex = sc.nextLine();
        logger.append("The user wants to find a message by a regular expression \"").append(regex).append("\"\n");
        resultSearch = messages.searchMessagesByRegex(regex);
        messages.showHistoryMessages(resultSearch, logger);
        logger.append(DELIMETER);
    }

    private void searchMessagesByKeyword(PrintStream logger, Messages messages, Scanner sc) throws Exception {
        List<Message> resultSearch;
        System.out.println("Enter a keyword:");
        String keyword = sc.nextLine();
        logger.append("The user wants to find a message containing the word \"").append(keyword).append("\"\n");
        resultSearch = messages.searchMessagesByKeyword(keyword);
        messages.showHistoryMessages(resultSearch, logger);
        logger.append(DELIMETER);
    }

    private void searchMessagesByAuthor(PrintStream logger, Messages messages, Scanner sc) throws Exception {
        List<Message> resultSearch;
        System.out.println("Enter the name of the author:");
        String author = sc.nextLine();
        logger.append("The user wants to find all the user ").append(author).append(" messages\n");
        resultSearch = messages.searchMessagesByAuthor(author);
        messages.showHistoryMessages(resultSearch, logger);
        logger.append(DELIMETER);
    }

    private void showHistory(PrintStream logger, Messages messages) throws Exception {
        logger.append("A user wants to know the entire history of messages\n");
        messages.showHistoryMessages(messages.getMessages(), logger);
        logger.append(DELIMETER);
    }

    private void deleteMessage(PrintStream logger, Messages messages, Scanner sc) throws Exception {
        System.out.println("Enter the message id");
        String delId = sc.nextLine();
        logger.append("A user wants to delete the message with ID number:").append(String.valueOf(delId)).append("\n");
        messages.deleteMessageByID(delId, logger);
        logger.append(DELIMETER);
    }

    private void addMessage(PrintStream logger, Messages messages) throws Exception {
        logger.append("The user wants to add a new message\n");
        messages.inputMessage(logger);
        logger.append(DELIMETER);
    }

    private void saveFile(PrintStream logger, Messages messages, Scanner sc) throws FileNotFoundException {
        System.out.println("Enter the file name without the extension:");
        String fileOutputName = sc.nextLine();
        logger.append("The user wants to save the information to the ").append(fileOutputName).append(".json file\n");
        File outputFile = new File(fileOutputName + ".json");
        messages.writeJSON(outputFile, logger);
        logger.append(DELIMETER);
    }

    private void loadFile(PrintStream logger, Messages messages, Scanner sc) throws IOException {
        System.out.println("Enter the file name without the extension:");
        String fileInputName = sc.nextLine();
        logger.append("The user wants to download the information from the ").append(fileInputName).append(".json file\n");
        File inputFile = new File(fileInputName + ".json");
        messages.readJSON(inputFile, logger);
        logger.append(DELIMETER);
    }
}