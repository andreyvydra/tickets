package gui;

import application.ClientApp;
import console.Console;
import core.exceptions.CommandWasNotFound;
import data.Coordinates;
import data.Person;
import data.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import network.UDPClient;
import requests.ShowRequest;
import responses.ShowResponse;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.ConsoleHandler;

import static core.Globals.CommandNames.CLEAR;
import static core.Globals.CommandNames.EXECUTE_SCRIPT;
import static core.Globals.Network.USERNAME;
import static core.Globals.TicketFields.*;
import static java.lang.Thread.sleep;

public class AppController {

    @FXML
    private TableColumn<Ticket, Coordinates> coordinates;

    @FXML
    private TableColumn<Ticket, ZonedDateTime> creationDate;

    @FXML
    private TableColumn<Ticket, String> creatorLogin;

    @FXML
    private TableColumn<Ticket, Long> id;

    @FXML
    private TableColumn<Ticket, String> name;

    @FXML
    private TableColumn<Ticket, Person> person;

    @FXML
    private TableColumn<Ticket, Long> price;

    @FXML
    private TableView<Ticket> table;

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button removeLowerButton;

    @FXML
    private Button executeScriptButton;

    @FXML
    private Button greaterThanTypeButton;

    @FXML
    private Button groupByDateButton;

    @FXML
    private Button printTypeAscendingButton;

    private final ClientApp clientApp;

    public AppController(ClientApp clientApp) {
        this.clientApp = clientApp;
    }

    public void initialize() {
        addButton.setOnAction(this::addTicket);
        clearButton.setOnAction(this::clear);
        removeLowerButton.setOnAction(this::removeLower);
        executeScriptButton.setOnAction(this::executeScript);
        table.setOnMouseClicked(this::pickRow);

        id.setCellValueFactory(new PropertyValueFactory<>(ID));
        name.setCellValueFactory(new PropertyValueFactory<>(NAME));
        person.setCellValueFactory(new PropertyValueFactory<>(PERSON));
        coordinates.setCellValueFactory(new PropertyValueFactory<>(COORDINATES));
        creationDate.setCellValueFactory(new PropertyValueFactory<>(CREATION_DATE));
        creatorLogin.setCellValueFactory(new PropertyValueFactory<>(CREATOR_LOGIN));
        price.setCellValueFactory(new PropertyValueFactory<>(PRICE));

        Thread thread = new Thread(() -> {
            while (true) {
                loadTickets();
                try {
                    sleep(1500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }

    public void addTicket(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setController(new AddController(clientApp));
            fxmlLoader.setLocation(AddController.class.getResource("/gui/addScene.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        } catch (IOException e) {
            clientApp.showMessage("Проблемы с подгрзкой fxml файла");
        }

    }

    public void pickRow(MouseEvent event) {
        Ticket ticket = table.getSelectionModel().selectedItemProperty().get();
        if (Objects.isNull(ticket)) {
            return;
        }
        if (ticket.getCreatorLogin().equals(clientApp.getUser().get(USERNAME))) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(UpdateController.class.getResource("/gui/updateScene.fxml"));
                Stage stage = new Stage();
                fxmlLoader.setController(new UpdateController(ticket, clientApp, stage));
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.show();
            } catch (IOException e) {
                clientApp.showMessage("Проблемы с подгрзкой fxml файла");
            }
        }
    }

    public void loadTickets() {
        ArrayList<Ticket> tickets = clientApp.loadTickets();
        ObservableList<Ticket> ticketsList = FXCollections.observableList(tickets);
        table.setItems(ticketsList);
    }

    public void clear(ActionEvent event) {
        clientApp.executeCommandFromConsole(CLEAR);
    }

    public void removeLower(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setController(new RemoveLowerController(clientApp));
            fxmlLoader.setLocation(RemoveLowerController.class.getResource("/gui/removeLower.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        } catch (IOException e) {
            clientApp.showMessage("Проблемы с подгрзкой fxml файла");
        }
    }

    public void executeScript(ActionEvent event) {
        clientApp.executeCommandFromConsole(EXECUTE_SCRIPT + " script");
        synchronized (clientApp) {
            clientApp.notifyAll();
        }
    }
}