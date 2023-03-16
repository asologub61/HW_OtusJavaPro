package otus.listener.homework;

import otus.listener.Listener;
import otus.model.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {

    Map<Long, Message> history = new HashMap<>();
    @Override
    public void onUpdated(Message msg) {
        history.put(msg.getId(), msg);
    }

    @Override
    public Optional<Message> findMessageById(long id)
    {
         Optional<Message> opt = Optional.of(history.get(id));
         return  opt;
    }
}
