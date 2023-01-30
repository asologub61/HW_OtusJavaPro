package Tests;

import Annotations.After;
import Annotations.Before;
import Annotations.Test;

public class TestClass {

    @Before
    public static  void beforeTest(){
        System.out.println("@Before is working");
    }

    @Test
    public void someTest1(){
        System.out.println("someTest1 method with annotation @Test");
        System.out.println("Instance of Tests.TestClass " + hashCode());
        System.out.println("someTest1() - PASSED");
    }
    @Test
    public void someTest2() {
        System.out.println("someTest2 method with annotation @Test");
        System.out.println("Instance of Tests.TestClass " + hashCode());
        System.out.println("someTest2() - PASSED");
    }


    @Test
    public void someTest3() {
        System.out.println("someTest3 method with annotation @Test");
        System.out.println("Instance of Tests.TestClass " + hashCode());
        System.out.println("someTest3() - PASSED");
            }

    @Test
    public void testForException(){
        throw new RuntimeException();
    }

    @After
    public static void afterTest(){
        System.out.println("@Annotations.After is working too");
    }
}
