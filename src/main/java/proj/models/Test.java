package proj.models;

public class Test {
    private int testId;
    private String title;
    private String imageUrl;

    public Test(int testId, String title, String imageUrl) {
        this.testId = testId;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
