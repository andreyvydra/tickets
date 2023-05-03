package requests;

import core.Globals;

public class RemoveRequest extends Request {
    private long id;

    public RemoveRequest(long id) {
        super(Globals.CommandNames.REMOVE_BY_ID);
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
