package proj.models;

public class Thread {
    private int forum_thread_id;
    private String title;
    private String description;
    private String image_url;

    public Thread(int forum_thread_id, String title, String description, String image_url) {
        this.forum_thread_id = forum_thread_id;
        this.title = title;
        this.description = description;
        this.image_url = image_url;
    }

    public int getForum_thread_id() {
        return forum_thread_id;
    }

    public void setForum_thread_id(int forum_thread_id) {
        this.forum_thread_id = forum_thread_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
