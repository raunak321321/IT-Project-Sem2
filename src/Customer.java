import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Customer extends User {

    //there are two constructors
    public Customer(String userName, String password) {
        super(true, userName, password);
    }

    public Customer(String userName, String password, String email) {
        super(true, userName, password, email);
    }

    //    this method just add the rating of the product given by the user in rating.txt file corresponding to that product id
    public void setRating(Product p, int val) {
        int id = p.getId(); // take id from product class method
        String notAppendData = "|" + super.getUserName() + "-" + val;
        String appendData = id + "|" + super.getUserName() + "-" + val;
        String path = "src//ratings.txt";
        Admin.appendOrWrite(path, id, appendData, notAppendData); // calls a method which is in Admin class
    }

    //    this method just add the reviews of the product given by the user in reviews.txt file corresponding to that product id
    public void setReviews(Product p, String str) {
        int id = p.getId();
        String notAppendData = "|" + super.getUserName() + "-" + str;
        String appendData = id + "|" + super.getUserName() + "-" + str;
        String path = "src//reviews.txt";
        Admin.appendOrWrite(path, id, appendData, notAppendData); // calls a method of Admin class
    }

    //    this method add product to that userCart.txt file corresponding to that user details which is calling this method
    public void addToCart(Product p, int quantity) {
        int qty = p.getQuantity();
        if (quantity > qty) {
            System.out.println("There is only " + qty + " Quantity of this product.");
            return;
        }
        int id = p.getId();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String notAppendData = "|" + "Pid-" + id + " qty-" + quantity + " " + dtf.format(now);
        String appendData = super.getUserName() + "|" + "Pid-" + id + " qty-" + quantity + " " + dtf.format(now);
        String path = "src//usersCart.txt";
        Admin.appendOrWrite(path, super.getUserName(), appendData, notAppendData);
        int left = qty - quantity;
    }

    //    this method gives true if customer is able to buy that product and just add the details in buyedProduct.txt file corresponding to that user who is calling
    public boolean buy(Product p, int quantity) throws IOException {
        int qty = p.getQuantity();
        if (quantity > qty) {
            System.out.println("There is only " + qty + " Quantity of this product.");
            return false;
        } else {
            int id = p.getId();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String notAppendData = "|" + "Prod-id-" + id + " qty-" + quantity + " " + dtf.format(now);
            String appendData = super.getUserName() + "|" + "Prod-id-" + id + " qty-" + quantity + " " + dtf.format(now);
            String path = "src//buyedProducts.txt";
            Admin.appendOrWrite(path, super.getUserName(), appendData, notAppendData);
            System.out.println("Your ordered is placed");
            int left = qty - quantity;
            p.setQuantity(left);

            Admin.updateEarnAmount(p.getPrice() / 100f * quantity);
            return true;
        }

    }

    //    the below three methods just call the Admin class methods
    public void buyCartProd() {
        Admin.letBuyCartProd(this);
    }

    public void viewCart() {
        Admin.letViewCart(this);
    }

    public void viewPurchaseHistory() {
        Admin.letViewPurchaseHistory(this);
    }
}