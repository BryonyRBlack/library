package com.nology.library.Users;

public class Admin extends User{
    private String onLoan;
    private int numOfTimesLoaned;

    public Admin(int id, String name, String password, String onLoan, int numOfTimesLoaned){
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

    public static String confirmLoan(){
        System.out.println("How many books are loaned out");
        return UserUtils.loanedOut();
    }

    public static String howManyTimesLoaned(){
        System.out.println("What book do you want to know?");
        return " ";
    }


}
