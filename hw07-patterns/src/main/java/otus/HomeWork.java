package otus;

import otus.handler.ComplexProcessor;
import otus.listener.homework.HistoryListener;
import otus.model.Message;
import otus.model.ObjectForMessage;
import otus.processor.homework.ProcessorForChangeFields;
import otus.processor.homework.ProcessorWithException;

import java.util.List;

public class HomeWork {

    /*
     Реализовать to do:
       1. Добавить поля field11 - field13 (для field13 используйте класс ObjectForMessage)
       2. Сделать процессор, который поменяет местами значения field11 и field12
       3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
             Секунда должна определяьться во время выполнения.
             Тест - важная часть задания
             Обязательно посмотрите пример к паттерну Мементо!
       4. Сделать Listener для ведения истории (подумайте, как сделать, чтобы сообщения не портились)
          Уже есть заготовка - класс HistoryListener, надо сделать его реализацию
          Для него уже есть тест, убедитесь, что тест проходит
     */

    public static void main(String[] args) {
        /*
           по аналогии с Demo.class
           из элеменов "to do" создать new ComplexProcessor и обработать сообщение
         */

        var processors  = List.of(new ProcessorForChangeFields(),
                new ProcessorWithException());

        var complexProcessor = new ComplexProcessor(processors, ex -> System.out.println(ex));
        var historyReader = new HistoryListener();
        complexProcessor.addListener(historyReader);

        var message = new Message.Builder(1L)
                .field1("field1")
                .field2("field2")
                .field3("field3")
                .field6("field6")
                .field10("field10")
                .field11("field11")
                .field12("field12")
                .field13(new ObjectForMessage())
                .build();

        var result = complexProcessor.handle(message);
        System.out.println("result:" + result);
        complexProcessor.removeListener(historyReader);
    }
}
