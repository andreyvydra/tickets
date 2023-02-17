package application;

import console.Console;
import core.CollectionManager;
import core.FileManager;
import core.JSONParser;
import data.Ticket;

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
    private CollectionManager collectionManager;
    private FileManager fileManager;
    private JSONParser jsonParser;
    private ArrayList<String> commandBuffer = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public App(String filename) {
        this.collectionManager = new CollectionManager();
        this.fileManager = new FileManager(filename);
        this.jsonParser = new JSONParser(this.fileManager.getJSONObjectFromFile());
        ArrayList<Ticket> tickets = this.jsonParser.parse();
        this.collectionManager.loadTickets(tickets);
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void execute() {

        Console console = new Console(this);

        while (true) {
            try {
                System.out.print("Введите команду: ");
                String input;
                if (this.commandBuffer.size() == 0) {
                    input = scanner.nextLine().trim();
                } else {
                    input = this.commandBuffer.get(0).trim();
                    System.out.println(input);
                    this.commandBuffer.remove(0);
                }
                console.execute(input);
            } catch (NoSuchElementException e) {
                System.out.println("Некорректный символ!");
                System.exit(1);
            } catch (InvocationTargetException | IllegalAccessException e) {
                System.out.println(e);
            } catch (NullPointerException e) {
                System.out.println("Введена неправильная команда!");
            }

        }

    }

    public FileManager getFileManager() {
        return this.fileManager;
    }

    public void setCommandBuffer(List<String> commands) {
        this.commandBuffer.addAll(commands);
    }
}
