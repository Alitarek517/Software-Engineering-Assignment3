package Models;

import Models.*;

import java.util.HashMap;
/**

 The Order class represents an order that a customer places.

 It includes the cart items, the total price of the order, and the delivery address.
 */
public class Order {
    /** A HashMap containing the items in the customer's cart and their quantities */
    public HashMap<Item, Integer> cartItems = new HashMap<Item, Integer>();
    /** The total price of the order */
    public double totalPrice;
    /** A boolean indicating whether the order has been paid for */
    public boolean isPaid = false;
    /** A boolean indicating whether the order has been delivered */
    public boolean isDelivered = false;

    /** The delivery date of the order */
    public String deliveryDate = "Unknown";
    /** The delivery address for the order */
    private String address;
    /**

     Constructs an Order object with the given cart items, total price, and delivery address.
     @param cart The HashMap of items in the customer's cart and their quantities
     @param totalPrice The total price of the order
     @param address The delivery address for the order
     */
    public Order(HashMap<Item, Integer> cart, double totalPrice, String address) {
        this.cartItems = cart;
        this.totalPrice = totalPrice;
        this.address = address;
    }
    /**

     Sets the delivery address for the order.
     @param address The new delivery address for the order
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
