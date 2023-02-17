package console.commads;

import application.App;
import console.commads.generalCommands.AppCommand;

import java.io.*;
import java.util.ArrayList;


/**
 * ExecuteScriptCommand can execute scripts with console commands.
 * It also adds commands to commandBuffer from files. It protected from
 * recursions.
 *
 * @see AppCommand
 * @see App
 * @see core.CollectionManager
 */
public class ExecuteScriptCommand extends AppCommand {

    public ExecuteScriptCommand(App app) {
        super(app);
    }

    @Override
    public void execute(String command) {
        ArrayList<String> allCommands = new ArrayList<>();
        allCommands.add(command);
        if (this.recursionFindingCommands(command, allCommands)) {
            this.getApp().setCommandBuffer(allCommands.subList(1, allCommands.size() - 1));
        } else {
            System.out.println("Неизбежна рекурсия");
        }
    }

    public boolean recursionFindingCommands(String currentCommand, ArrayList<String> allCommands) {
        String filename = currentCommand.split(" ")[1];
        String[] commands = this.getCommandsFromFile(filename);
        for (String command : commands) {
            command = command.trim();
            String commandName = command.split(" ")[0];
            if (commandName.equals("execute_script") && allCommands.contains(command)) {
                return false;
            }
            allCommands.add(command);
            if (commandName.equals("execute_script")) {
                if (!this.recursionFindingCommands(command, allCommands)) {
                    return false;
                }
            }
        }
        return true;
    }

    public String[] getCommandsFromFile(String filename) {
        try {
            FileInputStream fileInput = new FileInputStream(filename);
            BufferedInputStream buffer = new BufferedInputStream(fileInput);
            StringBuilder stringBuilder = new StringBuilder();
            for (int b : buffer.readAllBytes()) {
                stringBuilder.append((char) b);
            }
            fileInput.close();
            buffer.close();
            return stringBuilder.toString().split("\n");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }
}
