package requests;

import core.Globals;

import java.util.HashMap;

public class GroupByDateRequest extends Request {
    public GroupByDateRequest(HashMap<String, String> user) {
        super(Globals.CommandNames.GROUP_COUNTING_BY_CREATION_DATE, user);
    }
}
