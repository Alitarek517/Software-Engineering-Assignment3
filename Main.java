
import Managers.*;
import Models.*;

import java.io.IOException;
import java.util.Scanner;
/**

 This class represents the main class of the Toffee Store website.

 It handles the user's interactions with the website, including registration, login, and shopping cart management.

 It uses the ManageUser and shoppingCart classes to handle user data and shopping cart information.
 */
public class Main {
    static Scanner input = new Scanner(System.in);
    static public ManageUser user = new ManageUser();
    static public shoppingCart cart = new shoppingCart();
    /**

     This method displays the shopping cart menu and handles the user's interactions with it.
     The user can view the cart, clear the cart, check out, or go back to the main menu.
     If the user tries to check out an empty cart, an error message is displayed.
     */
    public static void shoppingCartMenu() {
        int cartChoice;
        System.out.println("Welcome To Shopping Cart Menu");
        while (true) {
            System.out.println("Choose From The Following Options");
            System.out.println("1-View Cart\n2-Clear Cart\n3-Check Out\n4-Go Back");
            System.out.print("Enter Your Choice: ");
            cartChoice = input.nextInt();
            switch (cartChoice) {
                case 1:
                    cart.displayCart();
                    break;
                case 2:
                    cart.clearCart();
                    break;
                case 3:
                    if(cart.cart.isEmpty()){
                        System.out.println("The Cart is Empty You Can't Check Out");
                        break;
                    }
                    Order order = new Order(cart.cart, cart.totalPrice, user.customer.address);
                    cart.checkOut(order);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Wrong Choice Enter a Valid One");
                    break;
            }
        }
    }
    /**

     This method displays the logged-in user's view menu and handles the user's interactions with it.

     The user can add an item to the cart, remove an item from the cart, edit an item in the cart, or go back to the main menu.
     */
    public static void loggedInViewMenu() {
        int logChoice, id, quantity;

        while (true) {
            System.out.println("Choose From The Following Options");
            System.out.println("1-Add Item To Cart\n2-Remove Item From Cart" +
                    "\n3-Edit a Selected Item\n4-Go Back");
            System.out.print("Enter Your Choice: ");
            logChoice = input.nextInt();
            switch (logChoice) {
                case 1:
                    System.out.print("Enter The ID Of The Item You Want To Add: ");
                    id = input.nextInt();
                    System.out.print("Enter The Quantity Of The Item: ");
                    quantity = input.nextInt();
                    cart.addItem(id, quantity);
                    break;
                case 2:
                    System.out.println("Enter The ID Of The Item You Want To Remove From The Cart");
                    id = input.nextInt();
                    cart.removeItem(id);
                    break;
                case 3:
                    System.out.println("1-Increase Quantity Of an Item\n2-Decrease Quantity Of an Item");
                    id = input.nextInt();
                    switch (id) {
                        case 1:
                            System.out.print("Enter The ID Of The Item You Want To increase: ");
                            id = input.nextInt();
                            System.out.print("Enter The Quantity to be increased: ");
                            quantity = input.nextInt();
                            cart.increaseQuantity(id,quantity);
                            break;
                        case 2:
                            System.out.print("Enter The ID Of The Item You Want To Decrease: ");
                            id = input.nextInt();
                            System.out.print("Enter The Quantity to be Decrease: ");
                            quantity = input.nextInt();
                            cart.decreaseQuantity(id,quantity);
                            break;
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Wrong Choice Enter a Valid One");
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ItemsData.loadData();
        int choice;
        System.out.printf("%-55s%s\n", "", "Hello To Toffee Store Website!");
        System.out.printf("%-55s%s\n", "", "------------------------------");
        while (true) {
            System.out.println("Choose From The Following Options");
            if (user.customer == null) {
                System.out.println("1-Register\n2-Log in\n3-View\n4-Exit");
                System.out.print("Enter Your Choice: ");
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        user.Register();
                        break;
                    case 2:
                        user.LogIn();
                        break;
                    case 3:
                        user.viewMenu();
                        break;
                    case 4:
                        System.out.println("Thanks for Using The Website,Bye :')");
                        return;
                    default:
                        break;
                }
            } else {
                System.out.println("1-Shopping Cart\n2-Log out\n3-ViewMenu\n4-Exit");
                System.out.print("Enter Your Choice: ");
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        shoppingCartMenu();
                        break;
                    case 2:
                        user.LogOut();
                        break;
                    case 3:
                        user.viewMenu();
                        loggedInViewMenu();
                        break;
                    case 4:
                        System.out.println("Thanks for Using The Website,Bye ;)");
                        return;
                    default:
                        break;
                }
            }
        }
    }

}