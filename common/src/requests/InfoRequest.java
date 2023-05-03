package requests;

import core.Globals;

public class InfoRequest extends Request {
    public InfoRequest() {
        super(Globals.CommandNames.INFO);
    }
}
