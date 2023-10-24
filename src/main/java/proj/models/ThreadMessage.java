package proj.models;

import java.sql.Date;

public class ThreadMessage {
    private int threadMessageId;
    private int user_id;
    private String text;
    private Date data;

    public ThreadMessage(int threadMessageId, int user_id, String text, Date data) {
        this.threadMessageId = threadMessageId;
        this.user_id = user_id;
        this.text = text;
        this.data = data;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getThreadMessageId() {
        return threadMessageId;
    }

    public void setThreadMessageId(int threadMessageId) {
        this.threadMessageId = threadMessageId;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
