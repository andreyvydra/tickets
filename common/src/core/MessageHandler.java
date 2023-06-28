package core;

import javafx.scene.control.Alert;

public class MessageHandler extends OutputHandler {

    private final Alert alert;

    public MessageHandler() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        this.alert = alert;
    }
    @Override
    public void println(Object... objects) {
        for (Object object : objects) {
            if (!alert.isShowing()) {
                alert.setContentText(object.toString());
                alert.showAndWait();
            }
        }
    }
}
