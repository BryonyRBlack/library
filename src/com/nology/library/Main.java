package com.nology.library;

import com.nology.library.Users.User;
import com.nology.library.Users.Admin;
import com.nology.library.Users.Customers;

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
            }else{
                User user = new User (User.inputId(), User.inputUserName(), User.inputPassword());
                String newName = user.getName();
                String newPassword = user.getPassword();
                int newId = user.getId();
                boolean logIn = false;
                String adminStatus = null;

                try (CSVReader reader = new CSVReader(new FileReader("userDatabase.csv"))) {
                    String[] row;
                    reader.readNext();

                    while ((row = reader.readNext()) != null){
                        String existingUsername = row[1];
                        String existingPassword = row[2];
                        if (existingUsername.equalsIgnoreCase(newName) && existingPassword.equals(newPassword)){
                            adminStatus = row[3];
                            logIn = true;
                            break;
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                } if (logIn) {
                    if(adminStatus.equalsIgnoreCase("true")) {
                        Admin.userIsAdmin();
                    }else{
                        Customers.Customer();
                    }
                }
            }
        }

        //User user = new User(User.inputId(), User.inputUserName(), User.inputPassword());
        //System.out.println("Hello " + user.getName() + ". Please "+ User.inputUserType());



    }
