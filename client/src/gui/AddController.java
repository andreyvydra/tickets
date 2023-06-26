package gui;

import application.ClientApp;
import console.Console;
import core.exceptions.CoordinateXException;
import core.exceptions.EmptyFieldException;
import core.exceptions.EmptyNameException;
import core.exceptions.ValueIsNotPositiveException;
import data.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.Objects;

import static core.Globals.Network.USERNAME;

public class AddController {

    @FXML
    protected DatePicker birthdayPersonPick;

    @FXML
    protected ChoiceBox<Color> eyeColorChoiceBox;

    @FXML
    protected ChoiceBox<Color> hairColorChoiceBox;

    @FXML
    protected TextField nameField;

    @FXML
    protected ChoiceBox<Country> nationalityChoiceBox;

    @FXML
    protected TextField priceField;

    @FXML
    protected ChoiceBox<TicketType> typeChoiceBox;

    @FXML
    protected TextField xCoordinatesField;

    @FXML
    protected TextField xLocationField;

    @FXML
    protected TextField yCoordinatesField;

    @FXML
    protected TextField yLocationField;

    @FXML
    protected TextField zLocationField;

    @FXML
    private Button addButton;

    @FXML
    private Button addIfMaxTicket;

    public ClientApp clientApp;

    public AddController(ClientApp clientApp) {
        this.clientApp = clientApp;
    }

    public void initialize() {
        initializeChoicesBox();
        addButton.setOnAction(this::addTicket);
        addIfMaxTicket.setOnAction(this::addIfMaxTicket);
    }

    public void initializeChoicesBox() {
        eyeColorChoiceBox.setItems(FXCollections.observableList(Arrays.stream(Color.values()).toList()));
        hairColorChoiceBox.setItems(FXCollections.observableList(Arrays.stream(Color.values()).toList()));
        nationalityChoiceBox.setItems(FXCollections.observableList(Arrays.stream(Country.values()).toList()));
        typeChoiceBox.setItems(FXCollections.observableList(Arrays.stream(TicketType.values()).toList()));
    }

    public boolean setTicketFromFields(Ticket ticket) {
        try {
            ticket.setName(nameField.getText());
            ticket.setPrice(Long.parseLong(priceField.getText()));
            ticket.setCreatorLogin(clientApp.getUser().get(USERNAME));
            ticket.setType(typeChoiceBox.getValue());
            Coordinates coordinates = new Coordinates();
            coordinates.setX(Float.parseFloat(xCoordinatesField.getText()));
            coordinates.setY(Float.parseFloat(yCoordinatesField.getText()));
            ticket.setCoordinates(coordinates);
            Person person = new Person();
            person.setHairColor(hairColorChoiceBox.getValue());
            person.setEyeColor(eyeColorChoiceBox.getValue());
            person.setBirthday(birthdayPersonPick.getValue().atStartOfDay());
            person.setNationality(nationalityChoiceBox.getValue());
            Location location = new Location();
            location.setX(Double.parseDouble(xLocationField.getText()));
            location.setY(Integer.valueOf(yLocationField.getText()));
            location.setZ(Float.parseFloat(zLocationField.getText()));
            person.setLocation(location);
            ticket.setPerson(person);
            return true;
        } catch (EmptyNameException | ValueIsNotPositiveException | EmptyFieldException | CoordinateXException e) {
            clientApp.showMessage(e.toString());
        }
        return false;
    }

    public Ticket createTicket() {
        Ticket ticket = new Ticket();
        if (setTicketFromFields(ticket)) {
            return ticket;
        }

        return null;
    }

    private void addTicket(ActionEvent event) {
        Ticket ticket = createTicket();
        if (Objects.isNull(ticket)) {
            return;
        }

        clientApp.addTicket(ticket);
    }

    private void addIfMaxTicket(ActionEvent event) {
        Ticket ticket = createTicket();
        if (Objects.isNull(ticket)) {
            return;
        }

        clientApp.addIfMaxTicket(ticket);
    }
}