package gui;

import application.ClientApp;
import data.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class RemoveLowerController extends AddController {

    @FXML
    private Button removeLowerTicket;

    public RemoveLowerController(ClientApp clientApp, Locale locale) {
        super(clientApp, locale);
    }

    @Override
    public void initialize() {
        initializeChoicesBox();
        initializeLanguage();
        removeLowerTicket.setOnAction(this::removeLower);
    }

    @Override
    public void initializeLanguage() {
        super.generalInitializeLanguage();
        ResourceBundle rb = ResourceBundle.getBundle("locale.GuiTicket", locale);
        removeLowerTicket.setText(rb.getString("removeLowerButton"));
    }

    public void removeLower(ActionEvent event) {
        Ticket ticket = createTicket();
        if (Objects.isNull(ticket)) {
            return;
        }

        clientApp.removeLowerTicket(ticket);
    }
}
