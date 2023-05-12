package Models;

import java.util.ArrayList;
/**

 The Item class represents a product item that can be sold in a store.
 */


public class Item {

    public String name, category, description, brand, status, type;
    public Double price, discount;
    public int id, unitsLeft=55;

    /**
     * Constructs an Item object from an ArrayList of Strings.
     *
     * @param data the ArrayList of Strings containing the data of the item to be constructed.
     */
    public Item(ArrayList<String> data) {
        name = data.get(0);
        category = data.get(1);
        description = data.get(2);
        brand = data.get(3);
        price = Double.parseDouble(data.get(4));
        discount = Double.parseDouble(data.get(5));
        status = data.get(6);
        type = data.get(7);
    }
    /**
     * Prints the details of the item in a formatted manner.
     */
    public void printItem() {

        System.out.printf("%-22s%-25s%-62s%s%-12f%d\n", name, brand, description, "$", price, unitsLeft);
    }
}