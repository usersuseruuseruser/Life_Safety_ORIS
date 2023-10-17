package proj.Dto;

public class UserDto {
    private int id;
    private String name;
    private String email;
    private String selfInfo;
    private String profilePictureUrl;

    public UserDto(int id,String name, String email, String selfInfo,String profilePictureUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.selfInfo = selfInfo;
        this.profilePictureUrl = profilePictureUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public UserDto() {}

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
