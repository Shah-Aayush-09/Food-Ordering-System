package src; /**
 * FoodOrderingSystem
 * -------------------
 * A console-based Java application that simulates an online food ordering system.
 * .
 * Features:
 * - User registration (name, address, mobile number)
 * - Browse restaurant menus
 * - Search food by category or cuisine
 * - Add, update, and remove items from cart
 * - Wallet system for adding credits and making payments
 * - Multiple checkout payment options
 * .
 * All data is stored in memory using arrays (no database).
 */

import java.util.Scanner;

class FoodOrderingSystem {


    static Scanner sc = new Scanner(System.in);

    // Scanner object used throughout the program to read user input
    String userName;


    String address;

    // Stores user personal details
    String mobileNumber;

    //---- UPI Payment Method ----//

    boolean payment_methods() {


        int attempts = 0;
        String upiRegex = "^[a-zA-Z0-9._-]{2,}@[a-zA-Z]{2,}$";

        while (attempts < 3) {

            System.out.print("Enter UPI ID (e.g. name@bank): ");
            String upiId = sc.nextLine();

            if (upiId.matches(upiRegex)) {
                System.out.println("Processing UPI payment...");
                System.out.println("✅ Payment successful via UPI!");
                return true;
            } else {
                System.out.println("❌ Invalid UPI ID format (Id must contain at least 2 valid characters before and after @).");
                attempts++;
            }
        }

        System.out.println("❌  Too many failed attempts. UPI verification failed. Payment cancelled.");
        return false;
    }

    //---- Card Payment Method ----//

    boolean payment_methods(String cardType) {

        String cardRegex = "[0-9]{16}";
        String cvvRegex = "[0-9]{3}";
        String expiryRegex = "(0[1-9]|1[0-2])/(2[6-9]|[3-9][0-9])"; //year validation must be 2k26 or above

        int attempts = 0;

        while (attempts < 3) {

            System.out.print("Enter " + cardType + " Number (16 digits): ");
            String cardNumber = sc.nextLine();

            if (!cardNumber.matches(cardRegex)) {
                System.out.println("❌ Invalid card number.");
                attempts++;
                System.out.println("🔁 Try again.\n");
                continue;
            }

            System.out.print("Enter Expiry Date (MM/YY): ");
            String expiry = sc.nextLine();
            if (!expiry.matches(expiryRegex)) {
                System.out.println("❌ Invalid expiry date.");
                attempts++;
                System.out.println("🔁 Try again.\n");
                continue;
            }

            System.out.print("Enter CVV (3 digits): ");
            String cvv = sc.nextLine();
            if (!cvv.matches(cvvRegex)) {
                System.out.println("❌ Invalid CVV.");
                attempts++;
                System.out.println("🔁 Try again.\n");
                continue;
            }

            System.out.println("Processing " + cardType + " payment...");
            System.out.println("✅ Payment successful via " + cardType + "!");
            return true;
        }

        System.out.println("❌ Too many failed attempts. Card verification failed. Payment cancelled.");
        return false;
    }

    //    Displays all food items of a selected cuisine across all restaurants.

    void searchCuisineAcrossRestaurants(Restaurant[] restaurants, int k) {

        System.out.println("\n--- Restaurants Serving " + Restaurant.available_cuisine[k] + " Cuisine ---");

        int false_cuisine_count = 1;

        // Loop through each restaurant to check if it has items of given cuisine

        for (Restaurant restaurant : restaurants) {

            int restaurant_has_cuisine = 0;

            // Check each menu item (avoid null to prevent NullPointerException)

            for (FoodItem MENU : restaurant.menu) {
                if (MENU != null) { // because if null than restaurants[i].menu[j].cuisine will throw error as null.cuisine = ?
                    if (Restaurant.available_cuisine[k].equals(MENU.cuisine)) {
                        restaurant_has_cuisine++;
                        if (restaurant_has_cuisine == 1) { //this is to print name of restaurant only once
                            System.out.println("\n🏪 " + restaurant.name + "\n");
                        }
                        System.out.print(false_cuisine_count++ + ". ");
                        MENU.display();
                    }
                }
            }
        }
    }

