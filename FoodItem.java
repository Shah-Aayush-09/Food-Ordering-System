package src;

/* ================= FOOD ITEM ================= */
class FoodItem {
    String name, category, cuisine;
    double price;

    FoodItem(String n, String cat, String cui, double p) {
        name = n;
        category = cat;
        cuisine = cui;
        price = p;
    }

    double getPrice(int q) {
        return price * q;
    }

    void display() {
        System.out.println(name + " | " + category + " | " + cuisine + " | Rs." + price);
    }
}