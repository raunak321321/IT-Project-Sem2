import java.io.*;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

//guys what if we give id to our customers and business man also

public class Shopping extends User{
//    private String password;
//    private String userName;
//    private String email;

    public Shopping(String userName, String password) {
        super(true,userName,password);

//        String filename = "D:\\IdeaProjects\\Testing\\buyers.txt";
//        File myObj1 = new File(filename);
//        Scanner myReader;
//        try {
//            myReader = new Scanner(myObj1);
//            String data;
//            boolean match = false;
//            while (myReader.hasNextLine()) {
//                data = myReader.nextLine();
//                String[] arrData = data.split(" ");
//                String name2 = arrData[0];
//                String email2 = arrData[2];
//                String password2 = arrData[1];
//                if (userName.equals(name2) && password.equals(password2)) {
//                    this.userName = userName;
//                    this.password = password;
//                    this.email = email2;
//                    match = true;
//                    break;
//                }
//
//            }
//            if (!match) {
//                System.out.println("There are no user with this login details.");
//                Scanner scanner = new Scanner(System.in);
//                System.out.println("Enter name again and password again");
//                String[] str = scanner.nextLine().split(" ");
//                String password1 = str[1];
//                String name1 = str[0];
//                Shopping s = new Shopping(name1, password1);
//                this.userName = s.userName;
//                this.password = s.password;
//                this.email = s.email;
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

    }

    public Shopping(String userName, String password, String email) {
        super(true,userName,password,email);
//        String filename = "D:\\IdeaProjects\\Testing\\buyers.txt";
//        File myObj1 = new File(filename);
//        Scanner myReader;
//        try {
//            myReader = new Scanner(myObj1);
//            String data;
//            boolean match = false;
//            if (myReader.hasNextLine()) {
//                while (myReader.hasNextLine()) {
//                    data = myReader.nextLine();
//                    String[] arrData = data.split(" ");
//                    String userName2 = arrData[0];
//                    if (userName.equals(userName2)) {
//                        match = true;
//                        break;
//                    }
//
//                }
//                if (match) {
//                    System.out.println("User already present with same username(<:>).");
//                    Scanner scanner = new Scanner(System.in);
//                    System.out.print("Enter details again: ");
//                    String[] str = scanner.nextLine().split(" ");
//                    String password1 = str[1];
//                    String email1 = str[2];
//                    String name1 = str[0];
//                    Shopping s = new Shopping(name1, password1, email1);
//                    this.userName = s.userName;
//                    this.password = s.password;
//                    this.email = s.email;
//                } else {
//                    this.userName = userName;
//                    this.password = password;
//                    this.email = email;
//                    String filePath = "D:\\IdeaProjects\\Testing\\buyers.txt";
//                    String appendData = this.userName + " " + this.password + " " + this.email;
//                    FileHandling.appendSameLine(filePath, appendData, false);
//                }
//            } else {
//                this.userName = userName;
//                this.password = password;
//                this.email = email;
//                String filePath = "D:\\IdeaProjects\\Testing\\buyers.txt";
//                String appendData = this.userName + " " + this.password + " " + this.email;
//                FileHandling.appendSameLine(filePath, appendData, true);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
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