package requests;

import core.Globals;

public class PrintTypeAscendingRequest extends Request {
    public PrintTypeAscendingRequest() {
        super(Globals.CommandNames.PRINT_FIELD_ASCENDING_TYPE);
    }
}
