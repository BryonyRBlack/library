package Users;

public class Admin extends User{
    private String onLoan;
    private int numOfTimesLoaned;

    public Admin(int id, String name, String password, String onLoan, int numOfTimesLoaned){
        super(id, name, password);
        this.onLoan = onLoan;
        this.numOfTimesLoaned = numOfTimesLoaned;
    }
}
