package classes;

import annotations.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Handler  implements InvocationHandler {
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