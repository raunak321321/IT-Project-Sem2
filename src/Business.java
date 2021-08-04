import java.io.*;
import java.util.Scanner;

public class Business extends User {

    public Business(String userName, String password) {
        super(false, userName, password, false);
    }

    public Business(String userName, String password, String email) {
        super(false, userName, password, email);
    }

    public void setItem(String categoryName, String subCategoryName, String productName, String desc, String imageName, int price, int quantity) throws IOException {
        int id = 0;
        String path4 = "D:\\IdeaProjects\\Testing\\ids.txt";
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

        String notAppendData = ", " + subCategoryName;
        String appendData = categoryName + "|" + subCategoryName;
        String path = "D:\\IdeaProjects\\Testing\\CategoryAndSubCategories.txt";
        FileHandling.appendOrWriteChecker(path, categoryName, appendData, notAppendData, subCategoryName);

        String notAppendData1 = "," + productName + "(" + id + ")";
        String appendData1 = subCategoryName + "|" + productName + "(" + id + ")";
        String path1 = "D:\\IdeaProjects\\Testing\\SubCategoryAndProducts.txt";
        FileHandling.appendOrWrite(path1, subCategoryName, appendData1, notAppendData1);

        String path2 = "D:\\IdeaProjects\\Testing\\products.txt";
        String appendData2 = productName + "|" + id + "|" + imageName + "|" + desc + "|" + price + "|" + quantity + "|" + super.getUserName();
        FileHandling.appendSameLine(path2, appendData2, false);
    }
}