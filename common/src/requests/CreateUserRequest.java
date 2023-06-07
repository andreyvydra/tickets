package requests;

import java.util.HashMap;

import static core.Globals.CommandNames.REGISTER;

public class CreateUserRequest extends Request {
    public CreateUserRequest(HashMap<String, String> user) {
        super(REGISTER, user);
    }
}
