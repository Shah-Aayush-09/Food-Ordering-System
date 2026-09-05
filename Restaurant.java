package src;

//================== Restaurant =================
class Restaurant {
    static String[] available_cuisine = new String[150];        //static because need to be updated during each restaurant entry
    static int count_cuisine;                                   //static because need to be updated during each restaurant entry
    String name;
    FoodItem[] menu = new FoodItem[50];
    String[] available_category = new String[50];
    int count; //Counts no of items
    int count_category;

    Restaurant(String n) {
        name = n;
    }

    //method for displaying cuisine

    void showCuisine() {
        for (int i = 0; i < available_cuisine.length; i++) {    // not used for each loop because of printing i+1
            if (available_cuisine[i] != null) {
                System.out.println((i + 1) + ". " + available_cuisine[i]); //here why i+1 isn't a problem because array is filled from 1 to last so i+1 are printed as continuous no.s
            }
        }
    }

    void addFood(FoodItem f) {
        menu[count++] = f;

        //-------setting available_category--------//

        boolean category_available_status = false;
        for (String string : available_category) {
            if (menu[count - 1].category.equals(string)) { //here available_category[i].equals(menu[count - 1].category) gives error because string can be equal to null but null cant be equal to string
                category_available_status = true;
                break;
            }
        }
        if (!category_available_status) {
            available_category[count_category++] = menu[count - 1].category;
        }

        //-------setting available_cuisine--------//

        boolean cuisine_available_status = false;
        for (String s : available_cuisine) {
            if (menu[count - 1].cuisine.equals(s)) {
                cuisine_available_status = true;
                break;
            }
        }
        if (!cuisine_available_status) {
            available_cuisine[count_cuisine++] = menu[count - 1].cuisine;
        }
    }

    void showMenu() {
        System.out.println("\n----- \uD83C\uDFEA " + name + " Menu -----");
        for (int i = 0; i < count; i++) {
            System.out.print((i + 1) + ". ");
            menu[i].display();
        }
    }

    //method for displaying all available categories

    void showCategories() {
        for (int i = 0; i < available_category.length; i++) {
            if (available_category[i] != null) {
                System.out.println((i + 1) + ". " + available_category[i]);//here why i+1 isn't a problem because array is filled from 1 to last so i+1 are printed as continuous no.s
            }
        }
    }

    void searchCategory(int choice_of_category) {
        int false_cate_count = 1;
        for (FoodItem foodItem : menu) {
            if (foodItem != null) {
                if (foodItem.category.equals(available_category[choice_of_category - 1])) { // here reverse equals isn't possible because null.category = ?
                    System.out.print(false_cate_count++ + ". ");
                    foodItem.display();
                }
            }
        }
    }
}
