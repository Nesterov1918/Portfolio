package api;

public class RequestReg_POST {

    private String email;
    private String password;

    public RequestReg_POST() {

    }

    public RequestReg_POST(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
