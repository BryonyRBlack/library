package com.nology.library.Users;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;

public class Customers extends User {
    private String haveLoanedOut;
    private Boolean isAvailable;

    public Customers(int id, String name, String password, String haveLoanedOut, Boolean isAvailable){
        super(id, name, password);
        this.haveLoanedOut = haveLoanedOut;
        this.isAvailable = isAvailable;
    }

    public String getHaveLoanedOut() {
        return haveLoanedOut;
    }

    public void setHaveLoanedOut(String haveLoanedOut) {
        this.haveLoanedOut = haveLoanedOut;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public static void Customer() {
        System.out.println("Please answer 1 to know what books you currently have loaned out, or 2 to check the availabitily of a book");
        int customerAnswer = scanner.nextInt();
        if (customerAnswer == 1){
            Customers.youHaveBorrowed();
        } else if (customerAnswer == 2) {
            Customers.availability();
        }
    }

    public static String youHaveBorrowed(){
        System.out.println("What do you have borrowed");
        return scanner.nextLine();
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
                        ) {
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
                }
            }
        }
    }
}
