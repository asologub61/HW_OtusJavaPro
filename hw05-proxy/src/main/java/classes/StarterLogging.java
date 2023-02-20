package classes;

public class StarterLogging {
    public static  void main(String[] args){
        TestLoggingInterface testLoggingInterface = MyIoc.createTestLogging(new Handler(new TestLogging()));
        testLoggingInterface.calculation(6);
        testLoggingInterface.calculation(6, 8);
    }
}
