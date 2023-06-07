package console.commads;

import console.commads.generalCommands.DefaultCommand;
import core.OutputHandler;

import java.util.HashMap;
import java.util.Objects;

import static core.Globals.Network.*;

public class LogoutCommand extends DefaultCommand {
    public LogoutCommand(OutputHandler outputHandler) {
        super(outputHandler);
    }

    @Override
    public void execute(String command, HashMap<String, String> user) {
        if (!Objects.isNull(user.get(IS_LOGGED))) {
            user.put(USERNAME, null);
            user.put(PASSWORD, null);
            user.put(IS_LOGGED, null);
            outputHandler.println("Вы разлогинены!");
        } else {
            outputHandler.println("Чтобы разлогиниться надо сначала залогиниться");
        }
    }

    @Override
    public void description() {
        outputHandler.println("logout : разлогиниться");
    }
}
