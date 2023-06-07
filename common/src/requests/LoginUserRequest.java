package requests;

import java.util.HashMap;

import static core.Globals.CommandNames.LOGIN_USER;

public class LoginUserRequest extends Request {
    public LoginUserRequest(HashMap<String, String> user) {
        super(LOGIN_USER, user);
    }
}
