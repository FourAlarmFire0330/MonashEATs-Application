
/**
 * Controller contains all the methods, e.g., add, search, remove
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
public class Controller
{
    private User logInUser;
    private Database database;
    private Admin administrator;
    private ArrayList<Order> orders;
    private Cart cart;   
    
    public Controller()
    {
        logInUser = new User();
        database = new Database();
        administrator = new Admin();
        orders = new ArrayList<Order>();
        cart = new Cart();
    }

    public User getLogInUser() {
        return logInUser;
    }

    public void setLogInUser(User logInUser) {
        this.logInUser = logInUser;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public Admin getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Admin administrator) {
        this.administrator = administrator;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    } 
    
    // Register Users for the Application
    public void registerUser()
    {
        User registerUser;
        String username = "";
        String password = "";
        String phoneNumber = "";
        String emailAddress = "";

        Scanner console = new Scanner(System.in);

        System.out.println("Welcome to Monash Eats!");
        System.out.println("May I have your name pls? ");
        username = console.nextLine();
        System.out.println("May I have your password pls? ");
        password = console.nextLine();
        System.out.println("May I have your phoneNumber pls? ");
        phoneNumber = console.nextLine();
        System.out.println("May I have your emailAddress pls? ");
        emailAddress = console.nextLine();
        registerUser = new User("", username, password, phoneNumber, emailAddress);

        System.out.println("Successfully Register!");
        database.writeToRegisteredUsers(registerUser);

        System.out.println("Press Enter to the Login page!");
        console.nextLine();
        System.out.print('\u000C');
    }

    // Login to the MonashEATS
    public int logIn()
    {
        String username;
        String password;
        User currentUser;
        Scanner console = new Scanner(System.in);

        System.out.println("Welcome to Monash Eats!");
        System.out.println("Enter your username pls~ ");
        username = console.nextLine();
        System.out.println("Enter your password pls~ ");
        password = console.nextLine();
        currentUser = new User("", username, password, "", "");

        logInUser = database.readFromRegisteredUser(currentUser);
        if (logInUser == null) {
            System.out.println("Wrong username or password");
            System.out.println("Press Enter to Login Again!");
            console.nextLine();
            System.out.print('\u000C');
            //  invalid user
            return 0;
        }
        else if (logInUser.getUserName().equals("admin") && logInUser.getPassword().equals("admin"))
        {
            System.out.println("Successfully Login!");
            System.out.println("Welcome to Monash Eats, Administrator!");
            System.out.println("Press Enter to the Manage Page!");
            console.nextLine();
            System.out.print('\u000C');
            //  Admin User
            return 2;  
        }
        else {
            System.out.println("Successfully Login!");
            System.out.println("Welcome to Monash Eats,  " + logInUser.getUserName());
            System.out.println("Press Enter to the Home page!");
            console.nextLine();
            System.out.print('\u000C');
            //  Customer User
            return 1;
        }
    }

    //  Log out MonashEATS
    public void logOut()
    {
        logInUser = null;   
        System.out.println("Successfully Log out!");
    }

    //  Add one menu to the cart at a time
    public void addToCart(Menu menu, Cart cart)
    {
        double totalPrice = 0;
        cart.getMenus().add(menu);
        for (Menu m : cart.getMenus())
        {
            totalPrice += m.getmPrice();
        }
        cart.setTotalPrice(totalPrice);
    }

    //  Check out the cart
    public void checkOut()
    {
        String checkOutOption;
        Scanner console = new Scanner(System.in);
        System.out.println("Do you want to check out? (Y/N) or (y/n)");
        checkOutOption = console.nextLine();
        if (checkOutOption.equals("Y") || checkOutOption.equals("y")) {
            generateOrders();
            cart = new Cart();
        }
        else {
            System.out.print('\u000C');
        }
    }    

    //  Once the user has checked out, a order will be generated
    public void generateOrders()
    {
        Scanner console = new Scanner(System.in);
        int remainTime = generateRandomNumber(0, 20);
        String status;
        int distance;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          
        if (remainTime > 5 && remainTime <= 15) {
            status = "Delivering";
            distance = generateRandomNumber(100, 600);
        }
        else if (remainTime > 15 && remainTime <= 20) {
            status = "Preparing";
            distance = generateRandomNumber(700, 900);
        }
        else {
            status = "Delivered";
            distance = 0;
        }
        database.writeToOrders(logInUser, cart, status, remainTime, distance, df.format(new Date()));
        System.out.println("Successfully place an order");
        System.out.println("Press Enter to go to the Home Page!");
        console.nextLine();
        System.out.print('\u000C');
    }

    public int generateRandomNumber(int low, int high)
    {
        int randomNumber = low + (int)(Math.random() * (high - low));
        if (randomNumber <= 5)
            randomNumber = 0;
        return randomNumber;
    }

    //  Administrator manages restaurant
    public void manageRestaurant()
    {
        Scanner console = new Scanner(System.in);
        String option;
        String restaurantID;
        boolean outOfLoop = true;
        
        do {    
            System.out.println("These are the restaurants in the database:");
            System.out.println("1. Name: xxx");
            System.out.println("   Description: xxx");
            System.out.println("   Average Price: xxx");
            System.out.println("   Ratings: xxx \n");
            
            System.out.println("Please Select the following options:");
            System.out.println("1.  Select restaurant to manage the feedback ");
            System.out.println("2.  Delete the restaurant ");
            System.out.println("3.  Go Back");
            option = console.nextLine();
            
            switch (option) {
                case "1":
                    System.out.println("Please enter the restaurant ID: ");
                    restaurantID = console.nextLine();
                    System.out.print('\u000C');
                    manageFeedBack(restaurantID);
                    break;
                case "2":
                    System.out.println("Please enter the restaurant ID: ");
                    restaurantID = console.nextLine();
                    deleteRestaurant(restaurantID);
                    break;
                case "3":
                    outOfLoop = false;
                    System.out.print('\u000C');
                    break;
                default:
                    System.out.println("There is no such option! ");
                    System.out.println("Please re-enter:");
            }
        } while (outOfLoop);
    }

    //  Administrator manages feedbacks of a particular restaurant
    public void manageFeedBack(String restaurantID)
    {
        String option;
        String selectedCID;
        boolean outOfLoop = true;
        Scanner console = new Scanner(System.in);
        
        do {
            System.out.println("These are the Feedback Of the restaurant:");
            System.out.println("1.  User: xxx");
            System.out.println("    Comment: xxxxxxxxxxxxxxxxxxx");
            System.out.println("    Ratings: ***\n");
            
            System.out.println("Please Select the following options:");
            System.out.println("1.  Select feedback to delete ");
            System.out.println("2.  Go Back");
            option = console.nextLine();
            
            switch (option) {
                case "1":
                    System.out.println("Please select the Comment ID that you want to delete: ");
                    selectedCID = console.nextLine();                    
                    System.out.println("You have deleted the Comment successfully~");
                    System.out.println("Press enter to continue~");
                    console.nextLine();
                    System.out.print('\u000C');
                    break;
                case "2":
                    outOfLoop = false;
                    System.out.print('\u000C');
                    break;
                default:
                    System.out.println("There is no such option! ");
                    System.out.println("Please re-enter:");
            }
        } while (outOfLoop);
    }
    
    public void deleteRestaurant(String restaurantID)
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Unfinished Part:");
        System.out.println("This function will perform delete the selected restaurant");
        System.out.println("The Restaurant that you chose has been deleted from the database successfully!");
        System.out.println("Press Enter to Go Back~");
        console.nextLine();
        System.out.print('\u000C');
    }
    
    //  Sort the Restaurant By different options
    public void sortRestaurant(String sortOption, ArrayList<Restaurant> restaurants)
    {
        Collections.sort(restaurants, new Comparator<Restaurant>() {
            public int compare(Restaurant o1, Restaurant o2) {
                if (sortOption.equals("1")) {
                    //  Sort the Restaurant by Price in Ascending Orders
                    if(o1.getrAvgPrice() < o2.getrAvgPrice())
                        return -1;
                    if(o1.getrAvgPrice() == o2.getrAvgPrice())
                        return 0;
                    return 1;
                }
                else {
                    //  Sort the Restaurant by Ratings in Descending Orders
                    if(Integer.parseInt(o1.getrRating()) < Integer.parseInt(o2.getrRating()))
                        return 1;
                    if(Integer.parseInt(o1.getrRating()) == Integer.parseInt(o2.getrRating()))
                        return 0;
                    return -1;
                }
            }
        });
    }
}
