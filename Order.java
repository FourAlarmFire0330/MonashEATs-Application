/**
 * Write a description of class Order here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Order {
    private String oID;
    private String uID;
    private String userName;
    private String oStatus;
    private int oRemainTime;
    private int oDistance;
    private double totalPrice;
    private String orderGenerateTime;

    public Order(String oID, String uID, String userName, String oStatus, int oRemainTime, int oDistance, double totalPrice, String orderGenerateTime) {
        this.oID = oID;
        this.uID = uID;
        this.userName = userName;
        this.oStatus = oStatus;
        this.oRemainTime = oRemainTime;
        this.oDistance = oDistance;
        this.totalPrice = totalPrice;
        this.orderGenerateTime = orderGenerateTime;
    }

    public String getoID() {
        return oID;
    }

    public void setoID(String oID) {
        this.oID = oID;
    }

    public String getoStatus() {
        return oStatus;
    }

    public void setoStatus(String oStatus) {
        this.oStatus = oStatus;
    }

    public int getoRemainTime() {
        return oRemainTime;
    }

    public void setoRemainTime(int oRemainTime) {
        this.oRemainTime = oRemainTime;
    }

    public int getoDistance() {
        return oDistance;
    }

    public void setoDistance(int oDistance) {
        this.oDistance = oDistance;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public String getOrderGenerateTime() {
        return orderGenerateTime;
    }
    
    public void setOrderGenerateTime(String orderGenerateTime) {
        this.orderGenerateTime = orderGenerateTime;
    }
}