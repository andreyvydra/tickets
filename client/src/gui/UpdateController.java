package gui;

import application.ClientApp;
import data.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Objects;

import static core.Globals.CommandNames.REMOVE_BY_ID;

public class UpdateController extends AddController {

    @FXML
    private Button removeTicket;

    @FXML
    private Button updateButton;


    public Ticket ticket;

    public ClientApp clientApp;

    public Stage stage;

    public UpdateController(Ticket ticket, ClientApp clientApp, Stage stage) {
        super(clientApp);
        this.ticket = ticket;
        this.clientApp = clientApp;
        this.stage = stage;
    }

    public void initialize() {
        initializeChoicesBox();
        removeTicket.setOnAction(this::remove);
        updateButton.setOnAction(this::update);

        nameField.setText(ticket.getName());
        eyeColorChoiceBox.setValue(ticket.getPerson().getEyeColor());
        hairColorChoiceBox.setValue(ticket.getPerson().getHairColor());
        birthdayPersonPick.setValue(ticket.getPerson().getBirthday().toLocalDate());
        nationalityChoiceBox.setValue(ticket.getPerson().getNationality());
        xLocationField.setText(String.valueOf(ticket.getPerson().getLocation().getX()));
        yLocationField.setText(String.valueOf(ticket.getPerson().getLocation().getY()));
        zLocationField.setText(String.valueOf(ticket.getPerson().getLocation().getZ()));
        xCoordinatesField.setText(String.valueOf(ticket.getCoordinates().getX()));
        yCoordinatesField.setText(String.valueOf(ticket.getCoordinates().getY()));
        priceField.setText(String.valueOf(ticket.getPrice()));
        typeChoiceBox.setValue(ticket.getType());

    }

    public void update(ActionEvent event) {
        setTicketFromFields(ticket);
        if (Objects.isNull(ticket)) {
            return;
        }

        clientApp.updateTicket(ticket);
    }


    public void remove(ActionEvent event) {
        clientApp.executeCommandFromConsole(REMOVE_BY_ID + " " + ticket.getId());
        stage.close();
    }
}
