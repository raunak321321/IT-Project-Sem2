import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class User {
    private String userName;
    private String password;
    private String email;

    public User(boolean isBuyer, String userName, String password, String email) {
        if (isBuyer) {
            Admin.userFileHandling(true, "src//buyers.txt", true, userName, password, email,this);
        } else {
            Admin.userFileHandling(true, "src//retailers.txt", false, userName, password, email,this);
        }
    }

    public User(boolean isBuyer, String userName, String password) {
        if (isBuyer) {
           Admin.userFileHandling(false, "src//buyers.txt", true,  userName, password, "",this);
        }  else {
            Admin.userFileHandling(false, "src//retailers.txt", false, userName, password, "",this);
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
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