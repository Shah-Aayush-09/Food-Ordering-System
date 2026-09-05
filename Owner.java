package src;

import java.util.InputMismatchException;
import java.util.Scanner;

class Owner {
    static FoodItem[] cart_ = new FoodItem[10];
    static int cartCount_ = 0;
    static int[] quantities_ = new int[10];
    static Restaurant[] updatedRes = setRes();
    Scanner sc = new Scanner(System.in);
    String id = "owner@def";
    String Pass = "Defpass";

    static Restaurant[] setRes() {
        Restaurant[] restaurants = new Restaurant[4];
        restaurants[0] = new Restaurant("Rang Mahal");
        restaurants[1] = new Restaurant("Spicy Villa");
        restaurants[2] = new Restaurant("Italian Corner");
        restaurants[3] = new Restaurant("The Melting Pot");

        restaurants[0].addFood(new FoodItem("Aam Panna", "Beverage", "Indian", 50));
        restaurants[0].addFood(new FoodItem("Masala Buttermilk", "Beverage", "Indian", 30));
        restaurants[0].addFood(new FoodItem("Kaju Paneer Pasanda & Butter Naan", "MainCourse", "Indian", 350));
        restaurants[0].addFood(new FoodItem("Dal-Fry & Jeera-Rice", "MainCourse", "Indian", 200));
        restaurants[0].addFood(new FoodItem("Shrikhand", "Dessert", "Indian", 100));
        restaurants[0].addFood(new FoodItem("Matka Kulfi", "Dessert", "Indian", 40));

        restaurants[1].addFood(new FoodItem("Spring Rolls", "Starter", "Chinese", 80));
        restaurants[1].addFood(new FoodItem("Chilli Paneer", "Starter", "Chinese", 120));
        restaurants[1].addFood(new FoodItem("Manchurian-Noodles", "MainCourse", "Chinese", 180));
        restaurants[1].addFood(new FoodItem("Manchurian-Rice", "MainCourse", "Chinese", 210));
        restaurants[1].addFood(new FoodItem("Rice Pudding", "Dessert", "Chinese", 190));
        restaurants[1].addFood(new FoodItem("Bubble Tea", "Beverage", "Chinese", 160));

        restaurants[2].addFood(new FoodItem("Creamy Garlic Mushrooms", "SideDish", "Italian", 160));
        restaurants[2].addFood(new FoodItem("Garlic Bread", "SideDish", "Italian", 140));
        restaurants[2].addFood(new FoodItem("Pizza", "MainCourse", "Italian", 400));
        restaurants[2].addFood(new FoodItem("Pasta", "MainCourse", "Italian", 350));
        restaurants[2].addFood(new FoodItem("Tiramisu", "Dessert", "Italian", 250));
        restaurants[2].addFood(new FoodItem("Hot Coffee", "Beverage", "Italian", 80));

        restaurants[3].addFood(new FoodItem("Saffron Milk", "Beverage", "Indian", 80));
        restaurants[3].addFood(new FoodItem("Chole Bhature", "MainCourse", "Indian", 230));
        restaurants[3].addFood(new FoodItem("Gajar ka Halwa", "Dessert", "Indian", 130));
        restaurants[3].addFood(new FoodItem("Triple Schezwan Fried Rice", "MainCourse", "Chinese", 200));
        restaurants[3].addFood(new FoodItem("Veg Momos", "Starter", "Chinese", 120));
        restaurants[3].addFood(new FoodItem("Risotto", "MainCourse", "Italian", 220));
        restaurants[3].addFood(new FoodItem("Gazzosa", "Beverage", "Italian", 110));       //gaht-SOH-zah
        return restaurants;
    }

