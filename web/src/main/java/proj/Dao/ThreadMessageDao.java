package proj.Dao;

import proj.Dao.Dao;
import proj.models.ThreadMessage;

public interface ThreadMessageDao extends Dao<ThreadMessage> {

    int saveAndGetId(ThreadMessage threadMessage);
}
