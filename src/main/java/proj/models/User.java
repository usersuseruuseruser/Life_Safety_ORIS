package proj.models;

public class User {
    private int id;
    private String name;
    private String email;
    private String login;
    private String password;
    private String selfInfo;

    public User(int id) {
        this.id = id;
    }

    public User(String name, String email, String login, String password, String selfInfo) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.selfInfo = selfInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSelfInfo() {
        return selfInfo;
    }

    public void setSelfInfo(String selfInfo) {
        this.selfInfo = selfInfo;
    }
}
