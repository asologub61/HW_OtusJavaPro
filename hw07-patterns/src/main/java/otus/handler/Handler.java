package otus.handler;

import otus.listener.Listener;
import otus.model.Message;

public interface Handler {
    Message handle(Message msg);

    void addListener(Listener listener);
    void removeListener(Listener listener);
}
