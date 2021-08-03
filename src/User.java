import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class User {
    private String userName;
    private String password;
    private String email;

    public User(boolean b, String userName, String password, String email) {
        if (b) {
            String filename = "D:\\IdeaProjects\\Testing\\buyers.txt";
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
                        User s = new User(b, name1, password1, email1);
                        this.userName = s.userName;
                        this.password = s.password;
                        this.email = s.email;
                    } else {
                        this.userName = userName;
                        this.password = password;
                        this.email = email;
                        String filePath = "D:\\IdeaProjects\\Testing\\buyers.txt";
                        String appendData = this.userName + " " + this.password + " " + this.email;
                        FileHandling.appendSameLine(filePath, appendData, false);
                    }
                } else {
                    this.userName = userName;
                    this.password = password;
                    this.email = email;
                    String filePath = "D:\\IdeaProjects\\Testing\\buyers.txt";
                    String appendData = this.userName + " " + this.password + " " + this.email;
                    FileHandling.appendSameLine(filePath, appendData, true);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
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
                        String userName2 = arrData[0];
                        if (userName.equals(userName2)) {
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
                        User s = new User(false,name1, password1, email1);
                        this.userName = s.userName;
                        this.password = s.password;
                        this.email = s.email;
                    } else {
                        this.userName = userName;
                        this.password = password;
                        this.email = email;
                        String filePath = "D:\\IdeaProjects\\Testing\\retailers.txt";
                        String appendData = this.userName + " " + this.password + " " + this.email;
                        FileHandling.appendSameLine(filePath, appendData, false);
                    }
                } else {
                    this.userName = userName;
                    this.password = password;
                    this.email = email;
                    String filePath = "D:\\IdeaProjects\\Testing\\retailers.txt";
                    String appendData = this.userName + " " + this.password + " " + this.email;
                    FileHandling.appendSameLine(filePath, appendData, true);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public User(boolean b, String userName, String password) {
        if (b) {
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
                    User s = new User(b, name1, password1);
                    this.userName = s.userName;
                    this.password = s.password;
                    this.email = s.email;
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
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
                    String userName2 = arrData[0];
                    String email2 = arrData[2];
                    String password2 = arrData[1];
                    if (userName.equals(userName2) && password.equals(password2)) {
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
                    User s = new User(false, name1, password1);
                    this.userName = s.userName;
                    this.password = s.password;
                    this.email = s.email;
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

public User(int n, String userName, String password) {

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