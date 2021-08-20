public class User {
    private String userName;
    private String password;
    private String email;

    //    two constructors for new and old user
    public User(boolean isBuyer, String userName, String password, String email) {
        if (isBuyer) {
            Admin.userFileHandling(true, "src//buyers.txt", true, userName, password, email, this);
        } else {
            Admin.userFileHandling(true, "src//retailers.txt", false, userName, password, email, this);
        }
    }

    public User(boolean isBuyer, String userName, String password) {
        if (isBuyer) {
            Admin.userFileHandling(false, "src//buyers.txt", true, userName, password, "", this);
        } else {
            Admin.userFileHandling(false, "src//retailers.txt", false, userName, password, "", this);
        }
    }

    //    below methods are setter or getter methods
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}