import pictures.Picture;

import java.io.*;
import java.util.Scanner;

public class Product {
    private String name;
    private int id;
    private int price;
    private String desc;
    private String picName;
    private int quantity;
    private String retailerName;
    private float rating;

    public Product(int n) {
        String filename1 = "src//products.txt";
        File myObj = new File(filename1);
        Scanner myReader1;
        try {
            myReader1 = new Scanner(myObj);
            String data1 = myReader1.nextLine();
            String[] arrData = data1.split("\\|");
            while (myReader1.hasNextLine() && Integer.parseInt(arrData[1]) != n) {
                data1 = myReader1.nextLine();
                arrData = data1.split("\\|");
            }
            if (Integer.parseInt(arrData[1]) == n) {
                this.id = n;
                this.name = arrData[0];
                this.rating = getRating();
                this.desc = arrData[3];
                this.price = Integer.parseInt(arrData[4]);
                this.picName = arrData[2];
                this.quantity = Integer.parseInt(arrData[5]);
                this.retailerName = arrData[6];
            } else {
                System.out.println("Please enter product id name.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public int getId() {
        return this.id;
    }

    public float getRating() {
        float rating = 0;
        int n = this.getId();
        String filename1 = "src//ratings.txt";
        File myObj = new File(filename1);
        Scanner myReader1;
        try {
            myReader1 = new Scanner(myObj);
            String data1 = myReader1.nextLine();
            String[] arrData = data1.split("\\|");
            while (myReader1.hasNextLine() && Integer.parseInt(arrData[0]) != n) {
                data1 = myReader1.nextLine();
                arrData = data1.split("\\|");
            }
            if (Integer.parseInt(arrData[0]) == n) {
                int length = arrData.length;
                for (int i = 1; i < length; i++) {
                    String arrRating[] = arrData[i].split("-");
                    rating = rating + Integer.parseInt(arrRating[1]);
                }
                return (rating / (length - 1));
            } else {
                return 0.0f;
            }
        } catch (FileNotFoundException e) {
            System.out.println("hello");
            e.printStackTrace();
        }
        return rating;
    }

    public void getDesc() {
        System.out.println("Description: " + this.desc);
    }

    public void getImage() {
        Picture pic = new Picture(picName);
        pic.show();
    }

    public int getQuantity() {
        return this.quantity;
    }

    void setQuantity(int qty) {
        int id = this.getId();
        String str = this.name + "|" + this.id + "|" + this.picName + "|" + this.desc + "|" + this.price + "|" + qty + "|" + this.retailerName;
        this.quantity = qty;
        FileHandling.UpdateProductFileLine(id, str);
    }

    public void getReviews() {
        String filename1 = "src//reviews.txt";
        File myObj = new File(filename1);
        Scanner myReader1;
        int n = this.id;
        try {
            myReader1 = new Scanner(myObj);
            String data1 = myReader1.nextLine();
            String[] arrData = data1.split("\\|");
            while (myReader1.hasNextLine() && Integer.parseInt(arrData[0]) != n) {
                data1 = myReader1.nextLine();
                arrData = data1.split("\\|");
            }
            if (Integer.parseInt(arrData[0]) == n) {
                int n1 = arrData.length - 1;
                int i = 1;
                for (; n1 != 0; n1--, i++) {
                    if (i == 1) {
                        System.out.print("The reviews are: ");
                    }
                    String[] arrStrData = arrData[i].split("-");
                    System.out.print(arrStrData[0] + "-->[" + arrStrData[1] + "]  ");
                }
                System.out.println();
            } else {
                System.out.println("There are no reviews corresponding to this product id.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}