import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by ֲÿקוסכאג on 14.02.2016.
 */
public class Work {
    public void inputMessage(File fin, Messages messages) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        Date date = new Date();
        String author;
        String message;
        System.out.println("Enter your name:");
        author = sc.nextLine();
        System.out.println("Enter your message:");
        message = sc.nextLine();
        long millis = date.getTime();
        messages.addMessage(new Message(author, message, String.valueOf(millis)));
    }
}

//new Date(System.currentTimeMillis()).toString()