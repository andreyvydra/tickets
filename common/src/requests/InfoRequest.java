package requests;

import core.Globals;

import java.util.HashMap;

public class InfoRequest extends Request {
    public InfoRequest(HashMap<String, String> user) {
        super(Globals.CommandNames.INFO, user);
    }
}
