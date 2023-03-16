package otus.processor.homework;

import otus.model.Message;
import otus.processor.Processor;

public class ProcessorForChangeFields implements Processor {
    @Override
    public Message process(Message message) {
        String field11 = message.getField11();
        String field12 = message.getField12();
        var builder = message.toBuilder();
        builder.field11(field12);
        builder.field12(field11);
        return builder.build();
    }
}
