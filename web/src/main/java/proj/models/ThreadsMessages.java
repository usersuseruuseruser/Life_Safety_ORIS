package proj.models;

public class ThreadsMessages {
    private int threadsMessagesId;
    private int threadId;
    private int threadMessageId;

    public ThreadsMessages(int threadsMessagesId, int threadId, int threadMessageId) {
        this.threadsMessagesId = threadsMessagesId;
        this.threadId = threadId;
        this.threadMessageId = threadMessageId;
    }

    public ThreadsMessages() {
    }

    public int getThreadsMessagesId() {
        return threadsMessagesId;
    }

    public void setThreadsMessagesId(int threadsMessagesId) {
        this.threadsMessagesId = threadsMessagesId;
    }

    public int getThreadId() {
        return threadId;
    }

    public void setThreadId(int threadId) {
        this.threadId = threadId;
    }

    public int getThreadMessageId() {
        return threadMessageId;
    }

    public void setThreadMessageId(int threadMessageId) {
        this.threadMessageId = threadMessageId;
    }
}
