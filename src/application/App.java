package application;

import console.Console;
import core.CollectionManager;
import core.FileManager;
import core.JSONParser;
import core.exceptions.CommandWasNotFound;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * App is class which link collection, fileManager, console, parser etc.
 * There is mainloop of program
 *
 * @see FileManager
 * @see Console
 * @see CollectionManager
 * @see JSONParser
 */
public class App {
    private final Scanner scanner = new Scanner(System.in);
    private final DataApp dataApp;

    public App(String filename) {
        dataApp = new DataApp(filename);
    }

    public void execute() throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Console console = new Console(dataApp);

        while (true) {
            try {
                System.out.print("Введите команду: ");
                String input;
                if (dataApp.getCommandBufferSize() == 0) {
                    input = scanner.nextLine().trim();
                } else {
                    input = dataApp.getCommandFromBuffer();
                    System.out.println(input);
                }
                console.execute(input);
            } catch (NoSuchElementException e) {
                System.out.println("Некорректный символ!");
                break;
            } catch (InvocationTargetException | IllegalAccessException e) {
                System.out.println("Файл не доступен для чтения!");
            } catch (CommandWasNotFound e) {
                System.out.println("Введена неправильная команда!");
            }
        }
    }
}
