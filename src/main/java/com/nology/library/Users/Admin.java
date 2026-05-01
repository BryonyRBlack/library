package com.nology.library.Users;

import com.opencsv.CSVReader;

import java.io.FileReader;

public class Admin extends User {
    private String onLoan;
    private int numOfTimesLoaned;

    public Admin(int id, String name, String password, String onLoan, int numOfTimesLoaned) {
        super(id, name, password);
        this.onLoan = onLoan;
        this.numOfTimesLoaned = numOfTimesLoaned;
    }


    public String getOnLoan() {
        return onLoan;
    }

    public void setOnLoan(String onLoan) {
        this.onLoan = onLoan;
    }

    public int getNumOfTimesLoaned() {
        return numOfTimesLoaned;
    }

    public void setNumOfTimesLoaned(int numOfTimesLoaned) {
        this.numOfTimesLoaned = numOfTimesLoaned;
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
            Admin.confirmLoan();
        } else if (adminAnswer == 2) {
            Admin.howManyTimesLoaned();
        }
    }
}

