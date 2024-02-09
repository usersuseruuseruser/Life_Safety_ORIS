package proj.Dao;

import proj.models.ThreadMessage;
import proj.models.ThreadsMessages;

import java.util.List;

public interface ThreadsMessagesDao extends Dao<ThreadsMessages> {
    List<ThreadsMessages> getAll(int threadId);
    List<ThreadMessage> getAllMessages(int threadId);
}
