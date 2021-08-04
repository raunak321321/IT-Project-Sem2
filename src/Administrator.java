import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Administrator extends User {

    public Administrator(String userName, String password) {
        super(false, userName, password, true);
    }

    public double getProfit() {
        double profit = 0;
        String path4 = "src//earnTillDate.txt";
        File myObj = new File(path4);
        Scanner myReader;
        try {
            myReader = new Scanner(myObj);
            String temp = myReader.nextLine();
            String[] tempArr = temp.split(" ");
            profit = Double.parseDouble(tempArr[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profit;
    }
}
