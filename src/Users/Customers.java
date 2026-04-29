package Users;

public class Customers extends User {
    private String haveLoanedOut;
    private Boolean isAvailable;

    public Customers(int id, String name, String password, String haveLoanedOut, Boolean isAvailable){
        super(id, name, password);
        this.haveLoanedOut = haveLoanedOut;
        this.isAvailable = isAvailable;
    }

    public String getHaveLoanedOut() {
        return haveLoanedOut;
    }

    public void setHaveLoanedOut(String haveLoanedOut) {
        this.haveLoanedOut = haveLoanedOut;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
