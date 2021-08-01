package sg.edu.np.madapplcation;

public class UserData {
    public String Username;
    public String Email;


    public UserData(){

    }

    public UserData(String username, String email){
        this.Username = username;
        this.Email = email;
    }

    public String getUsername() {
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
