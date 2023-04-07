package application;

import console.Console;
import core.CollectionManager;
import core.FileManager;
import core.JSONParser;
import core.OutputHandler;
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
    private final OutputHandler outputHandler = new OutputHandler();

    public App(String filename) {
        dataApp = new DataApp(filename);
    }

    public void execute() {

        Console console = new Console(outputHandler, dataApp);

        while (true) {
            try {
                outputHandler.print("Введите команду: ");
                String input;
                if (dataApp.getCommandBufferSize() == 0) {
                    input = scanner.nextLine().trim();
                } else {
                    input = dataApp.getCommandFromBuffer();
                    outputHandler.println(input);
                }
                console.execute(input);
            } catch (NoSuchElementException e) {
                outputHandler.println("До новой встречи!");
                break;
            } catch (InvocationTargetException | IllegalAccessException e) {
                outputHandler.println("Файл не доступен для чтения!");
            } catch (CommandWasNotFound e) {
                outputHandler.println("Введена неправильная команда!");
            }
        }
    }
}
