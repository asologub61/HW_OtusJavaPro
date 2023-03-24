package otus.processor.homework;

import otus.model.Message;
import otus.processor.Processor;

import java.time.LocalDateTime;

public class ProcessorWithException implements Processor {
    private final DateTimeProvider dateTimeProvider;

    public ProcessorWithException() {
        this.dateTimeProvider = LocalDateTime::now;
    }

    @Override
    public Message process(Message message) {
        if(dateTimeProvider.getDate().getSecond() % 2 == 0){
            throw new RuntimeException("Every even second");
        }
        return message;
    }
}