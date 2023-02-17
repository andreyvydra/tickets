package application;

import console.Console;
import core.CollectionManager;
import core.FileManager;
import core.JSONParser;
import core.exceptions.CoordinateException;
import data.Ticket;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
            } catch (InvocationTargetException | IllegalAccessException | NullPointerException e) {
                System.out.println(e);
            }

        }

    }

    public FileManager getFileManager() {
        return this.fileManager;
    }

    public void setCommandBuffer(String[] commands) {
        this.commandBuffer.addAll(Arrays.asList(commands));
    }
}
