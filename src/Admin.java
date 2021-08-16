import java.io.*;
import java.util.Scanner;

public class Admin implements FileHandling{
    public static void userFileHandling(boolean newUser, String path, boolean isBuyer, String userName, String password, String email,User person) {
        if (newUser) {
            String filename = path;
            File myObj1 = new File(filename);
            Scanner myReader;
            try {
                myReader = new Scanner(myObj1);
                String data;
                boolean match = false;
                if (myReader.hasNextLine()) {
                    while (myReader.hasNextLine()) {
                        data = myReader.nextLine();
                        String[] arrData = data.split(" ");
                        String name2 = arrData[0];
                        if (userName.equals(name2)) {
                            match = true;
                            break;
                        }

                    }
                    if (match) {
                        System.out.println("User already present with same username(<:>).");
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("Enter details again: ");
                        String[] str = scanner.nextLine().split(" ");
                        String password1 = str[1];
                        String email1 = str[2];
                        String name1 = str[0];
                        User s = new User(isBuyer, name1, password1, email1);
                        person.setUserName(s.getUserName());
                        person.setPassword(s.getPassword());
                        person.setEmail(s.getEmail());
                    } else {
                        person.setUserName(userName);
                        person.setPassword(password);
                        person.setEmail(email);
                        String filePath = path;
                        String appendData = person.getUserName() + " " + person.getPassword() + " " + person.getEmail();
                        FileHandling.appendSameLine(filePath, appendData, false);
                    }
                } else {
                    person.setUserName(userName);
                    person.setPassword(password);
                    person.setEmail(email);
                    String filePath = path;
                    String appendData = person.getUserName() + " " + person.getPassword() + " " + person.getEmail();
                    FileHandling.appendSameLine(filePath, appendData, true);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            String filename = path;
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
                    String password2 = arrData[1];
                    if (userName.equals(name2) && password.equals(password2)) {
                        person.setUserName(userName);
                        person.setPassword(password);
                        person.setEmail(email2);
                        match = true;
                        break;
                    }

                }
                if (!match) {
                    System.out.println("There are no user with this login details.");
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Enter name again and password again");
                    String[] str = scanner.nextLine().split(" ");
                    String password1 = str[1];
                    String name1 = str[0];
                    User s = new User(isBuyer, name1, password1);
                    person.setUserName(s.getUserName());
                    person.setPassword(s.getPassword());
                    person.setEmail(s.getEmail());
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void appendOrWrite(String path,int id,String appendData,String notAppendData){
        FileHandling.appendOrWrite(path, id, appendData, notAppendData);
    }
    public static void appendOrWrite(String path, String userName, String appendData, String notAppendData){
        FileHandling.appendOrWrite(path,userName,appendData,notAppendData);
    }
    public static void removeLine(String path,String userName){
        FileHandling.removeLine(path, userName);
    }
    public void updateProductFileLine(int id, String str){
        String path = "src//products.txt";
        File myObj = new File(path);
        Scanner myReader;
        try {
            myReader = new Scanner(myObj);
            String data = "";
            while (myReader.hasNextLine()) {
                String temp = "";
                temp = myReader.nextLine();
                String[] arrData = temp.split("\\|");
                if (Integer.parseInt(arrData[1]) == id) {
                    data = data + str;
                } else {
                    data = data + temp;
                }
                if (myReader.hasNextLine()) {
                    data = data + "\n";
                }
            }
            FileWriter fw = new FileWriter(path, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void appendOrWriteChecker(String path, String categoryName, String appendData, String notAppendData, String subCategoryName){
        FileHandling .appendOrWriteChecker(path, categoryName, appendData, notAppendData, subCategoryName);
    }
    public static void appendSameLine(String path2, String appendData2, boolean isSameLine){
        FileHandling.appendSameLine(path2, appendData2, isSameLine);
    }
    public static void updateEarnAmount(float amountToBeAdd) throws IOException {
        double amount = 0;
        String path5 = "src//earnTillDate.txt";
        File myObj1 = new File(path5);
        Scanner myReader1;
        try {
            myReader1 = new Scanner(myObj1);
            String temp = myReader1.nextLine();
            amount = Double.parseDouble(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        double newAmount = amount + amountToBeAdd;
        String data1 = "" + newAmount;
        FileWriter fw1 = new FileWriter(path5, false);
        BufferedWriter bw1 = new BufferedWriter(fw1);
        bw1.write(data1);
        bw1.close();
    }

    public static void letBuyCartProd(Customer customer){
        String path = "src//usersCart.txt";
        File myObj = new File(path);
        Scanner myReader1;
        try {
            myReader1 = new Scanner(myObj);
            if (myReader1.hasNextLine()) {
                String data1 = myReader1.nextLine();
                String[] arrData = data1.split("\\|");
                while (myReader1.hasNextLine() && !(arrData[0].equals(customer.getUserName()))) {
                    data1 = myReader1.nextLine();
                    arrData = data1.split("\\|");
                }
                if ((arrData[0].equals(customer.getUserName()))) {
                    int length = arrData.length;
                    for (int i = 1; i < length; i++) {
                        String[] str = arrData[i].split(" ");
                        int id = Integer.parseInt(str[0].substring(4));
                        int qty = Integer.parseInt(str[1].substring(4));
                        Product p = new Product(id);
                        customer.buy(p, qty);
                    }
                    Admin.removeLine(path, customer.getUserName());
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

    public static void letViewCart(Customer customer){
        String filename1 = "src//usersCart.txt";
        File myObj = new File(filename1);
        Scanner myReader1;
        String userName = customer.getUserName();
        try {
            myReader1 = new Scanner(myObj);
            if (myReader1.hasNextLine()){
                String data1 = myReader1.nextLine();
                String[] arrData = data1.split("\\|");
                while (myReader1.hasNextLine() && arrData[0].equals(userName)) {
                    data1 = myReader1.nextLine();
                    arrData = data1.split("\\|");
                }
                if (arrData[0].equals(userName)) {
                    int n1 = arrData.length - 1;
                    int i = 1;
                    for (; n1 != 0; n1--, i++) {
                        if (i == 1) {
                            System.out.print("The cart products are: ");
                        }
                        String[] str = arrData[i].split(" ");
                        int id = Integer.parseInt(str[0].substring(4));
                        int qty = Integer.parseInt(str[1].substring(4));
                        String date = str[2];
                        Product p = new Product(id);
                        System.out.println(p.getName() + "-->id: " + id + " , Qty: "+qty + "at " + date);
                    }
                    System.out.println();
                } else {
                    System.out.println("Your cart is empty.");
                }
            }
            else{
                System.out.println("Your cart is empty");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void letViewPurchaseHistory(Customer customer){
        String filename1 = "src//buyedProducts.txt";
        File myObj = new File(filename1);
        Scanner myReader1;
        String userName = customer.getUserName();
        try {
            myReader1 = new Scanner(myObj);
            if (myReader1.hasNextLine()){
                String data1 = myReader1.nextLine();
                String[] arrData = data1.split("\\|");
                while (myReader1.hasNextLine() && arrData[0].equals(userName)) {
                    data1 = myReader1.nextLine();
                    arrData = data1.split("\\|");
                }
                if (arrData[0].equals(userName)) {
                    int n1 = arrData.length - 1;
                    int i = 1;
                    for (; n1 != 0; n1--, i++) {
                        if (i == 1) {
                            System.out.print("Your History: ");
                        }
                        String[] str = arrData[i].split(" ");
                        int id = Integer.parseInt(str[0].substring(5));
                        int qty = Integer.parseInt(str[1].substring(4));
                        String date = str[2];
                        Product p = new Product(id);
                        System.out.println(p.getName() + "-->id: " + id + " , Qty: "+qty + "at " + date);
                    }
                    System.out.println();
                } else {
                    System.out.println("You haven't buy any product.");
                }
            }
            else{
                System.out.println("You haven't buy any product.");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void letGetCategory(){
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

    public static void letGetSubCategory(String category){
        String filename1 = "src//CategoryAndSubCategories.txt";
        File myObj = new File(filename1);
        Scanner myReader1;
        try {
            myReader1 = new Scanner(myObj);
            String data1 = myReader1.nextLine();
            String[] arrData = data1.split("\\|");
            while (!arrData[0].equals(category)) {
                data1 = myReader1.nextLine();
                arrData = data1.split("\\|");
            }
            if (arrData[0].equals(category)) {
                System.out.println(category + "->" + arrData[1]);
            } else {
                System.out.println("Please enter correct Category name.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void letGetProducts(String subCategory){
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

    public static void allAboutProducts(int id,Product product){
        String filename1 = "src//products.txt";
        File myObj = new File(filename1);
        Scanner myReader1;
        try {
            myReader1 = new Scanner(myObj);
            String data1 = myReader1.nextLine();
            String[] arrData = data1.split("\\|");
            while (myReader1.hasNextLine() && Integer.parseInt(arrData[1]) != id) {
                data1 = myReader1.nextLine();
                arrData = data1.split("\\|");
            }
            if (Integer.parseInt(arrData[1]) == id) {
                product.setId(id);
                product.setName(arrData[0]);
                product.setRating(product.getRating());
                product.setDesc(arrData[3]);
                product.setPrice(Integer.parseInt(arrData[4]));
                product.setPicName(arrData[2]);
                product.setRetailerName(arrData[6]);
                product.setQuantity(Integer.parseInt(arrData[5]));
            } else {
                System.out.println("Please enter correct product id name.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static float letGetRating(Product product){
        float rating = 0;
        int n = product.getId();
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
//            System.out.println("hello");
            e.printStackTrace();
        }
        return rating;
    }

    public static void letGetReviews(Product product){
        String filename1 = "src//reviews.txt";
        File myObj = new File(filename1);
        Scanner myReader1;
        int n = product.getId();
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
    public static int letUpdateId() throws IOException {
        int id = 0;
        String path4 = "src//ids.txt";
        File myObj = new File(path4);
        Scanner myReader;
        try {
            myReader = new Scanner(myObj);
            String temp = myReader.nextLine();
            String[] tempArr = temp.split(" ");
            id = Integer.parseInt(tempArr[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int newId = id + 1;
        String data = "" + newId;
        FileWriter fw = new FileWriter(path4, false);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(data);
        bw.close();
        return id;
    }
}
