
/**
 * Write a description of class Database here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    //  Once the user has registered an account, put this account into the file (database)
    public void writeToRegisteredUsers(User registerUser)
    {
        String content;
        //  Generate the new ID
        int newID = Integer.parseInt(getTheLastID("RegisteredUser.txt")) + 1;
        try {
            PrintWriter outPutFile = new PrintWriter(new FileWriter("RegisteredUser.txt", true));

            content = newID + ";" + registerUser.getUserName() + ";" + registerUser.getPassword() + ";" + registerUser.getPhoneNumber() +
                    ";" + registerUser.getEmailAddress();
            outPutFile.println(content);

            outPutFile.close();
        }
        catch (IOException e)
        {
            System.out.println("Something goes wrong with accessing the file!");
        }
    }

    //  Read the file to verify the login user
    public User readFromRegisteredUser(User loginUser)
    {
        User verifiedUser = new User();
        try {
            FileReader inputFile = new FileReader("RegisteredUser.txt");
            try {
                Scanner parser = new Scanner(inputFile);
                while (parser.hasNextLine()) {
                    String user = parser.nextLine();
                    String[] userInfo = user.split(";");
                    if (loginUser.getUserName().equals(userInfo[1]) && loginUser.getPassword().equals(userInfo[2])) {
                        verifiedUser = new User(userInfo[0], userInfo[1], userInfo[2], userInfo[3], userInfo[4]);
                        return verifiedUser;
                    }
                    else {
                        verifiedUser = null;
                    }
                }
            }
            finally {
                inputFile.close();
            }
            return verifiedUser;
        }
        catch (FileNotFoundException e) {
            System.out.println("File is not found!");
            return null;
        }
        catch (IOException e) {
            System.out.println("Unexpected I/O exceptions!");
            return null;
        }
    }

    public ArrayList<Restaurant> readFromRestaurants()
    {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        try {
            FileReader inputFile = new FileReader("Restaurant.txt");
            try {
                Scanner parser = new Scanner(inputFile);
                while (parser.hasNextLine()) {
                    String restaurant = parser.nextLine();
                    String[] rInfo = restaurant.split(";");
                    Restaurant newRestaurant = new Restaurant(rInfo[0], rInfo[1], rInfo[2], rInfo[3], Double.parseDouble(rInfo[4]), rInfo[5]);
                    restaurants.add(newRestaurant);
                }
                return restaurants;
            }
            finally {
                inputFile.close();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File is not found!");
            return null;
        }
        catch (IOException e) {
            System.out.println("Unexpected I/O exceptions!");
            return null;
        }
    }

    public void readFromMenus(ArrayList<Restaurant> restaurant)
    {
        try {
            FileReader inputFile = new FileReader("Menu.txt");
            try {
                Scanner parser = new Scanner(inputFile);
                while (parser.hasNextLine()) {
                    String menu = parser.nextLine();
                    String[] mInfo = menu.split(";");
                    for (Restaurant r : restaurant)
                    {                                      
                        if (r.getrID().equals(mInfo[4])) {
                            Menu newMenu = new Menu(mInfo[0], mInfo[1], mInfo[2], Double.parseDouble(mInfo[3]), mInfo[4]);
                            r.getMenus().add(newMenu);
                        }                        
                    }
                }
            }
            finally {
                inputFile.close();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File is not found!");
        }
        catch (IOException e) {
            System.out.println("Unexpected I/O exceptions!");
        }
    }
   
    public void writeToOrders(User loginUser, Cart cart, String status, int remainTime, int distance, String orderGenerateTime)
    {
        String content;
        int newOID = Integer.parseInt(getTheLastID("Orders.txt")) + 1;
        try {
            PrintWriter outPutFile = new PrintWriter(new FileWriter("Orders.txt", true));
            content = newOID + ";" + loginUser.getUserID() + ";" + loginUser.getUserName() + ";" + status + ";" +
                            remainTime + ";" + distance + ";" + cart.getTotalPrice() + ";" + orderGenerateTime;
            outPutFile.println(content);

            outPutFile.close();
        }
        catch (IOException e)
        {
            System.out.println("Something goes wrong with accessing the file!");
        }
    }

    public User readFromOrders(User loginUser)
    {
        ArrayList<Order> o = new ArrayList<Order>();
        loginUser.setOrders(o);
        User user = loginUser;
        try {
            FileReader inputFile = new FileReader("Orders.txt");
            try {
                Scanner parser = new Scanner(inputFile);
                while (parser.hasNextLine()) {
                    String orders = parser.nextLine();
                    String[] orderInfo = orders.split(";");
                    if (loginUser.getUserID().equals(orderInfo[1])) {
                        Order order = new Order(orderInfo[0], orderInfo[1], orderInfo[2],
                                                orderInfo[3], Integer.parseInt(orderInfo[4]), Integer.parseInt(orderInfo[5]), 
                                                Double.parseDouble(orderInfo[6]), orderInfo[7]);
                        user.getOrders().add(order);
                    }
                }
                return user;
            }
            finally {
                inputFile.close();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File is not found!");
            return null;
        }
        catch (IOException e) {
            System.out.println("Unexpected I/O exceptions!");
            return null;
        }
    }

    //  Get the ID of the last element to generate the new ID
    public String getTheLastID(String fileName)
    {
        String lastID = "";
        try {
            FileReader inputFile = new FileReader(fileName);
            try {
                Scanner parser = new Scanner(inputFile);
                String lastElement = "";
                if (!parser.hasNextLine()) {
                    lastID = "0";
                    return lastID;
                }
                while (parser.hasNextLine()) {
                    lastElement = parser.nextLine();
                }
                String[] lastE = lastElement.split(";");
                lastID = lastE[0];
                return lastID;
            }
            finally {
                inputFile.close();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File is not found!");
            return null;
        }
        catch (IOException e) {
            System.out.println("Unexpected I/O exceptions!");
            return null;
        }
    }
}
