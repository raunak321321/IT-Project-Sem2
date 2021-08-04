import java.io.*;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Shopping extends User{

    public Shopping(String userName, String password) {
        super(true,userName,password,false);
    }

    public Shopping(String userName, String password, String email) {
        super(true,userName,password,email);
    }

    public void setRating(Product p, int val) {
        int id = p.getId();
        // there is some doubt that what we can do means there are two option that user
        // can set rating without buy the product or can set after buying only

        String notAppendData = "|" + super.getUserName() + "-" + val;
        String appendData = id + "|" + super.getUserName() + "-" + val;
        String path = "D:\\IdeaProjects\\Testing\\ratings.txt";
        FileHandling.appendOrWrite(path, id, appendData, notAppendData);
    }

    public void setReviews(Product p, String str) {
        int id = p.getId();
        // there is some doubt that what we can do means there are two option that user
        // can set rating without buy the product or can set after buying only

        String notAppendData = "|" + super.getUserName() + "-" + str;
        String appendData = id + "|" + super.getUserName() + "-" + str;
        String path = "D:\\IdeaProjects\\Testing\\reviews.txt";
        FileHandling.appendOrWrite(path, id, appendData, notAppendData);
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
        String path = "D:\\IdeaProjects\\Testing\\usersCart.txt";
        FileHandling.appendOrWrite(path, super.getUserName(), appendData, notAppendData);
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
        String path = "D:\\IdeaProjects\\Testing\\buyedProducts.txt";
        FileHandling.appendOrWrite(path, super.getUserName(), appendData, notAppendData);
        System.out.println("Your ordered is placed");
        int left = qty - quantity;
        p.setQuantity(left);

        double amount = 0;
        String path5 = "D:\\IdeaProjects\\Testing\\earnTillDate.txt";
        File myObj1 = new File(path5);
        Scanner myReader1;
        try {
            myReader1 = new Scanner(myObj1);
            String temp = myReader1.nextLine();
            amount = Double.parseDouble(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        double newAmount = amount + p.getPrice()/100f*quantity;
        String data1 = "" + newAmount;
        FileWriter fw1 = new FileWriter(path5, false);
        BufferedWriter bw1 = new BufferedWriter(fw1);
        bw1.write(data1);
        bw1.close();
    }

    public void buyCartProd() {
        String path = "D:\\IdeaProjects\\Testing\\usersCart.txt";
        File myObj = new File(path);
        Scanner myReader1;
        try {
            myReader1 = new Scanner(myObj);
            if (myReader1.hasNextLine()) {
                String data1 = myReader1.nextLine();
                String[] arrData = data1.split("\\|");
                while (myReader1.hasNextLine() && !(arrData[0].equals(super.getUserName()))) {
                    data1 = myReader1.nextLine();
                    arrData = data1.split("\\|");
                }
                if ((arrData[0].equals(super.getUserName()))) {
                    int length = arrData.length;
                    for (int i = 1; i < length; i++) {
                        String[] str = arrData[i].split(" ");
                        int id = Integer.parseInt(str[0].substring(4));
                        int qty = Integer.parseInt(str[1].substring(4));
                        Product p = new Product(id);
                        this.buy(p, qty);
                    }
                    FileHandling.removeLine(path, super.getUserName());
                } else {
                    System.out.println("Your cart is empty.");
                }
            } else {
                System.out.println("Your cart is empty.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}