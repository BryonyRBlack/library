import Users.User;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {

        User user = new User(User.inputId(), User.inputUserName(), User.inputPassword());
        System.out.println("Hello " + user.getName() + ". Please "+ User.inputUserType());

    }
}