package proj.models;

import java.sql.Date;
import java.sql.Timestamp;

public class ThreadMessage {
    private int threadMessageId;
    private String username;
    private String text;
    private Timestamp data;

    public ThreadMessage(int threadMessageId, String username, String text, Timestamp data) {
        this.threadMessageId = threadMessageId;
        this.username = username;
        this.text = text;
        this.data = data;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public int getThreadMessageId() {
        return threadMessageId;
    }

    public void setThreadMessageId(int threadMessageId) {
        this.threadMessageId = threadMessageId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
