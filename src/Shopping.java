import java.io.*;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

//guys what if we give id to our customers and business man also

public class Shopping {
    private int password;
    private String userName;
    private String email;

    public Shopping(String username, int password) {
        String filename = "D:\\IdeaProjects\\Testing\\buyers.txt";
        File myObj1 = new File(filename);
        Scanner myReader;
        try {
            myReader = new Scanner(myObj1);
            String data;
            boolean match = false;
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                String[] arrData = data.split(" ");
                String name2 = arrData[0];
                String email2 = arrData[2];
                int number2 = Integer.parseInt(arrData[1]);
                if (username.equals(name2) && password == number2) {
                    this.userName = username;
                    this.password = password;
                    this.email = email2;
                    match = true;
                    break;
                }

            }
            if (!match) {
                System.out.println("Please enter correct login details.");
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter name again and password again");
                String[] str = scanner.nextLine().split(" ");
                int number1 = Integer.parseInt(str[1]);
                String name1 = str[0];
                Shopping s = new Shopping(name1, number1);
                this.userName = s.userName;
                this.password = s.password;
                this.email = s.email;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Shopping(String name, int number, String email) {
        String filename = "D:\\IdeaProjects\\Testing\\buyers.txt";
        File myObj1 = new File(filename);
        Scanner myReader;
        try {
            myReader = new Scanner(myObj1);
            String data;
            boolean match = false;
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                String[] arrData = data.split(" ");
                String name2 = arrData[0];
                if (name.equals(name2)) {
                    match = true;
                    break;
                }

            }
            if (match) {
                System.out.println("User already present with same username(<:>).");
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter details again: ");
                String[] str = scanner.nextLine().split(" ");
                int number1 = Integer.parseInt(str[1]);
                String email1 = str[2];
                String name1 = str[0];
                Shopping s = new Shopping(name1, number1, email1);
                this.userName = s.userName;
                this.password = s.password;
                this.email = s.email;
            } else {
                this.userName = name;
                this.password = number;
                this.email = email;
                String filePath = "D:\\IdeaProjects\\Testing\\buyers.txt";
                String appendData = this.userName + " " + this.password + " " + this.email;
                FileHandling.append(filePath,appendData);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setRating(Product p, int val) {
        int id = p.getId();
        // there is some doubt that what we can do means there are two option that user
        // can set rating without buy the product or can set after buying only

        String notAppendData = "|" + this.userName + "-" + val;
        String appendData = id + "|" + this.userName + "-" + val;
        String path = "D:\\IdeaProjects\\Testing\\ratings.txt";
        FileHandling.appendOrWrite(path,id,appendData,notAppendData);
    }

    public void setReviews(Product p, String str) {
        int id = p.getId();
        // there is some doubt that what we can do means there are two option that user
        // can set rating without buy the product or can set after buying only

        String notAppendData = "|" + this.userName + "-" + str;
        String appendData = id + "|" + this.userName + "-" + str;
        String path = "D:\\IdeaProjects\\Testing\\reviews.txt";
        FileHandling.appendOrWrite(path,id,appendData,notAppendData);
    }
    public void addToCart(Product p,int quantity){
        int qty = p.getQuantity();
        if(quantity>qty){
            System.out.println("There is only "+qty + " Quantity of this product.");
            return;
        }
        int id = p.getId();
//    there is some doubt that what we can do means there are two option that user
//    can set rating without buy the product or can set after buying only
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String notAppendData = "|" + "Pid-"+id+" qty-"+quantity +" "+dtf.format(now);
        String appendData = this.userName + "|" + "Pid-"+id+" qty-"+quantity +" "+dtf.format(now);
        String path = "D:\\IdeaProjects\\Testing\\usersCart.txt";
        FileHandling.appendOrWrite(path,this.userName,appendData,notAppendData);
        int left = qty - quantity;
    }
    public void buy(Product p,int quantity){
        int qty = p.getQuantity();
        if(quantity>qty){
            System.out.println("There is only "+qty + "Quantity of this product.");
            return;
        }
        int id = p.getId();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String notAppendData = "|" + "Prod-id-"+id + " qty-"+quantity +" "+dtf.format(now);
        String appendData = this.userName + "|" + "Prod-id-"+ id + " qty-"+quantity +" "+dtf.format(now);
        String path = "D:\\IdeaProjects\\Testing\\buyedProducts.txt";
        FileHandling.appendOrWrite(path,this.userName,appendData,notAppendData);
        System.out.println("Your ordered is placed");
        int left = qty - quantity;
        p.setQuantity(left);
    }
    public void buyCartProd(){
    String path = "D:\\IdeaProjects\\Testing\\usersCart.txt";
    File myObj = new File(path);
    Scanner myReader1;
        try {
        myReader1 = new Scanner(myObj);
        if(myReader1.hasNextLine()) {
            String data1 = myReader1.nextLine();
            String[] arrData = data1.split("\\|");
            while (myReader1.hasNextLine() && !(arrData[0].equals(this.userName))) {
                data1 = myReader1.nextLine();
                arrData = data1.split("\\|");
            }
            if ((arrData[0].equals(this.userName))) {
                int length = arrData.length;
                for(int i=1;i<length;i++){
                    String[] str = arrData[i].split(" ");
                    int id = Integer.parseInt(str[0].substring(4));
                    int qty = Integer.parseInt(str[1].substring(4));
                    Product p = new Product(id);
                    this.buy(p,qty);
                }
                FileHandling.removeLine(path,this.userName);
            } else {
                System.out.println("Your cart is empty.");
            }
        }
        else {
            System.out.println("Your cart is empty.");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}