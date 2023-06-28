package gui;

import application.ClientApp;
import core.exceptions.CoordinateXException;
import core.exceptions.EmptyFieldException;
import core.exceptions.EmptyNameException;
import core.exceptions.ValueIsNotPositiveException;
import data.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import static core.Globals.Network.USERNAME;

public class AddController {

    @FXML
    protected Label birthdayLabel;

    @FXML
    protected DatePicker birthdayPersonPick;

    @FXML
    protected Label colorEyeLabel;

    @FXML
    protected Label colorHairLabel;

    @FXML
    protected ChoiceBox<Color> eyeColorChoiceBox;

    @FXML
    protected ChoiceBox<Color> hairColorChoiceBox;

    @FXML
    protected TextField nameField;

    @FXML
    protected Label nameLabel;

    @FXML
    protected ChoiceBox<Country> nationalityChoiceBox;

    @FXML
    protected Label nationalityLabel;

    @FXML
    protected TextField priceField;

    @FXML
    protected Label priceLabel;

    @FXML
    protected Label ticketLabel;

    @FXML
    protected ChoiceBox<TicketType> typeChoiceBox;

    @FXML
    protected Label typeLabel;

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


    @FXML
    protected Label locationLabel;

    @FXML
    protected Label personLabel;


    @FXML
    protected Label coordinatesLabel;

    public ClientApp clientApp;

    protected Locale locale;

    public AddController(ClientApp clientApp, Locale locale) {
        this.clientApp = clientApp;
        this.locale = locale;
    }

    public void initialize() {
        initializeChoicesBox();
        initializeLanguage();
        addButton.setOnAction(this::addTicket);
        addIfMaxTicket.setOnAction(this::addIfMaxTicket);
    }

    public void initializeLanguage() {
        generalInitializeLanguage();
        ResourceBundle rb = ResourceBundle.getBundle("locale.GuiTicket", locale);
        addButton.setText(rb.getString("addButton"));
        addIfMaxTicket.setText(rb.getString("addIfMaxButton"));
    }

    public void generalInitializeLanguage() {
        ResourceBundle rb = ResourceBundle.getBundle("locale.GuiTicket", locale);
        ticketLabel.setText(rb.getString("ticketLabel"));
        nameLabel.setText(rb.getString("nameLabel"));
        priceLabel.setText(rb.getString("priceLabel"));
        typeLabel.setText(rb.getString("typeLabel"));
        birthdayLabel.setText(rb.getString("birthdayLabel"));
        colorEyeLabel.setText(rb.getString("colorEyeLabel"));
        colorHairLabel.setText(rb.getString("colorHairLabel"));
        nationalityLabel.setText(rb.getString("nationalityLabel"));
        coordinatesLabel.setText(rb.getString("coordinatesLabel"));
        personLabel.setText(rb.getString("personLabel"));
        locationLabel.setText(rb.getString("locationLabel"));
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
            if (Objects.isNull(birthdayPersonPick.getValue())) {
                clientApp.showMessage("Дата пустая");
                return false;
            }
            person.setBirthday(birthdayPersonPick.getValue().atStartOfDay());
            person.setHairColor(hairColorChoiceBox.getValue());
            person.setEyeColor(eyeColorChoiceBox.getValue());
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
        } catch (NumberFormatException e) {
            clientApp.showMessage("Неверный формат числа");
        } catch (DateTimeParseException e) {
            clientApp.showMessage("Неверный формат даты");
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