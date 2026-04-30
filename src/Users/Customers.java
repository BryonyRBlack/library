package Users;

import com.opencsv.CSVReader;

import java.io.FileReader;

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
        }
    }
}
