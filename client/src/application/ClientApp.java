package application;

import console.Console;
import core.OutputHandler;
import core.exceptions.CommandWasNotFound;
import network.UDPClient;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.util.*;

import static core.Globals.SERVER_PORT;

/**
 * App is class which link collection, fileManager, console, parser etc.
 * There is mainloop of program
 *
 * @see Console
 */
public class ClientApp {
    private final Scanner scanner = new Scanner(System.in);
    private final Queue<String> commandBuffer = new LinkedList<>();

    private final UDPClient udpClient;
    private final OutputHandler outputHandler = new OutputHandler();

    public ClientApp() throws IOException {
        udpClient = new UDPClient(InetAddress.getLocalHost(), SERVER_PORT);
    }

    public void execute() {

        Console console = new Console(outputHandler, udpClient, commandBuffer);

        while (true) {
            try {
                outputHandler.print("Введите команду: ");
                String input;
                if (commandBuffer.toArray().length == 0) {
                    input = scanner.nextLine().trim();
                } else {
                    input = Objects.requireNonNull(commandBuffer.poll()).trim();
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