    //Setting Name
    void setUserName() {

        // -------- First name --------
        System.out.print("Enter Your First Name: ");
        String first_name = sc.nextLine();
        if (!first_name.matches("[a-zA-Z@]+")) {
            do {
                System.out.println("Invalid Input!!! Try Again!!!");
                System.out.print("Enter: ");
                first_name = sc.nextLine();
            } while (!first_name.matches("[a-zA-Z@]+"));
        }
        if (first_name.equals(new Owner().id)) {
            System.out.print("Enter Password: ");
            String pas = sc.nextLine();
            if (pas.equals(new Owner().Pass)) {
                new Owner().display();
                System.out.println("\n\n                        -~-~-~-~-~-~-~-~-~- WELCOME TO ONLINE FOOD ORDERING SYSTEM -~-~-~-~-~-~-~-~-~-\n\n\n");
                setUserName();
                return;
            }
        }
        System.out.println();
        // -------- middle name --------
        System.out.print("Enter Your Middle Name: ");
        String middle_name = sc.nextLine();
        if (!middle_name.matches("[a-zA-Z]+")) {
            do {
                System.out.println("Invalid Input!!! Try Again!!!");
                System.out.print("Enter: ");
                middle_name = sc.nextLine();
            } while (!middle_name.matches("[a-zA-Z]+"));
        }
        System.out.println();
        // -------- Last name --------
        System.out.print("Enter Your Last Name: ");
        String last_name = sc.nextLine();
        if (!last_name.matches("[a-zA-Z]+")) {
            do {
                System.out.println("Invalid Input!!! Try Again!!!");
                System.out.print("Enter: ");
                last_name = sc.nextLine();
            } while (!last_name.matches("[a-zA-Z]+"));
        }
//        sc.close();   --> highly error causing thing.
        userName = first_name.toUpperCase() + " " + middle_name.toUpperCase() + " " + last_name.toUpperCase();
    }

    //Setting Address
    void setAddress() {

        // -------- Address Line 1 --------
        System.out.println("Enter Address Line 1 (House No / Society): ");
        System.out.println("It should only contain alphabets, digits, spaces and / , - .");
        System.out.print("Enter: ");
        String line1 = sc.nextLine();
        if (!line1.matches("[a-zA-Z0-9 /,-.]+")) {
            do {
                System.out.println("Invalid Input!!! Try Again!!!");
                System.out.print("Enter: ");
                line1 = sc.nextLine();
            } while (!line1.matches("[a-zA-Z0-9 /,-.]+"));
        }
        System.out.println();
        // -------- Address Line 2 --------
        System.out.println("Enter Address Line 2 (Street / Area): ");
        System.out.println("It should only contain alphabets, digits, spaces and / , - .");
        System.out.print("Enter: ");
        String line2 = sc.nextLine();
        if (!line2.matches("[a-zA-Z0-9 /,-.]+")) {
            do {
                System.out.println("Invalid Input!!! Try Again!!!");
                System.out.print("Enter: ");
                line2 = sc.nextLine();
            } while (!line2.matches("[a-zA-Z0-9 /,-.]+"));
        }
        System.out.println();
        // -------- Address Line 3 --------
        System.out.println("Enter Address Line 3 (City / State / PIN): ");
        System.out.println("Format: City<space>PIN");
        System.out.print("Enter: ");
        String line3 = sc.nextLine();
        if (!line3.matches("[a-zA-Z0-9 ]+")) {
            do {
                System.out.println("Invalid Input!!! Try Again!!!");
                System.out.print("Enter: ");
                line3 = sc.nextLine();
            } while (!line3.matches("[a-zA-Z0-9 ]+"));
        }

        address = line1.toUpperCase() + ", "
                + line2.toUpperCase() + ", "
                + line3.toUpperCase() + ".";
    }

