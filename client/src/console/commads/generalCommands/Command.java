package console.commads.generalCommands;

import java.util.HashMap;

public interface Command {
    void execute(String command, HashMap<String, String> user);

    void description();
}
