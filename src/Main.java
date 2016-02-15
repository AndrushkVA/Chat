import java.io.*;
import java.text.ParseException;
import java.util.InputMismatchException;

/**
 * Created by Вячеслав on 14.02.2016.
 */

public class Main {
    public static void main(String[] args) throws IOException {
        Work work = new Work();
        try {
            File fileLog = new File("logfile.txt");
            PrintStream ps = new PrintStream(fileLog);
            work.main(ps);
        } catch (InputMismatchException e) {
            System.out.println("You entered is not a number!");
        } catch (FileNotFoundException e){
            System.out.println("This file does not exist!");
        } catch (ParseException e){
            System.out.println("Incorrectly entered date!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

//ПОЧЕМУ УДАЛЯЕТ ТОЛЬКО С ТРЕТЬЕГО ЭЛЕМЕНТА?
//КАК СДЕЛАТЬ ЗАПИСЬ ИСКЛЮЧЕНИЙ В ФАЙЛ?
//УТОЧНИТЬ ПРО ПОИСК СЛОВА И ВЫРАЖЕНИЯ
