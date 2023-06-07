package responses;

public class CreateUserResponse extends Response {
    public long userId;
    public CreateUserResponse(String msg, long id) {
        super(msg);
        userId = id;
    }
}
