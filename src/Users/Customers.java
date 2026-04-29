package Users;

public class Customers extends User {
    private String haveLoanedOut;
    private Boolean isAvailable;

    public Customers(int id, String name, String password, String haveLoanedOut, Boolean isAvailable){
        super(id, name, password);
        this.haveLoanedOut = haveLoanedOut;
        this.isAvailable = isAvailable;
    }
}
