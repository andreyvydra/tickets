package requests;

import core.Globals;

public class ShowRequest extends Request{
    public ShowRequest() {
        super(Globals.CommandNames.SHOW);
    }
}
