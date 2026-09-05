package src;

import java.util.InputMismatchException;
import java.util.Scanner;

final class Main extends FinalGreeting {
    static Scanner sc = new Scanner(System.in);

    // Static block runs once when class is loaded
    // Used here to display welcome banner before main() executes

    static {
        new Main().greet(); //---------------- overriding(runtime polymorphism)
    }


    public static void main(String[] args) {

        //--------------Setting User Basic Details-------------------//

        FoodOrderingSystem ob1 = new FoodOrderingSystem();

        System.out.println("---------- Enter Your Name Below ----------\n");
        ob1.setUserName();
        System.out.println();
        System.out.println();
        System.out.println("---------- Enter Your Address Below ----------\n");
        ob1.setAddress();
        System.out.println();
        System.out.println();
        System.out.println("---------- Enter Your Mobile Number Below ----------\n");
        ob1.setMobileNumber();
        System.out.println();
        System.out.println();


        //----------setting food items and restaurants-----------//

        Restaurant[] restaurants = Owner.updatedRes;

        //----------------Displaying options / MAIN software body------------------//

        do {
            //---------choosing an action from display-----------------//

            System.out.println("Choose One Of The Following Actions.\n");
            System.out.println("1. MENU\n2. SEARCH\n3. ADD TO CART\n4. VIEW CART\n5. WALLET\n6. CHECKOUT\n7. EXIT APPLICATION");
            System.out.print("\nEnter Index No. Of Action(1-7): ");
            String choice = sc.nextLine().trim();
            while (!choice.matches("^[1-7]$")) {
                System.out.println("Invalid Input!!! Try Again!!!");
                System.out.print("Enter: ");
                choice = sc.nextLine().trim();
            }
            System.out.println();

            //---------switch case for chosen action-----------------//

            switch (choice) {

                //----Menu Display Option-----//

                case "1" -> {
                    for (Restaurant restaurant : restaurants) {
                        restaurant.showMenu();  //shows menu of each restaurant
                    }
                    System.out.println("\n\n\n");
                }

                //----Search Option----//

                case "2" -> {
                    boolean flag_for_2 = true;
                    while (flag_for_2) {
                        System.out.println("Choose One Of The Following Actions.\n");
                        System.out.println("1. SEARCH BY CATEGORY\n2. SEARCH BY CUISINE\n3. EXIT SEARCH");
                        System.out.print("\nEnter Index No. Of Action(1-3): ");
                        String choice_2 = sc.nextLine().trim();             //search choice and its validation
                        while (!choice_2.matches("^[1-3]$")) {
                            System.out.println("Invalid Input!!! Try Again!!!");
                            System.out.print("Enter: ");
                            choice_2 = sc.nextLine().trim();
                        }
                        System.out.println();

                        switch (choice_2) {

                            //-----search by category------//

                            case "1" -> {
                                System.out.println("Choose One Of The Following Restaurant.\n");
                                for (int i = 0; i < restaurants.length; i++) {
                                    System.out.println(i + 1 + ". " + restaurants[i].name + "\n");
                                }

                                int choice_of_restaurant;

                                while (true) {                       //choice of restaurant and it's validation
                                    try {
                                        System.out.print("Enter Index No. Of Restaurant(1-" + restaurants.length + "): ");
                                        choice_of_restaurant = sc.nextInt();
                                        if (choice_of_restaurant > restaurants.length || choice_of_restaurant < 1) {
                                            System.out.println("Invalid Input!!! Try Again!!!");
                                            continue;
                                        }
                                        break;
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalid Input!!! Try Again!!!");
                                        sc.next(); // VERY IMPORTANT: clear wrong input
                                    }
                                }

                                System.out.println();
                                System.out.println("Following Are The Categories Available In Selected Restaurant.\n");

                                restaurants[choice_of_restaurant - 1].showCategories(); //shows distinct categories available in selected restaurant

                                System.out.println();
                                int choice_of_category;

                                while (true) {  //category choice and validation
                                    try {
                                        System.out.print("Enter Index No. Of Category(1-" + restaurants[choice_of_restaurant - 1].count_category + "): ");
                                        choice_of_category = sc.nextInt();
                                        if (choice_of_category > restaurants[choice_of_restaurant - 1].count_category || choice_of_category < 1) {
                                            System.out.println("Invalid Input!!! Try Again!!!");
                                            continue;
                                        }
                                        break;
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalid Input!!! Try Again!!!");
                                        sc.next(); // VERY IMPORTANT: clear wrong input
                                    }
                                }

                                System.out.println();
                                System.out.println("🏪 " + restaurants[choice_of_restaurant - 1].name);
                                restaurants[choice_of_restaurant - 1].searchCategory(choice_of_category); // calls method which shows the menu of selected cate. in selected rest.
                                sc.nextLine();//needed for next iteration.

                                System.out.println();
                            }


                            //-----search by cuisine------//


                            case "2" -> {
                                System.out.println("Choose One Of The Following Cuisine.");
                                System.out.println();
                                restaurants[0].showCuisine(); //shows cuisine across all cuisine.
                                System.out.println();

                                int choice_of_cuisine;
                                while (true) {
                                    try {
                                        System.out.print("Enter Index No. Of Cuisine(1-" + Restaurant.count_cuisine + "): ");
                                        choice_of_cuisine = sc.nextInt();
                                        if (choice_of_cuisine > Restaurant.count_cuisine || choice_of_cuisine < 1) {
                                            System.out.println("Invalid Input!!! Try Again!!!");
                                            continue;
                                        }
                                        break;
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalid Input!!! Try Again!!!");
                                        sc.next(); // VERY IMPORTANT: clear wrong input
                                    }
                                }

                                ob1.searchCuisineAcrossRestaurants(restaurants, choice_of_cuisine - 1); //shows cuisine's menu across all restaurant
                                sc.nextLine();//needed for next iteration.
                                System.out.println();
                            }


                            //-----exit search-----//

                            case "3" -> flag_for_2 = false;

                        }
                    }
                }

                //----Add To Cart----//

                case "3" -> new Cart().addToCart(restaurants);

                //----View Cart----//

                case "4" -> new Cart().viewCart();

                //----Wallet----//

                case "5" -> {
                    System.out.println("\n--- WALLET ---");
                    System.out.println("Current Balance: Rs." + Wallet.walletBalance);
                    System.out.println("1. Add Credits");
                    System.out.println("2. Back");
                    System.out.print("Enter choice: ");

                    String wChoice = sc.nextLine().trim();
                    while (!wChoice.matches("[1-2]")) {
                        System.out.print("Invalid choice! Enter again: ");
                        wChoice = sc.nextLine().trim();
                    }

                    if (wChoice.equals("1")) {
                        new Wallet().addCreditsToWallet();
                    }
                }

                //----Checkout----//

                case "6" -> {

                    if (Cart.cartCount == 0) {
                        System.out.println("Cart is empty! Add items first.\n");
                        break; //breaks switch and continues to next iteration.
                    }
                    double total = 0;
                    for (int i = 0; i < Cart.cartCount; i++) {
                        total += Cart.cart[i].getPrice(Cart.quantities[i]);
                    }

                    if (ob1.checkoutPayment(total)) {
                        System.out.println("\n🧾 Order placed successfully!\n");
                        System.out.println("---------- Delivery Details ----------");
                        System.out.println();
                        for (int i = 0; i < Cart.cartCount; i++) {
                            double itemTotal = Cart.cart[i].getPrice(Cart.quantities[i]);
                            System.out.println((i + 1) + ". " + Cart.cart[i].name + " | Qty: " + Cart.quantities[i] + " | Rs." + itemTotal);
                        }
                        System.out.println("------------------------");
                        System.out.println("Total Amount: Rs." + total);
                        System.out.println();
                        System.out.println("Recipient: " + ob1.userName);
                        System.out.println("Mobile No.: " + ob1.mobileNumber);
                        System.out.println("Address: " + ob1.address + "\n");
                        if (Cart.cartCount >= 0) System.arraycopy(Cart.cart, 0, Owner.cart_, 0, Cart.cartCount);
                        Owner.cartCount_ = Cart.cartCount;
                        if (Cart.cartCount >= 0)
                            System.arraycopy(Cart.quantities, 0, Owner.quantities_, 0, Cart.cartCount);
                        Cart.cartCount = 0; // clear cart after success
                    }
                }

                //----Exit FoodOrderingSystem----//

                case "7" -> {
                    System.out.print("Do you want to exit the system (Y/N)?");
                    String confirm = sc.nextLine().trim().toUpperCase();
                    if (!confirm.matches("[YN]")) {
                        do {
                            System.out.println("Invalid Input!!! Try Again!!!");
                            System.out.print("Enter: ");
                            confirm = sc.nextLine();
                        } while (!confirm.matches("[YN]"));
                    }
                    if (confirm.equals("Y")) {
                        new FinalGreeting().greet();
                        System.exit(0);
                    }

                    String[] false_args = new String[0];
                    main(false_args);

                }
            }
        } while (true);
    }

    void greet() {
        System.out.println("\n\n                        -~-~-~-~-~-~-~-~-~- WELCOME TO ONLINE FOOD ORDERING SYSTEM -~-~-~-~-~-~-~-~-~-\n\n\n");
    }
}