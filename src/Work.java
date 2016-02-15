import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ¬ˇ˜ÂÒÎ‡‚ on 14.02.2016.
 */

public class Work {
    public void main(PrintStream ps) throws Exception {
        List<Message> resultSearch;
        Messages messages = new Messages();
        Scanner sc = new Scanner(System.in);
        //File fileLog = new File("logfile.txt");
        //PrintStream ps = new PrintStream(fileLog);
        boolean b = true;
        ps.println("Select the desired operation:\n" +
                "1)Upload a file;\n2)Save to File;\n3)Add new message;\n4)Delete a message by id number;\n" +
                "5)Show all messages history;\n6)Search author's posts;\n7)Search by keyword;\n" +
                "8)View message history for a certain period\n9)Quit the program;" +
                "\n========================================");
        while(b) {
            System.out.println("========================================\nSelect the desired operation:\n" +
                    "1)Upload a file;\n2)Save to File;\n3)Add new message;\n4)Delete a message by id number;\n" +
                    "5)Show all messages history;\n6)Search author's posts;\n7)Search by keyword;\n" +
                    "8)View message history for a certain period\n9)Quit the program;");
            int answer = sc.nextInt();
            if (answer < 1 || answer >9){
                throw new Exception("Such variants of the answer does not exist!");
            }
            long delId;
            switch (answer) {
                case 1:
                    System.out.println("Enter the file name without the extension:");
                    String fileInputName = sc.next();
                    ps.append("The user wants to download the information from the " + fileInputName + ".json file\n");
                    File inputFile = new File(fileInputName + ".json");
                    messages.readJSON(inputFile, ps);
                    ps.append("========================================\n");
                    break;
                case 2:
                    System.out.println("Enter the file name without the extension:");
                    String fileOutputName = sc.next();
                    ps.append("The user wants to save the information to the " + fileOutputName + ".json file\n");
                    File outputFile = new File(fileOutputName + ".json");
                    messages.writeJSON(outputFile, ps);
                    ps.append("========================================\n");
                    break;
                case 3: //œŒ◊≈Ã” ”ƒ¿Àﬂ≈“ “ŒÀ‹ Œ — “–≈“‹≈√Œ ›À≈Ã≈Õ“¿?
                    ps.append("The user wants to add a new message\n");
                    messages.inputMessage(ps);
                    ps.append("\n========================================\n");
                    break;
                case 4:
                    System.out.println("Enter the message id");
                    delId = sc.nextLong();
                    ps.append("A user wants to delete the message with ID number:" + String.valueOf(delId) + "\n");
                    messages.deleteMessageByID(delId, ps);
                    ps.append("\n========================================\n");
                    break;
                case 5:
                    ps.append("A user wants to know the entire history of messages");
                    messages.showHistoryMessages(messages.getMessages(), ps);
                    ps.append("\n========================================\n");
                    break;
                case 6:
                    resultSearch = new ArrayList<Message>();
                    System.out.println("Enter the name of the author:");
                    String author = sc.next();
                    ps.append("The user wants to find all the user " + author + " messages");
                    resultSearch = messages.searchMessagesByAuthor(author);
                    messages.showHistoryMessages(resultSearch, ps);
                    ps.append("\n========================================\n");
                    break;
                case 7:
                    resultSearch = new ArrayList<Message>();
                    System.out.println("Enter a keyword:");
                    String keyword = sc.next();
                    ps.append("The user wants to find a message containing the word \"" + keyword + "\"");
                    resultSearch = messages.searchMessagesByKeyword(keyword);
                    messages.showHistoryMessages(resultSearch, ps);
                    ps.append("\n========================================\n");
                    break;
                case 8:
                    resultSearch = new ArrayList<Message>();
                    System.out.print("Enter the period of \"yyyy:MM:dd HH:mm:ss\" \nFrom:");
                    String dateFrom = sc.next();
                    System.out.print("Before:");
                    String dateBefore = sc.next();
                    System.out.println();
                    ps.append("The user wants to find a message from " + dateFrom + " before " + dateBefore);
                    resultSearch = messages.viewHistoryACertainPeriod(dateFrom, dateBefore);
                    messages.showHistoryMessages(resultSearch, ps);
                    ps.append("\n========================================\n");
                    break;
                case 9:
                    ps.append("This user has decided to quit the application");
                    System.out.println("Do not forget to save your message history!\n" +
                            "You sure you want to exit the program?(y/n)");
                    String ans = sc.next();
                    switch (ans){
                        case "y":
                            System.out.println("\nBye-bye!");
                            b = false;
                            break;
                        case "n":
                            b = true;
                            break;
                    }
                    break;
            }
        }

    }

}
//messages.writeJSON(file);
//new Date(System.currentTimeMillis()).toString()