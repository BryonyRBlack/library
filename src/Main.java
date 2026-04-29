import Users.User;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        User user = new User(User.inputId(), User.inputUserName(), User.inputPassword());

    }
}