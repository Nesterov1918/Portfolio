package api;

public class UserData_GET {

    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;

    public UserData_GET() {
        // Конструктор по умолчанию без аргументов
    }

    public UserData_GET(Integer id, String email, String first_name, String last_name, String avatar) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {

        return email;
    }
    public String getFirst_name() {

        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getAvatar() {return avatar;
    }

}
