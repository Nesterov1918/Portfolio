package api;

public class ResponseReg_POST {

    private Integer id;
    private String token;

    public ResponseReg_POST() {

    }

    public ResponseReg_POST(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
