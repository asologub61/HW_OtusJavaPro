package classes;

import annotations.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class MyIoc {

    static TestLoggingInterface createTestLogging() {
        InvocationHandler handler = new Handler(new TestLogging());
        return (TestLoggingInterface) Proxy.newProxyInstance(MyIoc.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class Handler implements InvocationHandler {
        private final TestLoggingInterface testLoggingInterface;
        private final List<Method> methodsWithAnnotation = new ArrayList<>();

        public Handler(TestLoggingInterface original) {
            this.testLoggingInterface = original;
            for (Method method : original.getClass().getMethods()) {
                if (method.isAnnotationPresent(Log.class)) {
                    methodsWithAnnotation.add(getMethodFromInterface(method));
                }
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (methodsWithAnnotation.contains(method)) {
                System.out.println("Executing method: " + method.getName() + "\n Param: " + Arrays.toString(args));
            }
            return method.invoke(testLoggingInterface, args);
        }

        private Method getMethodFromInterface(Method method) {
            try {
                return TestLoggingInterface.class.getDeclaredMethod(method.getName(), method.getParameterTypes());
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

