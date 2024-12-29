package in.reqres.user;

public class UserCredentials {
    private String email;
    private String password;

    public UserCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserCredentials() {
    }

    public UserCredentials withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserCredentials withPassword(String password) {
        this.password = password;
        return this;
    }
}
