import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class User {
    private String userName;
    private String password;
    private String email;

    public User(boolean isBuyer, String userName, String password, String email) {
        if (isBuyer) {
            userFileHandling(true, "src//buyers.txt", true, false, userName, password, email);
        } else {
            userFileHandling(true, "src//retailers.txt", false, false, userName, password, email);
        }
    }

    public User(boolean isBuyer, String userName, String password, boolean isAdmin) {
        if (isBuyer) {
            userFileHandling(false, "src//buyers.txt", true, false, userName, password, "");
        } else if (isAdmin) {
            userFileHandling(false, "src//admins.txt", false, true, userName, password, "");
        } else {
            userFileHandling(false, "src//retailers.txt", false, false, userName, password, "");
        }
    }

    private void userFileHandling(boolean newUser, String path, boolean isBuyer, boolean isAdmin, String userName, String password, String email) {
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
                        this.userName = s.userName;
                        this.password = s.password;
                        this.email = s.email;
                    } else {
                        this.userName = userName;
                        this.password = password;
                        this.email = email;
                        String filePath = path;
                        String appendData = this.userName + " " + this.password + " " + this.email;
                        FileHandling.appendSameLine(filePath, appendData, false);
                    }
                } else {
                    this.userName = userName;
                    this.password = password;
                    this.email = email;
                    String filePath = path;
                    String appendData = this.userName + " " + this.password + " " + this.email;
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
                        this.userName = userName;
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
                    String password1 = str[1];
                    String name1 = str[0];
                    User s = new User(isBuyer, name1, password1, isAdmin);
                    this.userName = s.userName;
                    this.password = s.password;
                    this.email = s.email;
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }
}