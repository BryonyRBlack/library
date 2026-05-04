package com.nology.library.Users;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserUtils {

    static Scanner scanner = new Scanner(System.in);

    public static void logIn() {
        User user = new User(User.inputId(), User.inputUserName(), User.inputPassword());
        String newName = user.getName();
        String newPassword = user.getPassword();
        int newId = user.getId();
        boolean logIn = false;
        String adminStatus = null;

        try (CSVReader reader = new CSVReader(new FileReader("userDatabase.csv"))) {
            String[] row;
            reader.readNext();

            while ((row = reader.readNext()) != null) {
                String existingUsername = row[1];
                String existingPassword = row[2];
                if (existingUsername.equalsIgnoreCase(newName) && existingPassword.equals(newPassword)) {
                    adminStatus = row[3];
                    logIn = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (logIn) {
            if (adminStatus.equalsIgnoreCase("true")) {
                Admin.userIsAdmin();
            } else {
                Customers.Customer();
            }
        }
    }

    public static void signUp() {
        User user = new User(User.inputId(), User.inputUserName(), User.inputPassword());
        System.out.println("Please enter 1 if admin, or 2 if a user");
        String newName = user.getName();
        String newPassword = user.getPassword();
        int newId = user.getId();
        int adminStatusQuery = scanner.nextInt();
        Boolean adminStatus = false;
        if (adminStatusQuery == 1) {
            adminStatus = true;
        }

        String[] row = {String.valueOf(newId), newName, newPassword, adminStatus.toString()};

        try (CSVWriter writer = new CSVWriter(new FileWriter("userDatabase.csv", true))) {
            writer.writeNext(row);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String loanedOut() {
        String booksCsv = "booksLoandOut.csv";
        try(CSVReader reader = new CSVReader(new FileReader(booksCsv))) {
            String[] header = reader.readNext();
            if (header != null) {
                System.out.println("\nHeader: " + String.join(", ", header));
            }

            String[] nextLine;
            int rowNumber = 1;

            System.out.println("\nData read from CSV file:");
            while ((nextLine = reader.readNext()) != null) {
                System.out.println("Row " + rowNumber + ":");
                for (int i = 0; i < nextLine.length; i++) {
                    System.out.println(" " + header[i] + ": " + nextLine[i]);
                }
                rowNumber++;
                System.out.println();
            }
        } catch (IOException | CsvValidationException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
            e.printStackTrace();
        }
        return booksCsv;
    }
}