    void display() {
        Restaurant[] restaurants = updatedRes;
        while (true) {
            System.out.println("Choose One Of The Following Actions.\n");
            System.out.println("1. View Order");
            System.out.println("2. Update Price");
            System.out.println("3. Add Item");
            System.out.println("4. Exit");

            System.out.print("\nEnter Index No. Of Action(1-4): ");
            String choice = sc.nextLine().trim();
            while (!choice.matches("^[1-4]$")) {
                System.out.println("Invalid Input!!! Try Again!!!");
                System.out.print("Enter: ");
                choice = sc.nextLine().trim();
            }
            System.out.println();
            switch (choice) {
                case "1" -> {
                    if (cartCount_ == 0) {
                        System.out.println("No Orders Available");
                        break;
                    }
                    double total = 0;
                    System.out.println("\n--- YOUR ORDERS ---");

                    for (int i = 0; i < cartCount_; i++) {
                        double itemTotal = cart_[i].getPrice(quantities_[i]);
                        System.out.println((i + 1) + ". " + cart_[i].name + " | Qty: " + quantities_[i] + " | Rs." + itemTotal);

                        total += itemTotal;
                    }

                    System.out.println("------------------------");
                    System.out.println("Total Amount: Rs." + total);
                }
                case "2" -> {
                    boolean flag_cs_2 = true;
                    while (flag_cs_2) {
                        {

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
                                        flag_cs_2 = false;     //return back to where the method is called.
                                        break;
                                    }

                                    if (rChoice >= 1 && rChoice <= restaurants.length) {
                                        sc.nextLine();
                                        break;
                                    }
                                    System.out.println("Invalid choice!");

                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input!");
                                    sc.next();
                                }
                            }

                            if (!flag_cs_2) continue;

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
                                        flag_cs_2 = false;
                                        break;//return back to where the method is called.
                                    }

                                    if (itemChoice >= 1 && itemChoice <= r.count) {
                                        sc.nextLine();
                                        break;
                                    }
                                    System.out.println("Invalid choice!");

                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input!");
                                    sc.next();
                                }
                            }
                            if (!flag_cs_2) continue;
                            System.out.println("Old Price: " + restaurants[rChoice - 1].menu[itemChoice - 1].price);

                            while (true) {
                                try {
                                    System.out.print("Enter new price: ");

                                    restaurants[rChoice - 1].menu[itemChoice - 1].price = sc.nextDouble();


                                    if (restaurants[rChoice - 1].menu[itemChoice - 1].price <= 0) {
                                        System.out.println("Invalid Price!!!.");
                                        continue;
                                    }
                                    System.out.println("Price Updated!!!");
                                    sc.nextLine();
                                    break;

                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input!");
                                    sc.next();
                                }
                            }
                        }
                    }
                }
                case "3" -> {
                    boolean flag_cs_3 = true;
                    while (flag_cs_3) {
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
                                    flag_cs_3 = false;     //return back to where the method is called.
                                    break;
                                }

                                if (rChoice >= 1 && rChoice <= restaurants.length) {
                                    sc.nextLine();
                                    break;
                                }
                                System.out.println("Invalid choice!");

                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input!");
                                sc.next();
                            }
                        }

                        if (!flag_cs_3) continue;

                        System.out.println();

                        System.out.print("Enter name of new item: ");
                        String name_ = sc.nextLine();
                        System.out.print("Enter category of new item: ");
                        String cat_ = sc.nextLine();
                        System.out.print("Enter cuisine of new item: ");
                        String cui_ = sc.nextLine();
                        double price_;
                        while (true) {
                            try {
                                System.out.print("Enter price of new item: ");

                                price_ = sc.nextDouble();

                                if (price_ <= 0) {
                                    System.out.println("Invalid Price!!!.");
                                    continue;
                                }
                                sc.nextLine();
                                break;

                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input!");
                                sc.next();
                            }
                        }

                        restaurants[rChoice - 1].addFood(new FoodItem(name_, cat_, cui_, price_));
                        updatedRes = restaurants;
                    }
                }
                case "4" -> {
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
                    return;
                }
            }
        }
    }
}
