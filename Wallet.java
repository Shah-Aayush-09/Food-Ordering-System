package src;

import java.util.InputMismatchException;

class Wallet extends FoodOrderingSystem {

    static double walletBalance = 0;

    void addCreditsToWallet() {

        System.out.println("\n--- ADD CREDITS TO WALLET ---");

        double amount;
        while (true) {
            try {
                System.out.print("Enter amount to add: Rs. ");
                amount = sc.nextDouble();

                if (amount > 0) break;
                System.out.println("Amount must be greater than 0!");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                sc.next();
            }
        }
        sc.nextLine(); // clear buffer

        // ---- PAYMENT OPTIONS ----
        System.out.println("\nChoose Payment Method:");
        System.out.println("1. UPI");
        System.out.println("2. Debit Card");
        System.out.println("3. Credit Card");
        System.out.print("Enter choice (1-3): ");

        String pay_choice = sc.nextLine().trim();
        while (!pay_choice.matches("[1-3]")) {
            System.out.print("Invalid choice! Enter again: ");
            pay_choice = sc.nextLine().trim();
        }

        String method = switch (pay_choice) {
            case "1" -> "UPI";
            case "2" -> "Debit Card";
            default -> "Credit Card";
        };

        // ---- CONFIRMATION ----
        System.out.print("Confirm adding Rs." + amount + " using " + method + " (Y/N): ");
        String confirm = sc.nextLine().trim().toUpperCase();

        while (!confirm.matches("[YN]")) {
            System.out.print("Invalid input! Enter Y or N: ");
            confirm = sc.nextLine().trim().toUpperCase();
        }

        if (confirm.equals("N")) {
            System.out.println("❌ Wallet top-up cancelled.\n");
            return;
        }

        // ---- SIMULATED PAYMENT DETAILS ----
        if (pay_choice.equals("1")) {
            if (!payment_methods()) {//--------------------------------inheritance + overloading(compile time polymorphism)
                System.out.println("Wallet Credit Failed!!!");
                return;
            }
        } else {
            if (!payment_methods(method)) {//--------------------------------inheritance + overloading(compile time polymorphism)
                System.out.println("Wallet Credit Failed!!!");
                return;
            }
        }

        // ---- CREDIT WALLET ----
        walletBalance += amount;
        System.out.println("\n✅ Wallet credited successfully!");
        System.out.println("Current Wallet Balance: Rs." + walletBalance + "\n");
    }

    boolean useWallet(double amount) {

        if (walletBalance < amount) {
            System.out.println("❌ Insufficient wallet balance!");
            System.out.println("Wallet Balance: Rs." + walletBalance);
            return false;
        }

        walletBalance -= amount;
        System.out.println("✅ Payment successful using Wallet!");
        System.out.println("Remaining Balance: Rs." + walletBalance + "\n");
        return true;
    }
}
