package Users;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class UserUtils {

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
