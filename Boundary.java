
/**
 * Write a description of class Boundary here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Boundary
{
    private Controller controller;
    private Database database;
    private ArrayList<Restaurant> restaurants;
    
    
    public Boundary()
    {
        controller = new Controller();
        database = new Database();
        restaurants = database.readFromRestaurants();
        database.readFromMenus(restaurants);
    }   
    
    // The Entrance of the application
    public void Welcome()
    {
        String option = "";
        int userIdentifier = 0;
        
        do {
            System.out.println("Welcome to Monash Eats!");
            System.out.println("1. Register Account");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.println("Please enter your option: ");
            Scanner console = new Scanner(System.in);
            option = console.nextLine();
            switch (option) {
                case "1":
                    System.out.print('\u000C');
                    controller.registerUser();
                    break;
                case "2":
                    System.out.print('\u000C');
                    userIdentifier = controller.logIn();
                    if (userIdentifier == 2) {
                        Management();
                        break;
                    }
                    else if(userIdentifier == 1) {
                        displayHomePage();
                        break;
                    }
                    else
                        break;
                case "3":
                    System.out.println("See you next time!");
                    System.exit(1);
                    break;
                default :
                    System.out.println("There is no such option! ");
                    System.out.println("Please re-enter:");
            }
        } while (true);
    }
    
    public void Management()
    {
        Scanner console = new Scanner(System.in);
        String option;
        System.out.println("Welcome to Monash Eats, Administrator!");
        do {
            System.out.println("Please select the following options: ");
            System.out.println("1.  Manage Restaurant ");
            System.out.println("2.  Log Out ");
            option = console.nextLine();
            switch (option) {
                case "1":
                    System.out.print('\u000C');
                    controller.manageRestaurant();
                    break;
                case "2":
                    controller.logOut();
                    System.out.println("Press Enter to the Login Page~");
                    console.nextLine();
                    System.out.print('\u000C');
                    Welcome();
                    break;
                default:
                    System.out.println("There is no such option! ");
                    System.out.println("Please re-enter:");
            }
        } while (true);
    }
    
    public void displayHomePage()
    {
        String option = "";
        boolean outOfLoop = true;
        
        System.out.println("Welcome to Monash Eats, " + controller.getLogInUser().getUserName());
        do {
            displayRestaurant();
            Scanner console = new Scanner(System.in);
            option = console.nextLine();
            switch (option) {
                case "1":
                    String sortOption;
                    System.out.println("Please select the following options: ");
                    System.out.println("1. Sorting By Price");
                    System.out.println("2. Sorting By Ratings");
                    sortOption = console.nextLine();
                    System.out.print('\u000C');
                    controller.sortRestaurant(sortOption, restaurants);
                    break;
                case "2":
                    String selectOption;
                    System.out.println("Please enter the ID of the restaurant that you want to select: ");
                    selectOption = console.nextLine();
                    for (Restaurant restaurant : restaurants) {
                        if (restaurant.getrID().equals(selectOption)) {
                            System.out.print('\u000C');
                            //controller.selectRestaurant(restaurant);
                            displayMenu(restaurant);
                        }
                    }
                    break;
                case "3":
                    System.out.print('\u000C');
                    displayOrders();
                    System.out.println("Press Enter to go to the Home Page!");
                    console.nextLine();
                    System.out.print('\u000C');
                    break;
                case "4":
                    controller.logOut();
                    System.out.println("Press Enter to the Login Page~");
                    console.nextLine();
                    System.out.print('\u000C');
                    Welcome();
                    break;
                default :
                    System.out.println("There is no such option! ");
                    System.out.println("Please re-enter:");
            }
        } while (outOfLoop);
    }    

    public void displayRestaurant()
    {
        System.out.println("These are the restaurants within 30kms: \n");
        for (Restaurant restaurant : restaurants) {
                String rating = "";
                System.out.println(restaurant.getrID() + ". Name: \t\t" + restaurant.getrName());
                System.out.println("   Description: \t" + restaurant.getrDescription());
                System.out.println("   Location: \t\t" + restaurant.getrLocation());
                System.out.println("   Average Price: \t" + "$" + restaurant.getrAvgPrice());
                for (int i = 0; i < Integer.parseInt(restaurant.getrRating()); i++)
                    rating += "*";
                System.out.println("   Rating: \t\t" + rating);
                System.out.println();
        }
        System.out.println("Please select the following options: ");
        System.out.println("1. Sorting an Restaurant");
        System.out.println("2. Select an Restaurant");
        System.out.println("3. View your Orders");
        System.out.println("4. Log out");
        System.out.println("Please enter your option: ");        
    }
    
    public void displayCart()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("This is your cart: ");
        if (controller.getCart().getTotalPrice() == 0)
        {
            System.out.println("The cart is empty, Order something in the cart~!");
            System.out.println("Press Enter to continue~");
            console.nextLine();
            System.out.print('\u000C');
        }        
        else
        {
            for (Menu menu : controller.getCart().getMenus()) {
                System.out.println("order name: " + menu.getmName());
                System.out.println("\t\tIngredients: " + menu.getmIngredients());
                System.out.println("\t\tPrice" + menu.getmPrice() + "\n");
            }
            System.out.println("The total price is: " + controller.getCart().getTotalPrice());
                    
            controller.checkOut();
        }
    }
    
    public void displayOrders()
    {
        database.readFromOrders(controller.getLogInUser());
        if (controller.getLogInUser().getOrders().size() == 0)
        {
            System.out.println("There is no order~");
            System.out.println();
        }
        else
        {
            System.out.println(controller.getLogInUser().getUserName() + ", This is your order: ");
            for (Order orders : controller.getLogInUser().getOrders()) {
                System.out.println(orders.getoID() + ":");
                System.out.println("Status: " + orders.getoStatus());
                System.out.println("The remainTime is: " + orders.getoRemainTime() + "mins");
                System.out.println("The remaining Distance is: " + orders.getoDistance() + "m");
                System.out.println("Total Price for this order is: " + orders.getTotalPrice());
                System.out.println("The date of this order is: " + orders.getOrderGenerateTime());
                System.out.println();
            }
        }        
    }
    
    public void displayMenu(Restaurant restaurant)
    {
        boolean outOfLoop = true;
        Scanner console = new Scanner(System.in);
        String selectOption;
        String menuID;
        
        do {
            System.out.println("This is restaurant: " + restaurant.getrName());
            System.out.println("These are the Menu in this restaurant: \n");
            for (Menu menu : restaurant.getMenus()) {
                System.out.println(menu.getmID() + ". Name: \t\t\t" + menu.getmName());
                System.out.println("   Ingredients: \t\t" + menu.getmIngredients());
                System.out.println("   Price: \t\t\t" + "$" + menu.getmPrice());
                System.out.println();
            }
        
            System.out.println("Please select the following options: ");
            System.out.println("1. Add Menu");
            System.out.println("2. View Cart");
            System.out.println("3. Go Back");
            selectOption = console.nextLine();
            switch (selectOption) {
                case "1":
                    System.out.println("Please enter the menu ID to add to the cart");
                    menuID = console.nextLine();
                    for (Menu menu : restaurant.getMenus()) {
                        if (menu.getmID().equals(menuID)) {
                            controller.addToCart(menu, controller.getCart());
                            System.out.println("Successfully add to the cart! ");
                            System.out.println("Press Enter to Continue~");
                            console.nextLine();
                            System.out.print('\u000C');
                        }
                    }
                    break;
                case "2":                    
                    displayCart();
                    break;
                case "3":
                    outOfLoop = false;
                    System.out.print('\u000C');
                    break;
                default :
                    System.out.println("There is no such option! ");
                    System.out.println("Please re-enter:");        
            }
        } while (outOfLoop);
    }
}
