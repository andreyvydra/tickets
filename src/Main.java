import application.App;

import java.lang.reflect.InvocationTargetException;

import static core.Globals.FILENAME_POSITION;

/**
 * Main class for execution program
 */
public class Main {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        if (args.length == 0) {
            System.out.println("Передайте имя файла!");
        } else {
            App app = new App(args[FILENAME_POSITION]);
            app.execute();
        }
    }
}
