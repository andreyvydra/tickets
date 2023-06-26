package application;

import console.Console;
import console.commads.AddCommand;
import console.commads.AddIfMaxCommand;
import console.commads.RemoveLowerCommand;
import console.commads.UpdateCommand;
import console.commads.generalCommands.Command;
import core.MessageHandler;
import core.OutputHandler;
import core.exceptions.CommandWasNotFound;
import data.Ticket;
import gui.AppController;
import gui.LoginController;
import gui.RemoveLowerController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import network.UDPClient;
import requests.ShowRequest;
import responses.ShowResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.util.*;

import static core.Globals.CommandNames.*;
import static core.Globals.Network.*;
import static core.Globals.SERVER_PORT;
import static java.lang.Thread.sleep;

/**
 * App is class which link collection, fileManager, console, parser etc.
 * There is mainloop of program
 *
 * @see Console
 */
public class ClientApp {
    private final Queue<String> commandBuffer = new LinkedList<>();

    private final UDPClient udpClient;

    private final Console console;

    private Stage stage;
    private final OutputHandler outputHandler = new MessageHandler();

    private HashMap<String, String> user = new HashMap<>();

    public ClientApp() throws IOException {
        user.put(USERNAME, null);
        user.put(PASSWORD, null);
        udpClient = new UDPClient(InetAddress.getLocalHost(), SERVER_PORT);
        console = new Console(outputHandler, udpClient, commandBuffer);
    }

    public void start(Stage stage) throws Exception {
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(LoginController.class.getResource("/gui/loginScene.fxml"));
        loader.setController(new LoginController(this));
        AnchorPane root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void goToAppScene() {
        Thread thread = new Thread(this::executeScript);
        thread.start();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppController.class.getResource("/gui/appScene.fxml"));
            loader.setController(new AppController(this));
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            showMessage(e.toString());
        }
    }

    public ArrayList<Ticket> loadTickets() {
        try {
            ShowResponse response = (ShowResponse) udpClient.sendRequestAndGetResponse(new ShowRequest(user));
            return response.tickets;
        } catch (IOException e) {
            showMessage("Сервер отдыхает");
        } catch (ClassNotFoundException e) {
            showMessage("Класс не был найден");
        }
        return new ArrayList<>();
    }

    public void showMessage(String objects) {
        outputHandler.println(objects);
    }

    public void setUser(String login, String password) {
        user.put(USERNAME, login);
        user.put(PASSWORD, password);
    }

    public String getUserIsLogged() {
        return user.get(IS_LOGGED);
    }

    public HashMap<String, String> getUser() {
        return user;
    }

    public void addTicket(Ticket ticket) {
        AddCommand addCommand = new AddCommand(outputHandler, udpClient);
        addCommand.setTicket(ticket);
        executeCommand(addCommand, ADD);
    }

    public void addIfMaxTicket(Ticket ticket) {
        AddIfMaxCommand addIfMaxCommand = new AddIfMaxCommand(outputHandler, udpClient);
        addIfMaxCommand.setTicket(ticket);
        executeCommand(addIfMaxCommand, ADD_IF_MAX);
    }

    public void updateTicket(Ticket ticket) {
        UpdateCommand updateCommand = new UpdateCommand(outputHandler, udpClient);
        updateCommand.setTicket(ticket);
        executeCommand(updateCommand, UPDATE + " " + ticket.getId());
    }

    public void removeLowerTicket(Ticket ticket) {
        RemoveLowerCommand removeLowerCommand = new RemoveLowerCommand(outputHandler, udpClient);
        removeLowerCommand.setTicket(ticket);
        executeCommand(removeLowerCommand, REMOVE_LOWER);
    }


    public Console getConsole() {
        return console;
    }


    public void executeCommand(Command command, String commandName) {
        try {
            command.execute(commandName, user);
            console.addToHistory(commandName);
        } catch (IOException e) {
            showMessage("Сервер отдыхает");
        } catch (ClassNotFoundException e) {
            showMessage("Класс не был найден");
        }
    }

    public void executeCommandFromConsole(String command) {
        try {
            console.execute(command, user);
        } catch (CommandWasNotFound e) {
            showMessage(e.toString());
        }
    }

    public void executeScript() {
        while (true) {
            try {
                if (commandBuffer.toArray().length == 0) {
                    synchronized (this) {
                        wait();
                    }
                } else {
                    String input = Objects.requireNonNull(commandBuffer.poll()).trim();
                    console.execute(input, user);
                }
            } catch (NoSuchElementException e) {
                outputHandler.println("До новой встречи!");
                break;
            } catch (InterruptedException e) {
                outputHandler.println("Amogus!");
            } catch (CommandWasNotFound e) {
                outputHandler.println("Введена неправильная команда!");
            } catch (IllegalStateException ignore) {
            }
        }
    }

}
