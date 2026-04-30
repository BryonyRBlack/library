package com.nology.library;

import com.nology.library.Users.User;
import com.nology.library.Users.Admin;
import com.nology.library.Users.Customers;
import java.util.Scanner;
import com.nology.library.Users.User;
import com.opencsv.CSVWriter;

import java.io.FileWriter;





public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter 1 to create a new account, or 2 to log into an existing account");
            int existStatus = scanner.nextInt();
            if (existStatus == 1) {
                User user = new User (User.inputId(), User.inputUserName(), User.inputPassword());
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
        }

        //User user = new User(User.inputId(), User.inputUserName(), User.inputPassword());
        //System.out.println("Hello " + user.getName() + ". Please "+ User.inputUserType());



    }
