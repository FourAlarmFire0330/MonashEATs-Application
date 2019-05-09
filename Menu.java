
/**
 * Store the information about a menu
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu {
    //  Fields
    private String mID;
    private String mName;
    private String mIngredients;
    private double mPrice;
    private String rID;     //  RestaurantID

    public Menu(String mID, String mName, String mIngredients, double mPrice, String rID) {
        this.mID = mID;
        this.mName = mName;
        this.mIngredients = mIngredients;
        this.mPrice = mPrice;
        this.rID = rID;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmIngredients() {
        return mIngredients;
    }

    public void setmIngredients(String mIngredients) {
        this.mIngredients = mIngredients;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public String getrID() {
        return rID;
    }

    public void setrID(String rID) {
        this.rID = rID;
    }
}