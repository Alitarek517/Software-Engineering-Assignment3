package Managers;

import jakarta.mail.*;
import Models.*;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
/**
 * This class manages user authentication and registration.
 */
public class ManageUser {
    /**
     * The currently authenticated customer.
     */
     public User customer;

    String OTPCode;
    /**
     * Generates a 4-digit OTP code.
     *
     * @return the generated OTP code.
     */
    private String OTPGenerator(){
        Random random = new Random();

        // Generate a random 4-digit code
        int code = random.nextInt(9000) + 1000;

        return Integer.toString(code);
    }
    /**
            * Checks if the provided email is valid.
     *
             * @param email the email to validate.
     * @return true if the email is valid, false otherwise.
     */
    private boolean isEmailValid(String email) {
        String EMAIL_REGEX =
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+.[A-Za-z]+$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        return pattern.matcher(email).matches();
    }
    /**
     * Sends an OTP code to the provided email address.
     *
     * @param email the email address to send the OTP to.
     * @return true if the OTP was sent successfully, false otherwise.
     */

    private boolean OTPAuthentication(String email) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "smtp.gmail.com");

        String username = "alitarek664";
        String password = "ctftxrlqgavgxbgb";

        // Creating Session Object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setFrom(new InternetAddress("alitarek664@gmail.com"));
            String msg = "";
            OTPCode=OTPGenerator();
            message.setSubject("Welcome to Toffee Store! Confirm Your Account with OTP");
            msg = "Dear " + email + "," +
                    "\n" +
                    "We're excited to welcome you to Toffee Store " +
                    "and thank you for registering with us! To complete your account registration, " +
                    "Please use the following OTP to verify your account: " + OTPCode+
                    "\n\nThank you for choosing Toffee Store." +
                    " We look forward to providing you with the best possible shopping experience.\n\n" +
                    "Toffee Store Team";
            message.setText(msg);
            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    static Scanner input = new Scanner(System.in);
    /**
     * Displays the menu of available items.
     *
     * @throws IOException if there is an error reading the menu data.
     */
    public void viewMenu() throws IOException {
        ItemsData.showMenu();
    }
    /**
     * Registers a new user.
     *
     * @throws IOException if there is an error writing the account data.
     */
    public void Register() throws IOException {
        System.out.println("Registration Form");
        System.out.print("Enter Username: ");
        String info = input.next() + ',';
        System.out.print("Enter Password: ");
        info += input.next() + ',';
        System.out.print("Enter Address: ");
        info += input.next() + ',';
        System.out.print("Enter Email: ");
        String email = input.next();
        while (!isEmailValid(email)) {
            System.out.print("Not a valid Enter a valid email address: ");
            email = input.next();
        }
        if (OTPAuthentication(email)) {
            System.out.println("Check your Email and Enter The Confirmation Code");
            String otp = input.next();
            if (!otp.equals(OTPCode)) {
                System.out.println("The Confirmation Code is Wrong\n Try Again Later");
                return;
            }
        }
        info += email + ',';
        info += "0,Active";
        FileWriter fileWriter = new FileWriter("AccountsData.csv", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(info + "\n");
        bufferedWriter.close();
        System.out.println("You Have Registered Successfully!");
    }
    /**
     * Logs in an existing user.
     *
     * @return true if the login was successful, false otherwise.
     * @throws IOException if there is an error reading the account data.
     */

    public boolean LogIn() throws IOException {
        System.out.println("Log in Form");
        System.out.print("Enter Username: ");
        String username = input.next();
        System.out.print("Enter Password: ");
        String password = input.next();
        FileReader fileReader = new FileReader("AccountsData.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            ArrayList<String> data = new ArrayList<String>(6);
            data.addAll(Arrays.asList(line.split(",")));
            if (data.get(0).equals(username) && data.get(1).equals(password)) {
                if (data.get(5).equals("Suspended")) {
                    System.out.println("This Account is temporary Suspended");
                    return false;
                }
                System.out.println("You Have Logged in Successfully!");
                System.out.println("Welcome back "+ username+"!");
                customer = new User(data);
                return true;
            }

        }
        System.out.println("Log in Failed\n Username or password is wrong");
        return false;
    }
    /**
     * Logs out the currently authenticated user.
     */
    public void LogOut() {
        customer = null;
    }
}