    //Setting Mobile Number
    void setMobileNumber() {
        // Updated requirement: Must start with +91 and then follow digit rules
        System.out.print("Enter Mobile Number: +91 ");
        String mob = sc.nextLine().trim();
        System.out.println();
        // Regex explanation:
        // [6-9]      -> Next digit must be 6, 7, 8, or 9
        // [0-9]{9}$  -> Followed by exactly 9 more digits
        while (!mob.matches("^[6-9][0-9]{9}$")) {
            System.out.println("Invalid Input!!!\nMobile no. must be of 10-digits(starting with 6-9).");
            System.out.print("Enter: +91 ");
            mob = sc.nextLine().trim();
        }

        mobileNumber = "+91 " + mob;
    }

    // ================= CHECKOUT PAYMENT OPTIONS =================

    boolean checkoutPayment(double totalAmount) {

        System.out.println("\n--- CHECKOUT ---");
        System.out.println("Total Amount: Rs." + totalAmount);
        System.out.println();
        System.out.println("1. Pay using Wallet Credits");
        System.out.println("2. Pay Directly (Payment Gateway)");
        System.out.println("3. Cancel Checkout");
        System.out.print("Choose an option (1-3): ");

        String choice = sc.nextLine().trim();
        while (!choice.matches("[1-3]")) {
            System.out.print("Invalid choice! Enter again: ");
            choice = sc.nextLine().trim();
        }

        switch (choice) {

            case "1" -> {
                // Wallet payment
                if (Wallet.walletBalance <= 0) {
                    System.out.println("❌ Wallet not created or empty!\n");
                    return false;
                }

                System.out.print("Confirm payment using Wallet (Y/N): ");
                String confirm = sc.nextLine().trim().toUpperCase();
                if (confirm.equals("N")) {
                    System.out.println("Wallet payment cancelled.\n");
                    return false;
                }

                return new Wallet().useWallet(totalAmount);
            }

            case "2" -> {
                // Direct payment
                return paymentGateway(totalAmount);
            }

            case "3" -> {
                System.out.println("Checkout cancelled.\n");
                return false;
            }
        }

        return false; // safety
    }

    // ================= PAYMENT GATEWAY (WITH OPTION CONFIRMATION) =================

    boolean paymentGateway(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid payment amount!");
            return false;
        }

        System.out.println("\n--- PAYMENT GATEWAY ---");
        System.out.println("Total Amount: Rs." + amount);
        System.out.println();
        System.out.println("1. UPI");
        System.out.println("2. Debit Card");
        System.out.println("3. Credit Card");
        System.out.println("4. Cash on Delivery");
        System.out.print("Choose Payment Method (1-4): ");

        String choice = sc.nextLine().trim();
        while (!choice.matches("[1-4]")) {
            System.out.print("Invalid choice! Enter again: ");
            choice = sc.nextLine().trim();
        }

        String method = switch (choice) {
            case "1" -> "UPI";
            case "2" -> "Debit Card";
            case "3" -> "Credit Card";
            default -> "Cash on Delivery";
        };


        // ---- CONFIRM PAYMENT METHOD ----//
        System.out.print("Confirm payment using " + method + " (Y/N): ");
        String confirm = sc.nextLine().trim().toUpperCase();

        while (!confirm.matches("[YN]")) {
            System.out.print("Invalid input! Enter Y or N: ");
            confirm = sc.nextLine().trim().toUpperCase();
        }

        if (confirm.equals("N")) {
            System.out.println("❌ Payment option cancelled.\n");
            return false;
        }

        // ---- SIMULATED PAYMENT DETAILS ----
        if (choice.equals("1")) {
            return payment_methods();           //--------------------------------overloading
        } else if (choice.equals("4")) {
            System.out.println("Cash on Delivery selected.");
            return true;
        } else {
            return payment_methods(method);     //--------------------------------overloading
        }
    }
}