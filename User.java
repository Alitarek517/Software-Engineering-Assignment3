/**

 The User class represents a user in the system.
 It stores the user's information, such as username, password, address, email, loyalty points, and account status.
 */
package Models;

import java.util.ArrayList;

/**
 * Creates a new user with the provided information.
 */
public class User {
    Double loyaltyPoints;
    String password,email,accStatus;
    public String username,address;
    public User(ArrayList<String> userInfo){
        username=userInfo.get(0);
        password=userInfo.get(1);
        address=userInfo.get(2);
        email=userInfo.get(3);
        loyaltyPoints=Double.parseDouble(userInfo.get(4));
        accStatus=userInfo.get(5);
    }
}
