package sg.edu.np.madapplcation;

public class UserData {
    private static String Username;
    //public String Username;
    public String Email;


    public UserData(){

    }

    public UserData(String username, String email){
        this.Username = username;
        this.Email = email;
    }

    public static String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    /*public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() { return Email; }

    public void setPassword(String password) {
        Email = password;
    }*/
}
