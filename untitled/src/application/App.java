package application;

import console.Console;
import core.CollectionManager;
import core.FileManager;
import core.JSONParser;
import data.Ticket;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {
    private CollectionManager collectionManager;
    private FileManager fileManager;
    private JSONParser jsonParser;

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

    public void execute() {

        Console console = new Console(this);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Введите команду: ");
                String input = scanner.nextLine();
                console.execute(input);
            } catch (NoSuchElementException e) {
                System.exit(1);
            } catch (Exception e) {
                System.out.println(e);
            }

        }

    }

    public FileManager getFileManager() {
        return this.fileManager;
    }
}
