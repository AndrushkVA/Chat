import java.io.*;
import java.text.ParseException;
import java.util.InputMismatchException;

/**
 * Created by Вячеслав on 14.02.2016.
 */

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "logfile.txt";
        Work work = new Work();
        File fileLog = new File(fileName);
        PrintStream ps = new PrintStream(fileLog);

        try {
            work.main(ps);
        } catch (InputMismatchException e) {
            ps.append("You entered is not a number!");
            System.out.println("You entered is not a number!");
            e.printStackTrace();
        } catch (FileNotFoundException e){
            ps.append("This file does not exist!");
            System.out.println("This file does not exist!");
            e.printStackTrace();
        } catch (ParseException e){
            ps.append("Incorrectly entered date!");
            System.out.println("Incorrectly entered date!");
            e.printStackTrace();
        } catch (Exception e) {
            ps.append(e.getMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
//УТОЧНИТЬ ПРО ПОИСК СЛОВА И ВЫРАЖЕНИЯ