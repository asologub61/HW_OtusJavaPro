import java.lang.reflect.InvocationTargetException;

import static tests.TestFramework.testClass;

public class Runner {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException,
            IllegalAccessException, NoSuchMethodException, InstantiationException {
        testClass("tests.TestClass");

    }
}
