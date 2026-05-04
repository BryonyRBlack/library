package com.nology.library.Users;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.apache.commons.lang3.BooleanUtils.isTrue;

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
                userIsAdmin();
            } else {
                Customer();
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
        System.out.println("Would you like to log in?");
        scanner.nextLine();
        String answer = scanner.nextLine();
        String state = "yes";
        if ((answer.equals(state))){
            logIn();
        }else{
            System.out.println("Goodbye!");
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

    public static String confirmLoan() {
        System.out.println("How many books are loaned out");
        return UserUtils.loanedOut();
    }


    public static int getLoanCount(String bookTitle) {

        try (CSVReader reader = new CSVReader(new FileReader("books.csv"))) {
            String[] row;
            boolean isHeader = true;

            while ((row = reader.readNext()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                if (row.length > 5 && row[1].equalsIgnoreCase(bookTitle.trim())) {
                    try {
                        return Integer.parseInt(row[5]);
                    } catch (Exception e) {
                        return 0;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void howManyTimesLoaned() {
        System.out.println("What book are you interested in?");
        scanner.nextLine();
        String bookName = scanner.nextLine();
        int loanCount= getLoanCount(bookName);
        System.out.println("This book has been loaned out " + loanCount + " times");
    }

    public static void userIsAdmin() {
        System.out.println("Please answer 1 for all currently loaned out books, or 2 to get the history of a particular book");
        int adminAnswer = scanner.nextInt();
        if (adminAnswer == 1) {
            confirmLoan();
        } else if (adminAnswer == 2) {
            howManyTimesLoaned();
        }
    }

    public static void Customer() {
        System.out.println("Please answer 1 to know what books you currently have loaned out, or 2 to check the availabitily of a book");
        int customerAnswer = scanner.nextInt();
        if (customerAnswer == 1){
            youHaveBorrowed();
        } else if (customerAnswer == 2) {
            availability();
        }
    }

    public static String youHaveBorrowed(){
        System.out.println("What do you have borrowed");
        return scanner.nextLine();
    }

    public static void incrementBookCount(String bookTitle) {
        List<String[]> updatedData = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("books.csv"))) {
            String[] row;
            boolean isHeader = true;

            while ((row = reader.readNext()) != null) {

                if (isHeader) {
                    updatedData.add(row);
                    isHeader = false;
                    continue;
                }

                if (row.length > 6 && row[1].equalsIgnoreCase(bookTitle.trim())) {

                    int count = 0;

                    try {
                        count = Integer.parseInt(row[6]);
                    } catch (Exception e) {
                        count = 0;
                    }
                    count++;
                    row[6] = String.valueOf(count);
                }
                updatedData.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }try (CSVWriter writer = new CSVWriter(new FileWriter("books.csv"))) {
            writer.writeAll(updatedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void availability(){
        System.out.println("What book are you interested in?");
        scanner.nextLine();
        String bookName = scanner.nextLine();
        boolean found = false;

        try (CSVReader reader = new CSVReader(new FileReader("booksLoandOut.csv"))){
            String [] row;

            while ((row = reader.readNext()) != null){
                for (String cell : row) {
                    if (cell.equals(bookName)) {
                        found = true;
                        break;
                    }
                } if (found) break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (found) {
            System.out.println("Sorry, this book is not available");
        }else{
            System.out.println("This book is available");
            System.out.println("Do you want to borrow this book?");
            String borrowing = scanner.nextLine();

            if (borrowing.equalsIgnoreCase("yes")) {
                boolean bookIsAvaialbe = false;

                try (
                        CSVReader reader2 = new CSVReader(new FileReader("books.csv"));
                        CSVWriter writer = new CSVWriter(new FileWriter("booksLoandOut.csv", true))
                )
                {
                    String [] row;

                    while ((row = reader2.readNext()) != null) {
                        if (row.length > 0 && row[1].equalsIgnoreCase(bookName.trim())) {
                            writer.writeNext(row);
                            bookIsAvaialbe = true;

                            System.out.println("You have borrowed this book");
                            break;

                        }
                    } if (!bookIsAvaialbe) {
                    System.out.println("We do not have this book");
                }
                } catch (Exception e) {
                    e.printStackTrace();
                }if (bookIsAvaialbe) {
                    incrementBookCount(bookName);
                }
            }
        }
    }

}
