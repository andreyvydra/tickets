package gui;

import application.ClientApp;
import data.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.Objects;

public class RemoveLowerController extends AddController {

    @FXML
    private Button removeLowerTicket;

    public RemoveLowerController(ClientApp clientApp) {
        super(clientApp);
    }

    @Override
    public void initialize() {
        initializeChoicesBox();
        removeLowerTicket.setOnAction(this::removeLower);
    }

    public void removeLower(ActionEvent event) {
        Ticket ticket = createTicket();
        if (Objects.isNull(ticket)) {
            return;
        }

        clientApp.removeLowerTicket(ticket);
    }
}
