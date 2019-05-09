
/**
 * Write a description of class Restaurant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
public class Restaurant {
    private String rID;
    private String rName;
    private String rDescription;
    private String rLocation;
    private double rAvgPrice;
    private String rRating;
    private ArrayList<Menu> menus;

    public Restaurant()
    {
        rID = "";
        rName = "";
        rDescription = "";
        rLocation = "";
        rAvgPrice = 0;
        rRating = "";
        menus = new ArrayList<>();
    }

    public Restaurant(String rID, String rName, String rDescription, String rLocation, double rAvgPrice, String rRating) {
        this.rID = rID;
        this.rName = rName;
        this.rDescription = rDescription;
        this.rLocation = rLocation;
        this.rAvgPrice = rAvgPrice;
        this.rRating = rRating;
        menus = new ArrayList<>();
    }

    public String getrID() {
        return rID;
    }

    public void setrID(String rID) {
        this.rID = rID;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrDescription() {
        return rDescription;
    }

    public void setrDescription(String rDescription) {
        this.rDescription = rDescription;
    }

    public String getrLocation() {
        return rLocation;
    }

    public void setrLocation(String rLocation) {
        this.rLocation = rLocation;
    }

    public double getrAvgPrice() {
        return rAvgPrice;
    }

    public void setrAvgPrice(double rAvgPrice) {
        this.rAvgPrice = rAvgPrice;
    }

    public String getrRating() {
        return rRating;
    }

    public void setrRating(String rRating) {
        this.rRating = rRating;
    }
    
    public ArrayList<Menu> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }    
}