package console.commads;

import application.App;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class ExecuteScriptCommand implements Command {

    private App app;

    public ExecuteScriptCommand(App app) {
        this.app = app;
    }
    @Override
    public void execute(String command) {
        try {
            String filename = command.split(" ")[1];
            FileInputStream fileInput = new FileInputStream(filename);
            BufferedInputStream buffer = new BufferedInputStream(fileInput);
            StringBuilder stringBuilder = new StringBuilder();
            for (int b : buffer.readAllBytes()) {
                stringBuilder.append((char) b);
            }
            String[] commands = stringBuilder.toString().split("\n");
            for (String currentCommand : stringBuilder.toString().split("\n")) {
                currentCommand = currentCommand.trim();
                if (currentCommand.equals(command)) {
                    System.out.println("В файле присутствует рекурсия!");
                    return;
                }
            }
            this.app.setCommandBuffer(commands);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
