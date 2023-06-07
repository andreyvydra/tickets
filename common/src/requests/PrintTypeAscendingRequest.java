package requests;

import core.Globals;

import java.util.HashMap;

public class PrintTypeAscendingRequest extends Request {
    public PrintTypeAscendingRequest(HashMap<String, String> user) {
        super(Globals.CommandNames.PRINT_FIELD_ASCENDING_TYPE, user);
    }
}
