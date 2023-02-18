package classes;

import annotations.Log;

public class TestLogging implements TestLoggingInterface {
    @Log
    public void calculation(int param){
        System.out.println(param);
    }

    @Log
    public void calculation(int param1, int param2){
        System.out.printf("Param1: " + param1 + " Param2: " + param2);
    }

    public void calculation(int param1, int param2, String param3){
        System.out.println("Method with three parameters");
    }
}

