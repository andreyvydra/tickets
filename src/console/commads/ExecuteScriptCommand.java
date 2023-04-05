package console.commads;

import application.DataApp;
import console.commads.generalCommands.DataAppCommand;
import core.OutputHandler;

import java.io.*;
import java.util.ArrayList;

import static core.Globals.CommandNames.EXECUTE_SCRIPT;
import static core.Globals.ARGUMENT_POSITION;
import static core.Globals.COMMAND_POSITION;


/**
 * ExecuteScriptCommand can execute scripts with console commands.
 * It also adds commands to commandBuffer from files. It protected from
 * recursions.
 *
 * @see DataApp
 * @see core.CollectionManager
 */
public class ExecuteScriptCommand extends DataAppCommand {


    public ExecuteScriptCommand(OutputHandler outputHandler, DataApp dataApp) {
        super(outputHandler, dataApp);
    }

    @Override
    public void execute(String command) {
        ArrayList<String> allCommands = new ArrayList<>();
        allCommands.add(command);
        if (!this.recursionFindingCommands(command, allCommands) && allCommands.size() != 0) {
            this.dataApp.setCommandBuffer(allCommands.subList(1, allCommands.size() - 1));
        } else {
            outputHandler.println("Неизбежна рекурсия");
        }
    }

    public boolean recursionFindingCommands(String currentCommand, ArrayList<String> allCommands) {
        String filename = currentCommand.split(" ")[ARGUMENT_POSITION];
        String[] commands = this.getCommandsFromFile(filename);
        for (String command : commands) {
            command = command.trim();
            String commandName = command.split(" ")[COMMAND_POSITION];
            if (commandName.equals(EXECUTE_SCRIPT) && allCommands.contains(command)) {
                return true;
            }
            allCommands.add(command);
            if (commandName.equals(EXECUTE_SCRIPT)) {
                if (this.recursionFindingCommands(command, allCommands)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String[] getCommandsFromFile(String filename) {
        try (FileInputStream fileInput = new FileInputStream(filename);
             BufferedInputStream buffer = new BufferedInputStream(fileInput)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int b : buffer.readAllBytes()) {
                stringBuilder.append((char) b);
            }
            return stringBuilder.toString().split("\n");
        } catch (FileNotFoundException e) {
            outputHandler.println("Файл не найден, является директорией или не может быть прочитан!");
        } catch (IOException e) {
            outputHandler.println("Поток был завершён, или файл не может быть прочитан!");
        }
        return new String[]{};
    }

    @Override
    public void printHelp() {
        outputHandler.println("execute_script file_name : считать и исполнить скрипт из указанного файла.");
    }
}
