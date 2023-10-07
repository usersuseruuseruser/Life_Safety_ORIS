package proj.Dto;

public class UserDto {
    private String name;
    private String email;
    private String selfInfo;

    public UserDto(String name, String email, String selfInfo) {
        this.name = name;
        this.email = email;
        this.selfInfo = selfInfo;
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

    public String getSelfInfo() {
        return selfInfo;
    }

    public void setSelfInfo(String selfInfo) {
        this.selfInfo = selfInfo;
    }
}
