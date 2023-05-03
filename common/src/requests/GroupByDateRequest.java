package requests;

import core.Globals;

public class GroupByDateRequest extends Request {
    public GroupByDateRequest() {
        super(Globals.CommandNames.GROUP_COUNTING_BY_CREATION_DATE);
    }
}
