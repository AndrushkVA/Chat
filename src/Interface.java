import java.io.File;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ֲÿקוסכאג on 14.02.2016.
 */

public class Interface {
    public void main(PrintStream ps) throws Exception {
        String delimiter = "\n========================================\n";
        List<Message> resultSearch;
        Messages messages = new Messages();
        boolean b = true;
        Scanner sc = new Scanner(System.in);
        ps.println("Select the desired operation:\n" +
                "1)Upload a file;\n2)Save to File;\n3)Add new message;\n4)Delete a message by id number;\n" +
                "5)Show all messages history;\n6)Search author's posts;\n7)Search by keyword;\n" +
                "8)Search for a regular expression;\n9)View message history for a certain period\n" +
                "10)Quit the program;" + delimiter);

        while (b) {

            System.out.println(delimiter + "Select the desired operation:\n" +
                    "1)Upload a file;\n2)Save to File;\n3)Add new message;\n4)Delete a message by id number;\n" +
                    "5)Show all messages history;\n6)Search author's posts;\n7)Search by keyword;\n" +
                    "8)Search for a regular expression;\n9)View message history for a certain period\n" +
                    "10)Quit the program;" + delimiter);
            int answer = Integer.parseInt(sc.nextLine());
            if (answer < 1 || answer > 10) {
                throw new Exception("Such variants of the answer does not exist!");
            }
            String delId;
            switch (answer) {
                case 1:
                    System.out.println("Enter the file name without the extension:");
                    String fileInputName = sc.nextLine();
                    ps.append("The user wants to download the information from the ").append(fileInputName).append(".json file\n");
                    File inputFile = new File(fileInputName + ".json");
                    messages.readJSON(inputFile, ps);
                    ps.append(delimiter);
                    break;
                case 2:
                    System.out.println("Enter the file name without the extension:");
                    String fileOutputName = sc.nextLine();
                    ps.append("The user wants to save the information to the ").append(fileOutputName).append(".json file\n");
                    File outputFile = new File(fileOutputName + ".json");
                    messages.writeJSON(outputFile, ps);
                    ps.append(delimiter);
                    break;
                case 3:
                    ps.append("The user wants to add a new message\n");
                    messages.inputMessage(ps);
                    ps.append(delimiter);
                    break;
                case 4:
                    System.out.println("Enter the message id");
                    delId = sc.nextLine();
                    ps.append("A user wants to delete the message with ID number:").append(String.valueOf(delId)).append("\n");
                    messages.deleteMessageByID(delId, ps);
                    ps.append(delimiter);
                    break;
                case 5:
                    ps.append("A user wants to know the entire history of messages\n");
                    messages.showHistoryMessages(messages.getMessages(), ps);
                    ps.append(delimiter);
                    break;
                case 6:
                    System.out.println("Enter the name of the author:");
                    String author = sc.nextLine();
                    ps.append("The user wants to find all the user ").append(author).append(" messages\n");
                    resultSearch = messages.searchMessagesByAuthor(author);
                    messages.showHistoryMessages(resultSearch, ps);
                    ps.append(delimiter);
                    break;
                case 7:
                    System.out.println("Enter a keyword:");
                    String keyword = sc.nextLine();
                    ps.append("The user wants to find a message containing the word \"").append(keyword).append("\"\n");
                    resultSearch = messages.searchMessagesByKeyword(keyword);
                    messages.showHistoryMessages(resultSearch, ps);
                    ps.append(delimiter);
                    break;
                case 8:
                    System.out.println("Enter a regular expression:");
                    String regex = sc.nextLine();
                    ps.append("The user wants to find a message by a regular expression \"").append(regex).append("\"\n");
                    resultSearch = messages.searchMessagesByRegex(regex);
                    messages.showHistoryMessages(resultSearch, ps);
                    ps.append(delimiter);
                    break;
                case 9:
                    System.out.print("Enter the period of \"yyyy:MM:dd HH:mm:ss\" \nFrom:");
                    String dateFrom = sc.nextLine();
                    System.out.print("Before:");
                    String dateBefore = sc.nextLine();
                    System.out.println();
                    ps.append("The user wants to find a message from ").append(dateFrom).append(" before ").append(dateBefore).append("\n");
                    resultSearch = messages.searchMessagesACertainPeriod(dateFrom, dateBefore);
                    messages.showHistoryMessages(resultSearch, ps);
                    ps.append(delimiter);
                    break;
                case 10:
                    ps.append("This user has decided to quit the application");
                    System.out.println("Do not forget to save your message history!\n" +
                            "You sure you want to exit the program?(y/n)");
                    String ans = sc.nextLine();
                    switch (ans) {
                        case "y":
                            System.out.println("\nBye-bye!");
                            b = false;
                            break;
                    }
                    break;
            }
        }
    }
}
//messages.writeJSON(file);
//new Date(System.currentTimeMillis()).toString()