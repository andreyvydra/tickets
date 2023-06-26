package console.commads.generalCommands;

import java.io.IOException;
import java.util.HashMap;

public interface Command {
    void execute(String command, HashMap<String, String> user) throws IOException, ClassNotFoundException;

    void description();
}
