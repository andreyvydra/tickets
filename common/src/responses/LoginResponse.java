package responses;

public class LoginResponse extends Response{
    public boolean is_logged = true;

    public LoginResponse(String msg, boolean is_logged) {
        super(msg);
        this.is_logged = is_logged;
    }
}
