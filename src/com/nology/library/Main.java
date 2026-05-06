package com.nology.library;

import com.nology.library.Users.User;
import com.nology.library.Users.Admin;
import com.nology.library.Users.Customers;
import com.nology.library.Users.UserUtils;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.nology.library.Users.User;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileWriter;





public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter 1 to create a new account, 2 to log into an existing account, or 3 to view all books");
        int existStatus = scanner.nextInt();
        if (existStatus == 1) {
            UserUtils.signUp();
        } else if (existStatus == 2) {
            UserUtils.logIn();
        } else if (existStatus == 3) {
            UserUtils.viewAllBooks();
        }
    }
}

        //User user = new User(User.inputId(), User.inputUserName(), User.inputPassword());
        //System.out.println("Hello " + user.getName() + ". Please "+ User.inputUserType());




