import java.lang.reflect.InvocationTargetException;

import static Tests.TestFramework.testClass;

public class Runner {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException,
            IllegalAccessException, NoSuchMethodException, InstantiationException {
        testClass("Tests.TestClass");

    }
}
