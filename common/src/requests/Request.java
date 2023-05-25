package requests;


import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 200L;
    String commandName;

    private final HashMap<String, String> user;

    public Request(String commandName, HashMap<String, String> user) {
        this.commandName = commandName;
        this.user = user;
    }

    public HashMap<String, String> getUser() {
        return user;
    }

    public String getCommandName() {
        return commandName;
    }

}
