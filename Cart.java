package src;/* ================= CART FUNCTIONS ================= */

import java.util.InputMismatchException;
import java.util.Scanner;

class Cart {
    static Scanner sc = new Scanner(System.in);
    static FoodItem[] cart = new FoodItem[10];  // selected food items
    static int[] quantities = new int[10];      // quantity for each item

    // Cart storage (max 10 items)
    static int cartCount = 0;                   // current number of items in cart

    // ================= ADD TO CART (WITH EXIT OPTION) =================
    void addToCart(Restaurant[] restaurants) {

        if (cartCount == 10) {
            System.out.println("Maximum No. Of Items In Cart Reached.\n");
            return;
        }

        System.out.println("\n--- ADD TO CART ---\n");

        System.out.println("Limit Of Cart Is 10 Items.\n");

        System.out.println("Choose A Restaurant To Continue(1-" + restaurants.length + ").");
        System.out.println("Or Enter 0 To Exit.\n");

        for (int i = 0; i < restaurants.length; i++) {
            System.out.println((i + 1) + ". " + restaurants[i].name);
        }
        System.out.println("0. EXIT");

        int rChoice;
        while (true) {  //choosing restaurant or exit and choice validation
            try {
                System.out.print("Enter Your Choice: ");
                rChoice = sc.nextInt();

                if (rChoice == 0) {
                    sc.nextLine(); // clear buffer
                    System.out.println("⬅ Exiting Add To Cart...\n");
                    return;     //return back to where the method is called.
                }

                if (rChoice >= 1 && rChoice <= restaurants.length) break;
                System.out.println("Invalid choice!");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                sc.next();
            }
        }

        Restaurant r = restaurants[rChoice - 1];
        r.showMenu();

        System.out.println("0. EXIT");

        System.out.println("Choose An Item No. To Continue.");
        System.out.println("Or Enter 0 To Exit.\n");

        int itemChoice;
        while (true) {
            try {
                System.out.print("Enter Your Choice: ");
                itemChoice = sc.nextInt();

                if (itemChoice == 0) {
                    sc.nextLine(); // clear buffer
                    System.out.println("⬅ Exiting Add To Cart...\n");
                    return;         //return back to where the method is called.
                }

                if (itemChoice >= 1 && itemChoice <= r.count) break;
                System.out.println("Invalid choice!");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                sc.next();
            }
        }

        int qty;
        while (true) {
            try {
                System.out.print("Enter Quantity (1-20): ");
                qty = sc.nextInt();

                if (qty >= 1 && qty <= 20) break;

                System.out.println("Invalid Quantity! Allowed range is 1 to 20.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter numbers only.");
                sc.next();
            }
        }

        cart[cartCount] = r.menu[itemChoice - 1];     // returns the object of selected item to cart[cartCount]
        //r.getItem(itemChoice - 1); //getItem
        quantities[cartCount++] = qty;

        sc.nextLine(); // clear buffer
        System.out.println("✅ Item added to cart successfully!\n");
    }


    // ================= VIEW CART (WITH UPDATE & REMOVE) =================
    void viewCart() {

        if (cartCount == 0) {
            System.out.println("🛒 Cart is empty!\n");
            return;
        }

        boolean inCart = true;

        while (inCart) {

            double total = 0;
            System.out.println("\n--- YOUR CART ---");

            for (int i = 0; i < cartCount; i++) {
                double itemTotal = cart[i].getPrice(quantities[i]);
                System.out.println((i + 1) + ". " + cart[i].name + " | Qty: " + quantities[i] + " | Rs." + itemTotal);

                total += itemTotal;
            }

            System.out.println("------------------------");
            System.out.println("Total Amount: Rs." + total);

            System.out.println("\n1. UPDATE QUANTITY");
            System.out.println("2. REMOVE ITEM");
            System.out.println("3. EXIT CART");
            System.out.print("Enter choice: ");

            String choice = sc.nextLine().trim();
            while (!choice.matches("[1-3]")) {
                System.out.print("Invalid choice! Enter again: ");
                choice = sc.nextLine().trim();
            }

            switch (choice) {

                // -------- UPDATE QUANTITY -------- //
                case "1" -> {
                    int index;
                    while (true) {
                        try {
                            System.out.print("Enter item number: ");
                            index = sc.nextInt();
                            if (index >= 1 && index <= cartCount) break;
                            System.out.println("Invalid item number!");
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                    }

                    int newQty;
                    while (true) {
                        try {
                            System.out.print("Enter new quantity: ");
                            newQty = sc.nextInt();
                            if (newQty >= 1 && newQty <= 20) break;
                            System.out.println("Quantity must be from (1-20)!");
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                    }


                    quantities[index - 1] = newQty;
                    sc.nextLine(); // clear buffer
                    System.out.println("🔄 Quantity updated!\n");
                }

                // -------- REMOVE ITEM -------- //
                case "2" -> {
                    int removeIndex;
                    while (true) {
                        try {
                            System.out.print("Enter item number to remove: ");
                            removeIndex = sc.nextInt();
                            if (removeIndex >= 1 && removeIndex <= cartCount) break;
                            System.out.println("Invalid item number!");
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                    }

                    for (int i = removeIndex - 1; i < cartCount - 1; i++) {
                        cart[i] = cart[i + 1];
                        quantities[i] = quantities[i + 1];
                    }

                    cart[cartCount - 1] = null;         // updating the last not-null index to null with quantity to 0 and cartCount to cartCount-1
                    quantities[--cartCount] = 0;

                    sc.nextLine(); // clear buffer
                    System.out.println("❌ Item removed!\n");

                    if (cartCount == 0) {
                        System.out.println("🛒 Cart is now empty!");
                        inCart = false;
                    }
                }

                // -------- EXIT CART -------- //
                case "3" -> inCart = false;
            }

        }
    }
}