package org.varun.quizclient.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.varun.quizclient.HelloApplication;
import org.varun.quizclient.service.AuthService;

import java.io.IOException;

public class AuthController {
    @FXML
    private Label titleLabel;
    @FXML
    private TextField logInField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button logInButton;
    @FXML
    private Label errorLabel;
    @FXML
    private Button signUpButton;
    @FXML
    private Hyperlink logInSwitch;
    @FXML
    private Hyperlink signUpSwitch;

    private final AuthService authService=new AuthService();
    private final HelloApplication ha=new HelloApplication();

    @FXML
    public void onLogInClick() {
        try {
            String message= authService.login(logInField.getText(),passwordField.getText());
            errorLabel.setStyle("-fx-text-fill: green");
            errorLabel.setText(message);
        } catch (Exception e) {
            errorLabel.setStyle("-fx-text-fill: red");
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML
    public void signUpNavigate() throws IOException {
        ha.changeScene("views/signup-view.fxml");
    }

    @FXML
    public void logInNavigate() throws IOException {
        ha.changeScene("views/login-view.fxml");
    }
}
