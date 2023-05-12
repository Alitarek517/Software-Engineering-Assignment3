
package Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**

 The ItemsData class represents the data of all available items in the store.
 It provides methods to load the data from a CSV file and display the menu of items.
 */
public class ItemsData {
    public static ArrayList<Item> items = new ArrayList<Item>();
/**
        * Loads the data of all items from a CSV file.
            *
            * @throws IOException if an I/O error occurs while reading the file
 */
    public static void loadData() throws IOException {
        FileReader fileReader = new FileReader("Items.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        int id = 1;
        while ((line = bufferedReader.readLine()) != null) {
            ArrayList<String> data = new ArrayList<>(8);
            data.addAll(Arrays.asList(line.split(",")));
            Item newItem = new Item(data);
            newItem.id = id++;
            items.add(newItem);
        }
        fileReader.close();
    }
    /**
     * Displays the menu of all available items in the store.
     */
    public static void showMenu() {
        System.out.printf("%-4s%-22s%-25s%-62s%-12s%s\n", "ID", "Name", "Brand", "Description", "Price", "Units Left");
        int id = 1;
        for (Item data : items) {
            System.out.printf("%-4d", id++);
            data.printItem();
        }
        System.out.println();
    }
}