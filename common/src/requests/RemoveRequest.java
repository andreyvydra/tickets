package requests;

import core.Globals;

import java.util.HashMap;

public class RemoveRequest extends Request {
    private final long id;

    public RemoveRequest(long id, HashMap<String, String> user) {
        super(Globals.CommandNames.REMOVE_BY_ID, user);
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
