
/**
 * Write a description of class Cart here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
public class Cart {
    private User loginUser;
    private double totalPrice;
    private ArrayList<Menu> menus;

    public Cart()
    {
        loginUser = new User();
        totalPrice = 0;
        menus = new ArrayList<>();
    }
    
    public User getUser()
    {
        return loginUser;
    }
    
    public void setUser(User loginUser)
    {
        this.loginUser = loginUser;
    }

    public ArrayList<Menu> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
