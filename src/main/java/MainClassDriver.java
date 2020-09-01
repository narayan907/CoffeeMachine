import org.testng.TestNG;

public class MainClassDriver {

    public static void main(String args[])
    {
        TestNG test = new TestNG();
        test.setTestClasses(new Class[] { JavaTestngTestCases.class });
        test.run();
    }

}
