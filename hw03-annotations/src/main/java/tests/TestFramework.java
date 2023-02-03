package tests;

import annotations.After;
import annotations.Before;
import annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestFramework {

    TestFramework() {
    }

    public static void testClass(String testClassName) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Class<?> ClassForTest = Class.forName(testClassName);
        Constructor<?> constructor = ClassForTest.getConstructor();

        var before = getMethodWithAnnotation(ClassForTest, Before.class);
        var after = getMethodWithAnnotation(ClassForTest, After.class);
        var tests = getMethodWithAnnotation(ClassForTest, Test.class);

        int passed = 0;
        int failed = 0;

        for (var test : tests) {
            Object instanceTestClass = constructor.newInstance();
            try {
                before.get(0).invoke(instanceTestClass);
                test.invoke(instanceTestClass);
                passed++;
            } catch (Exception e) {
                System.err.println(test.getName() + " FAILED");
                System.err.println(e.getStackTrace());
                failed++;
            } finally {
                after.get(0).invoke(instanceTestClass);
                System.out.println();
            }
        }
        System.out.println("\nTests passed: " + passed + "\nTests failed: " + failed + "\nSum of tests: " + (passed + failed));
    }

    private static List<Method> getMethodWithAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) {
        Method[] methods = clazz.getMethods();
        List<Method> methodWithAnnotation = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotation)) {
                methodWithAnnotation.add(method);
            }
        }
        return methodWithAnnotation;
    }
}


