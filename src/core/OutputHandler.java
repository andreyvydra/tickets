package core;

public class OutputHandler {
    public void println(Object... objects) {
        for (Object object : objects) {
            System.out.println(object);
        }
    }

    public void print(Object... objects) {
        for (Object object : objects) {
            System.out.print(object);
        }
    }
}
