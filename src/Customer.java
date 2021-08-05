import java.io.*;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Customer extends User{

    public Customer(String userName, String password) {
        super(true,userName,password,false);
    }

    public Customer(String userName, String password, String email) {
        super(true,userName,password,email);
    }

    public void setRating(Product p, int val) {
        int id = p.getId();
        // there is some doubt that what we can do means there are two option that user
        // can set rating without buy the product or can set after buying only

        String notAppendData = "|" + super.getUserName() + "-" + val;
        String appendData = id + "|" + super.getUserName() + "-" + val;
        String path = "src//ratings.txt";
        Admin.appendOrWrite(path, id, appendData, notAppendData);
    }

    public void setReviews(Product p, String str) {
        int id = p.getId();
        // there is some doubt that what we can do means there are two option that user
        // can set rating without buy the product or can set after buying only

        String notAppendData = "|" + super.getUserName() + "-" + str;
        String appendData = id + "|" + super.getUserName() + "-" + str;
        String path = "src//reviews.txt";
        Admin.appendOrWrite(path, id, appendData, notAppendData);
    }

    public void addToCart(Product p, int quantity) {
        int qty = p.getQuantity();
        if (quantity > qty) {
            System.out.println("There is only " + qty + " Quantity of this product.");
            return;
        }
        int id = p.getId();
//    there is some doubt that what we can do means there are two option that user
//    can set rating without buy the product or can set after buying only
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String notAppendData = "|" + "Pid-" + id + " qty-" + quantity + " " + dtf.format(now);
        String appendData = super.getUserName() + "|" + "Pid-" + id + " qty-" + quantity + " " + dtf.format(now);
        String path = "src//usersCart.txt";
        Admin.appendOrWrite(path, super.getUserName(), appendData, notAppendData);
        int left = qty - quantity;
    }

    public void buy(Product p, int quantity) throws IOException {
        int qty = p.getQuantity();
        if (quantity > qty) {
            System.out.println("There is only " + qty + "Quantity of this product.");
            return;
        }
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

        Admin.updateEarnAmount(p.getPrice()/100f*quantity);
    }

    public void buyCartProd() {
        Admin.letBuyCartProd(this);
    }

    public void viewCart(){
        Admin.letViewCart(this);
    }

    public void viewPurchaseHistory(){
        Admin.letViewPurchaseHistory(this);
    }
}