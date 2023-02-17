import application.App;

/**
 * Main class for execution program
 */
public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Передайте имя файла!");
            System.exit(1);
        }
        App app = new App(args[0]);
        app.execute();
    }
}
