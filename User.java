/**
 * 
 */
import java.util.ArrayList;
public class User {
    private String userID;
    private String userName;
    private String password;
    private String phoneNumber;
    private String emailAddress;
    private ArrayList<Order> orders;

    public User() {
        userID = "";
        userName = "";
        password = "";
        phoneNumber = "";
        emailAddress = "";
        orders = new ArrayList<>();
    }

    public User(String userID, String userName, String password, String phoneNumber, String emailAddress) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        orders = new ArrayList<>();
    }

    public String getUserID() { return userID; }

    public void setUserID(String userID) { this.userID = userID; }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
