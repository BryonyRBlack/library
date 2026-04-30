package com.nology.library.Users;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.util.Scanner;

public class User {
    private int id;
    private String name;
    private String password;

    static Scanner scanner = new Scanner(System.in);

    public User(int id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
