package requests;

import core.Globals;

import java.util.HashMap;

public class ShowRequest extends Request{
    public ShowRequest(HashMap<String, String> user) {
        super(Globals.CommandNames.SHOW, user);
    }
}
