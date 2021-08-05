import java.io.*;
import java.util.Scanner;

public class Retailer extends User {

    public Retailer(String userName, String password) {
        super(false, userName, password, false);
    }

    public Retailer(String userName, String password, String email) {
        super(false, userName, password, email);
    }

    public void sellItem(String categoryName, String subCategoryName, String productName, String desc, String imageName, int price, int quantity) throws IOException {
        int id = Admin.letUpdateId();


        String notAppendData = ", " + subCategoryName;
        String appendData = categoryName + "|" + subCategoryName;
        String path = "src//CategoryAndSubCategories.txt";
        Admin.appendOrWriteChecker(path, categoryName, appendData, notAppendData, subCategoryName);

        String notAppendData1 = "," + productName + "(" + id + ")";
        String appendData1 = subCategoryName + "|" + productName + "(" + id + ")";
        String path1 = "src//SubCategoryAndProducts.txt";
        Admin.appendOrWrite(path1, subCategoryName, appendData1, notAppendData1);

        String path2 = "src//products.txt";
        String appendData2 = productName + "|" + id + "|" + imageName + "|" + desc + "|" + price + "|" + quantity + "|" + super.getUserName();
        Admin.appendSameLine(path2, appendData2, false);
    }
}