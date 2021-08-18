import java.io.IOException;
import java.util.Scanner;

public class TestMain {

//    public static void admin(Administrator a){
//        System.out.println("Profit you earn till date is: "+a.getProfit());
//    }

    public static void buy(Customer s) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ENTER THE CATEGORY OF YOUR CHOICE IN THE LIST BELOW");
        Object.getCategory();
        String category = scanner.nextLine();
        Object.getSubCategory(category);
        System.out.println("ENTER THE SUB-CATEGORY OF YOUR CHOICE FROM THE LIST GIVEN ABOVE");
        String subcategory = scanner.nextLine();
        Object.getProducts(subcategory);
        System.out.println("NOW SELECT THE PRODUCT OF YOUR CHOICE FROM THE LIST GIVEN ABOVE(type the product number)");
        int num = scanner.nextInt();
        Product p = new Product(num);
        System.out.println("IMAGE WILL BE VISIBLE IN NEW DIALOG BOX");
        p.getImage();
//        System.out.println("DESCRIPTION OF THE PRODUCT:");
        p.getDesc();
        System.out.print("PRICE OF THE PRODUCT:");
        int price = p.getPrice();
        System.out.println(price);
        System.out.println("DO YOU WISH TO SEE THE REVIEWS AND RATING OF THIS PRODUCT(type true or false)");
        boolean input2 = scanner.nextBoolean();
        if (input2) {
            System.out.print("RATING:");
            float rating = p.getRating();
            System.out.println(rating);
            System.out.println("REVIEWS:");
            p.getReviews();
        }
        System.out.println("ENTER 1: TO ADD THE PRODUCT TO THE CART");
        System.out.println("ENTER 2:TO BUY THE PRODUCT");
        System.out.println("ENTER 3:TO SELECT ANOTHER PRODUCT");
        int num2 = scanner.nextInt();
        switch (num2) {
            case 1 -> {
                System.out.println("ENTER THE QUANTITY OF THE PRODUCT YOU WISH TO BUY");
                int q = scanner.nextInt();
                s.addToCart(p, q);
                System.out.println("DO YOU WANT TO SELECT ANOTHER PRODUCTS");
                boolean input4 = scanner.nextBoolean();
                if (input4)
                    buy(s);
            }
            case 2 -> {
                System.out.println("ENTER THE QUANTITY OF THE PRODUCT YOU WISH TO BUY");
                int q1 = scanner.nextInt();
                boolean isBuyable = s.buy(p, q1);
                if(isBuyable){
                    System.out.println("DO YOU WANT TO GIVE RATING FOR PRODUCT YOU HAVE PURCHASED");
                    boolean input6 = scanner.nextBoolean();
                    if (input6) {
                        System.out.println("ENTER THE RATING OUT OF 5");
                        int r = scanner.nextInt();
                        s.setRating(p, r);
                    }
                    System.out.println("DO YOU WANT TO GIVE REVIEW TO THE PRODUCT YOU HAVE PURCHASED");
                    boolean input7 = scanner.nextBoolean();
                    if (input7) {
                        System.out.println("TYPE THE REVIEW");
                        scanner.nextLine();
                        String rev = scanner.nextLine();
                        s.setReviews(p, rev);
                    }
                }

                System.out.println("DO YOU WANT TO SELECT ANOTHER PRODUCTS");
                boolean input5 = scanner.nextBoolean();
                if (input5)
                    buy(s);
            }
            case 3 -> buy(s);
        }
        System.out.println("DO YOU WANT TO BUY THE PRODUCTS IN YOUR CART");
        boolean input3 = scanner.nextBoolean();
        if (input3) {
            s.buyCartProd();
        }
    }

    public static void sell(Retailer b) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String category, subcategory, productName, description, image;
        int price, quantity;
        System.out.println("OK SO AS YOU WANT TO SELL YOUR PRODUCT THEN PLEASE GIVE THE FOLLOWING INFO:)");
        System.out.println("ENTER THE CATEGORY ");
        category = scanner.nextLine();
        System.out.println("ENTER THE SUB-CATEGORY");
        subcategory = scanner.nextLine();
        System.out.println("ENTER THE PRODUCT NAME");
        productName = scanner.nextLine();
        System.out.println("ENTER THE DESCRIPTION OF THE PRODUCT");
        description = scanner.nextLine();
        System.out.println("ENTER THE IMAGE NAME");
        image = scanner.nextLine();
        System.out.println("ENTER THE PRICE");
        price = scanner.nextInt();
        System.out.println("ENTER THE TOTAL QUANTITY OF PRODUCT");
        quantity = scanner.nextInt();
        b.sellItem(category, subcategory, productName, description, image, price, quantity);
        System.out.println("DO YOU WANT TO SELL MORE PRODUCTS");
        boolean input7 = scanner.nextBoolean();
        if (input7)
            sell(b);

    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean user;
        Customer s;
//        System.out.println("You are admin!(type true if yes and false otherwise)");
//        boolean isAdmin = scanner.nextBoolean();
//        if(isAdmin){
//            Administrator a;
//            System.out.println("ENTER USER-NAME(without space)");
//            String name = scanner.next();
//            System.out.println("ENTER PASSWORD");
//            String password = scanner.next();
//            a = new Administrator(name, password);
//            admin(a);
//        }
        System.out.println("WELCOME TO OUR E COMMERCE WEBSITE");
        System.out.println("DO YOU WANT TO BUY OR SELL PRODUCTS(type true to buy and false to sell)");
        boolean input = scanner.nextBoolean();
        if (input) {
            System.out.println("ARE YOU A NEW USER(type true or false)");
            user = scanner.nextBoolean();
            if (user) {
                System.out.println("ENTER  USER-NAME(without space)");
                String name = scanner.next();
                System.out.println("ENTER PASSWORD");
                String password = scanner.next();
                System.out.println("ENTER EMAIL");
                String email = scanner.next();
                s = new Customer(name, password, email);
            } else {
                System.out.println("ENTER USER-NAME(without space)");
                String name = scanner.next();
                System.out.println("ENTER PASSWORD");
                String password = scanner.next();
                s = new Customer(name, password);
            }
            buy(s);
        } else {
            Retailer b;
            System.out.println("ARE YOU A NEW USER(type true or false)");
            boolean user1 = scanner.nextBoolean();
            if (user1) {
                System.out.println("ENTER  USER-NAME(without space)");
                String name = scanner.next();
                System.out.println("ENTER PASSWORD");
                String password = scanner.next();
                System.out.println("ENTER EMAIL");
                String email = scanner.next();
                b = new Retailer(name, password, email);
            } else {
                System.out.println("ENTER USER-NAME(without space)");
                String name = scanner.next();
                System.out.println("ENTER PASSWORD");
                String password = scanner.next();
                b = new Retailer(name, password);
            }
            sell(b);
        }
    }
}