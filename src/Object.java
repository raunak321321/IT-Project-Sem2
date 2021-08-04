import java.io.*;
import java.util.Scanner;

public class Object {
    public static void getCategory() {
        String filename1 = "src//CategoryAndSubCategories.txt";
        File myObj = new File(filename1);
        Scanner myReader1;
        try {
            myReader1 = new Scanner(myObj);
            String data1;
            while (myReader1.hasNextLine()) {
                data1 = myReader1.nextLine();
                String[] arrData = data1.split("\\|");
                System.out.println(arrData[0]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void getSubCategory(String Category) {
        String filename1 = "src//CategoryAndSubCategories.txt";
        File myObj = new File(filename1);
        Scanner myReader1;
        try {
            myReader1 = new Scanner(myObj);
            String data1 = myReader1.nextLine();
            String[] arrData = data1.split("\\|");
            while (!arrData[0].equals(Category)) {
                data1 = myReader1.nextLine();
                arrData = data1.split("\\|");
            }
            if (arrData[0].equals(Category)) {
                System.out.println(Category + "->" + arrData[1]);
            } else {
                System.out.println("Please enter correct Category name.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void getProducts(String subCategory) {
        String filename1 = "src//SubCategoryAndProducts.txt";
        File myObj = new File(filename1);
        Scanner myReader1;
        try {
            myReader1 = new Scanner(myObj);
            String data1 = myReader1.nextLine();
            String[] arrData = data1.split("\\|");
            while (!arrData[0].equals(subCategory)) {
                data1 = myReader1.nextLine();
                arrData = data1.split("\\|");
            }
            if (arrData[0].equals(subCategory)) {
                System.out.println(subCategory + "->" + arrData[1]);
            } else {
                System.out.println("Please enter correct subCategory name.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}