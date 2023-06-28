package gui;

import application.ClientApp;
import data.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;

import static core.Globals.CommandNames.CLEAR;
import static core.Globals.CommandNames.EXECUTE_SCRIPT;
import static core.Globals.Network.USERNAME;
import static core.Globals.TicketFields.*;

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
    private Canvas canvas;

    @FXML
    private Tab canvasTab;

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

    @FXML
    private Label commandHistory;

    @FXML
    private ChoiceBox<Language> language;

    @FXML
    private Label historyLabel;

    @FXML
    private Tab tableTab;

    private ResourceBundle messages;

    private final ClientApp clientApp;

    private final ObservableList<Ticket> ticketObservableList = FXCollections.observableList(new ArrayList<>());

    public AppController(ClientApp clientApp) {
        this.clientApp = clientApp;
    }

    public void initialize() {
        addButton.setOnAction(this::addTicket);
        clearButton.setOnAction(this::clear);
        canvas.setOnMouseClicked(this::openTicket);
        removeLowerButton.setOnAction(this::removeLower);
        executeScriptButton.setOnAction(this::executeScript);
        table.setOnMouseClicked(this::pickRow);
        table.setItems(ticketObservableList);
        language.setValue(Language.RU);
        language.setItems(FXCollections.observableList(Arrays.stream(Language.values()).toList()));
        language.setOnAction(this::changeLanguage);

        id.setCellValueFactory(new PropertyValueFactory<>(ID));
        name.setCellValueFactory(new PropertyValueFactory<>(NAME));
        person.setCellValueFactory(new PropertyValueFactory<>(PERSON));
        coordinates.setCellValueFactory(new PropertyValueFactory<>(COORDINATES));
        creationDate.setCellValueFactory(new PropertyValueFactory<>(CREATION_DATE));
        creatorLogin.setCellValueFactory(new PropertyValueFactory<>(CREATOR_LOGIN));
        price.setCellValueFactory(new PropertyValueFactory<>(PRICE));

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> loadTickets());
            }
        }, 0, 1000);

        Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> updateCanvas());
            }
        }, 0, 1000);

        Timer timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> commandHistory.setText(clientApp.getHistory()));
            }
        }, 0, 1000);

    }

    private void changeLanguage(ActionEvent event) {
        Locale locale = Locale.forLanguageTag(language.getValue().languageName);
        ResourceBundle rb = ResourceBundle.getBundle("locale.Gui", locale);
        messages = ResourceBundle.getBundle("local.Messages", locale);
        tableTab.setText(rb.getString("tableTab"));
        canvasTab.setText(rb.getString("canvasTab"));
        historyLabel.setText(rb.getString("historyLabel"));
        addButton.setText(rb.getString("addButton"));
        removeLowerButton.setText(rb.getString("removeLowerButton"));
        clearButton.setText(rb.getString("clearButton"));
        executeScriptButton.setText(rb.getString("executeScriptButton"));
        greaterThanTypeButton.setText(rb.getString("greaterThanTypeButton"));
        groupByDateButton.setText(rb.getString("groupByDateButton"));
        printTypeAscendingButton.setText(rb.getString("printTypeAscendingButton"));
    }

    private void openTicket(MouseEvent mouseEvent) {
        for (Ticket ticket : clientApp.loadTickets()) {
            Float x = ticket.getCoordinates().getX();
            Float y = ticket.getCoordinates().getY();
            double mx = mouseEvent.getX();
            double my = mouseEvent.getY();
            if (x <= mx && mx <= x + ticket.getPrice() && y <= my && my <= y + ticket.getPrice()) {
                if (ticket.getCreatorLogin().equals(clientApp.getUser().get(USERNAME))) {
                    loadUpdateController(ticket);
                }
            }
        }
    }

    public void updateCanvas() {
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setFill(Paint.valueOf("#fff"));
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Ticket ticket : clientApp.loadTickets()) {
            Float x = ticket.getCoordinates().getX();
            Float y = ticket.getCoordinates().getY();
            String r = Integer.toHexString((int) ((ticket.getId() * 1000) % 256));
            if (r.length() == 1) {
                r = "0" + r;
            }
            String g = Integer.toHexString((int) ((ticket.getId() * 2000) % 256));
            if (g.length() == 1) {
                g = "0" + g;
            }
            String b = Integer.toHexString((int) ((ticket.getId() * 3000) % 256));
            if (b.length() == 1) {
                b = "0" + b;
            }
            context.setStroke(Paint.valueOf("#" + r + g + b));
            long price = ticket.getPrice();
            context.strokeRoundRect(x, y, price, price, price / 2, price / 2);
            context.drawImage(new Image("ticket.png"), x, y, ticket.getPrice(), ticket.getPrice());
        }
    }

    public void addTicket(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setController(new AddController(clientApp, Locale.forLanguageTag(language.getValue().languageName)));
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
            loadUpdateController(ticket);
        }
    }

    private void loadUpdateController(Ticket ticket) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(UpdateController.class.getResource("/gui/updateScene.fxml"));
            Stage stage = new Stage();
            fxmlLoader.setController(new UpdateController(ticket, clientApp, stage, Locale.forLanguageTag(language.getValue().languageName)));
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        } catch (IOException e) {
            clientApp.showMessage("Проблемы с подгрзкой fxml файла");
        }
    }

    public void loadTickets() {
        ArrayList<Ticket> tickets = clientApp.loadTickets();
        ticketObservableList.clear();
        ticketObservableList.addAll(tickets);
    }

    public void clear(ActionEvent event) {
        clientApp.executeCommandFromConsole(CLEAR);
    }

    public void removeLower(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setController(new RemoveLowerController(clientApp, Locale.forLanguageTag(language.getValue().languageName)));
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