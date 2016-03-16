package com.bsu.slava.up;

import java.io.*;
import java.text.ParseException;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "logfile.txt";
        Interface menu = new Interface();
        File fileLog = new File(fileName);
        PrintStream logger = new PrintStream(fileLog);

        try {
            menu.show(logger);
        } catch (InputMismatchException e) {
            logger.append("You entered is not a number!");
            System.out.println("You entered is not a number!");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            logger.append("This file does not exist!");
            System.out.println("This file does not exist!");
            e.printStackTrace();
        } catch (ParseException e) {
            logger.append("Incorrectly entered date!");
            System.out.println("Incorrectly entered date!");
            e.printStackTrace();
        } catch (Exception e) {
            logger.append(e.getMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}