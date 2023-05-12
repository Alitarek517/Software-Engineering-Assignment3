package Managers;

import Models.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**

 This class manages the delivery and status of an Order object.
 */
public class ManageOrder {
    /**

     The current order being managed.
     */
    Order currOrder;
    /**

     Creates a new ManageOrder object with the given Order object.
     @param order the Order object to be managed
     */
    ManageOrder(Order order) {
        currOrder = order;
    }
    /**

     Checks if the current order has been delivered and marks it as delivered if not already done so.
     Also updates the delivery date and marks the order as paid.
     @return true if the order has been delivered, false otherwise
     */
    public boolean isOrderDelivered() {
        if (currOrder != null && !currOrder.isPaid) {
            if (!currOrder.isDelivered) {
                System.out.println("Order has been delivered successfully You Have To Pay " + currOrder.totalPrice + "$");
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                currOrder.deliveryDate = currentDate.format(formatter);
                currOrder.isPaid=true;
                currOrder.isDelivered = true;
            } else
                System.out.println("Order has already been delivered since " + currOrder.deliveryDate);
            return true;
        }
        return false;
    }
    /**

     Displays the status of the current order, whether it has been delivered or not.
     */
    public void orderStatus() {
        if (currOrder.isDelivered) {
            System.out.println("The Order Has Been Delivered and Closed Successfully");
        } else {
            System.out.println("The Order Has not Been Delivered yet");
        }
    }
    /**

     Cancels the current order and sets the currOrder variable to null.
     */
    public void cancelCurrentOrder() {
        currOrder=null;
    }
}