package responses;

public class DataResponse extends Response {
    private final Object[] data;

    public DataResponse(String msg, Object[] data) {
        super(msg);
        this.data = data;
    }

    public Object[] getData() {
        return data;
    }
}
