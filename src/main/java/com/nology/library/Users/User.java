package com.nology.library.Users;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.util.Scanner;

public class User {
    private String name;
    private String password;

    static Scanner scanner = new Scanner(System.in);

    public User(String name, String password){
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static int inputId(){
        System.out.println("Please enter your ID");
        return Integer.parseInt(scanner.nextLine());
    }

    public static String inputUserName() {
        System.out.println("Please enter your name");
        return scanner.nextLine();
    }

    public static String inputPassword(){
        System.out.println("Please enter your password");
        return scanner.nextLine();
    }

}
