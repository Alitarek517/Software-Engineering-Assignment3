package Managers;

import Models.*;


import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

/**

 The shoppingCart class represents a shopping cart for a user that can add, remove, and modify items.
 It contains methods to display the cart, increase and decrease item quantities, add and remove items,
 clear the cart, and checkout the order. It also stores the total price of the items in the cart.
 */
public class shoppingCart {
    /**
     * A HashMap to store the items in the cart and their corresponding quantities.
     */
    public HashMap<Item, Integer> cart = new HashMap<Item, Integer>();
    /**
     * The total price of all the items in the cart.
     */
    public double totalPrice = 0;
    /**
     * Displays the contents of the cart with the item name, quantity, and total price.
     */
    public void displayCart() {
        if(cart.isEmpty()){
            System.out.println("Cart is Empty Add some Items To Show It");
            return;
        }
        System.out.printf("%-22s%-12s%-12s\n", "Item Name", "Quantity", "Total Price");
        for (Item item : cart.keySet()) {
            double price = item.price;
            DecimalFormat decimalFormat = new DecimalFormat("#.###");
            double totalPrice = price * cart.get(item);
            String formattedNumber = decimalFormat.format(totalPrice);
            System.out.printf("%-22s%-12d%-12s\n", item.name, cart.get(item), formattedNumber + "$");
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        String formattedNumber = decimalFormat.format(totalPrice);
        System.out.println("Total Price: " + formattedNumber + "$");
    }
    /**
     * Increases the quantity of a specific item in the cart.
     *
     * @param ind  the index of the item to increase the quantity of
     * @param more the quantity to add to the cart
     */
    public void increaseQuantity(int ind, int more) {
        for (Item item : cart.keySet()) {
            if (item.id == ind) {
                if (ItemsData.items.get(item.id - 1).unitsLeft < more) {
                    System.out.println("The UnitsLeft For This Item is Less Than The Required item");
                    System.out.println("The UnitsLeft is: " + ItemsData.items.get(item.id - 1).unitsLeft);
                    return;
                }
                if (cart.get(item) + more > 50 && item.type.equals("sealed")) {
                    System.out.println("You Can't add From This Product More Than 50 units");
                    return;
                } else if (cart.get(item) + more > 10.0 && item.type.equals("loose")) {
                    System.out.println("You Can't add From This Product More Than 10 KG");
                    return;
                }
                totalPrice += (more * item.price);
                ItemsData.items.get(item.id - 1).unitsLeft -= more;
                cart.put(item, cart.get(item) + more);
                System.out.println("The Quantity was increased Successfully!\nTotal Quantity Now is: " + cart.get(item));
                return;
            }
        }
        System.out.println("There is No Such Item in your Cart");
    }
    /**
     * Decreases the quantity of a specific item in the cart.
     *
     * @param ind  the index of the item to decrease the quantity of
     * @param less the quantity to remove from the cart
     */

    public void decreaseQuantity(int ind, int less) {
        for (Item item : cart.keySet()) {
            if (item.id == ind) {
                if(less > cart.get(item)){
                    System.out.println("Quantity Selected To Decrease is More Than The Quantity in The Cart");
                    return;
                }
                if(less==cart.get(item)){
                    removeItem(ind);
                    return;
                }
                ItemsData.items.get(ind-1).unitsLeft+=less;
                totalPrice-=less*item.price;
                cart.put(item,cart.get(item)-less);
                System.out.println("The Quantity is Decreased Successfully!\nTotal Quantity Now is : "+cart.get(item));
                return;
            }
        }
        System.out.println("There is no such item in your cart");
    }
    /**
     * Adds a new item to the cart with a specified quantity.
     *
     * @param ind      the index of the item to add to the cart
     * @param quantity the quantity of the item to add to the cart
     */
    public void addItem(int ind, int quantity) {
        if(ind>11){
            System.out.println("There's no Such Item in Our Menu");
            return;
        }
        if (ItemsData.items.get(ind - 1).unitsLeft < quantity) {
            System.out.println("The UnitsLeft for this item is less than the required item");
            System.out.println("The UnitsLeft is: " + ItemsData.items.get(ind - 1).unitsLeft);
            return;
        }
        if(ItemsData.items.get(ind - 1).type.equals("loose")&& quantity>10){
            System.out.println("You Can't add from this product more than 10 KG");
            return;
        }
        else if(ItemsData.items.get(ind - 1).type.equals("sealed")&& quantity>50){
            System.out.println("You Can't add from this product more than 50 units");
            return;
        }
        ItemsData.items.get(ind - 1).unitsLeft -= quantity;
        cart.put(ItemsData.items.get(ind - 1), quantity);
        totalPrice += ItemsData.items.get(ind - 1).price * quantity;
    }
    /**
     * Clears the contents of the cart and sets the total price to 0.
     */
    public void clearCart() {
        if(cart.isEmpty()){
            System.out.println("The Cart is Empty");
            return;
        }
        cart.clear();
        totalPrice = 0;
        System.out.println("Cart has been Cleared Successfully!");
    }
    /**
     * Removes a specific item from the cart.
     *
     * @param ind the index of the item to remove from the cart
     */
    public void removeItem(int ind) {
        if (cart.isEmpty()) {
            System.out.println("The Cart is empty");
            return;
        }
        for (Item item : cart.keySet()) {
            if (item.id == ind) {
                totalPrice -= (cart.get(item) * item.price);
                ItemsData.items.get(ind-1).unitsLeft+=cart.get(item);
                cart.remove(item);
                System.out.println("The Chosen Item is Removed Successfully!");
                return;
            }
        }
        System.out.println("There is no such item in your cart");
    }
    /**
     * Checks out the order and creates a new ManageOrder object to manage the order.
     *
     * @param order the order to check out
     */
    public void checkOut(Order order) {

        ManageOrder currOrder = new ManageOrder(order);
        System.out.println("1-Ship to Default Address\n2-Ship to New Address\n");
        Scanner input= new Scanner(System.in);
        int id= input.nextInt();
        if(id==2){
            System.out.println("Enter The New Address");
            String address=input.next();
            currOrder.currOrder.setAddress(address);
        }
        currOrder.isOrderDelivered();
        currOrder.orderStatus();
    }
}