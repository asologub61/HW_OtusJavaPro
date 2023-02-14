package classes;

public class StartLogging {
    public static  void main(String[] args){
        TestLoggingInterface testLoggingInterface = MyIoc.createTestLogging();
        testLoggingInterface.calculation(6);
        testLoggingInterface.calculation(6, 8);
    }
}
