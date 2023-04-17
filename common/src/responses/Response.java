package responses;

import java.io.Serializable;

public class Response implements Serializable {
    private String msg;
    public Response(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
