package gui;

import application.ClientApp;
import console.Console;
import console.commads.generalCommands.Command;
import core.exceptions.CommandWasNotFound;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import javax.naming.Context;
import javax.print.attribute.AttributeSet;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Objects;

import static core.Globals.CommandNames.LOGIN;
import static core.Globals.CommandNames.REGISTER;
import static core.Globals.Network.*;

public class LoginController {

    @FXML
    private Button logInButton;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button signUpButton;

    private ClientApp clientApp;

    public LoginController(ClientApp clientApp) {
        this.clientApp = clientApp;
    }

    public void initialize() {
        logInButton.setOnAction(this::logInUser);
        signUpButton.setOnAction(this::signUpUser);
    }

    public void logInUser(ActionEvent event) {
        clientApp.setUser(loginField.getText(), passwordField.getText());
        clientApp.executeCommandFromConsole(LOGIN);
        if (!Objects.isNull(clientApp.getUserIsLogged())) {
            clientApp.goToAppScene();
        }
    }

    public void signUpUser(ActionEvent event) {
        clientApp.setUser(loginField.getText(), passwordField.getText());
        clientApp.executeCommandFromConsole(REGISTER);
    }

}
