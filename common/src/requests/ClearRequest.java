package requests;

import core.Globals.CommandNames;

import java.util.HashMap;

public class ClearRequest extends Request{

    public ClearRequest(HashMap<String, String> user) {
        super(CommandNames.CLEAR, user);
    }
}
