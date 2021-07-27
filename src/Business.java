import java.io.*;
import java.util.Scanner;

public class Business {
    private int password;
    private String userName;
    private String email;

    public Business(String username, int password) {
        String filename = "D:\\IdeaProjects\\Testing\\retailers.txt";
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
                System.out.println("There are no user with this login details.");
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter name again and password again");
                String[] str = scanner.nextLine().split(" ");
                int number1 = Integer.parseInt(str[1]);
                String name1 = str[0];
                Business s = new Business(name1, number1);
                this.userName = s.userName;
                this.password = s.password;
                this.email = s.email;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Business(String name, int number, String email) {
        String filename = "D:\\IdeaProjects\\Testing\\retailers.txt";
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
                    Business s = new Business(name1, number1, email1);
                    this.userName = s.userName;
                    this.password = s.password;
                    this.email = s.email;
                } else {
                    this.userName = name;
                    this.password = number;
                    this.email = email;
                    String filePath = "D:\\IdeaProjects\\Testing\\retailers.txt";
                    String appendData = this.userName + " " + this.password + " " + this.email;
                    FileHandling.appendSameLine(filePath, appendData, false);
                }
            } else {
                this.userName = name;
                this.password = number;
                this.email = email;
                String filePath = "D:\\IdeaProjects\\Testing\\retailers.txt";
                String appendData = this.userName + " " + this.password + " " + this.email;
                FileHandling.appendSameLine(filePath, appendData, true);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setItem(String categoryName, String subCategoryName, String productName, String desc, String imageName, int price, int quantity) throws IOException {
        int id = 0;
        String path4 = "D:\\IdeaProjects\\Testing\\id.txt";
        File myObj = new File(path4);
        Scanner myReader;
        try {
            myReader = new Scanner(myObj);
            String temp = myReader.nextLine();
            id = Integer.parseInt(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int newId = id + 1;
        String data = "" + newId;
        FileWriter fw = new FileWriter(path4, false);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(data);
        bw.close();

        String notAppendData = ", " + subCategoryName;
        String appendData = categoryName + "|" + subCategoryName;
        String path = "D:\\IdeaProjects\\Testing\\CategoryAndSubCategories.txt";
        FileHandling.appendOrWriteChecker(path, categoryName, appendData, notAppendData, subCategoryName);

        String notAppendData1 = "," + productName + "(" + id + ")";
        String appendData1 = subCategoryName + "|" + productName + "(" + id + ")";
        String path1 = "D:\\IdeaProjects\\Testing\\SubCategoryAndProducts.txt";
        FileHandling.appendOrWrite(path1, subCategoryName, appendData1, notAppendData1);

        String path2 = "D:\\IdeaProjects\\Testing\\products.txt";
        String appendData2 = productName + "|" + id + "|" + imageName + "|" + desc + "|" + price + "|" + quantity+"|"+this.userName;
        FileHandling.appendSameLine(path2, appendData2, false);
    }
}