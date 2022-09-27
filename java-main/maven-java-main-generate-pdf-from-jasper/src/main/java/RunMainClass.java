import application.Example;
import application.ExampleImpl;

public class RunMainClass {
    public static void main(String[] args) {
        Example example = new ExampleImpl();
        example.generateExampleJasperToPdfDynamic();
    }
}
