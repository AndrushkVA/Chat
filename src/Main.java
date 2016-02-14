import sun.java2d.cmm.Profile;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by Вячеслав on 14.02.2016.
 */
public class Main {
    public static ArrayList<Message> messages = new ArrayList<Message>();

    public static void main(String[] args) {
        messages = (ArrayList<Message>) deserData("messages");
        System.out.println(messages.size());
        Message message = new Message();
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        message.setMessage(line);
        messages.add(message);
        for(Message p : messages){
            System.out.println(p.getMessage());
        }
        System.out.println(messages.size());
        serData("messages", messages);
    }

    private static Object deserData(String file_name) {
        Object retObject = 0;
        try {
            FileInputStream fileIn = new FileInputStream(file_name + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            retObject = in.readObject();
            fileIn.close();
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода!");
            System.exit(2);
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден");
            System.exit(3);
        }
        return retObject;
    }

    private static void serData(String file_name, Object obj) {
        try {
            FileOutputStream fileOut = new FileOutputStream(file_name + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            fileOut.close();
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода!");
            System.exit(2);
        }
    }
}
