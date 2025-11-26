package org.varun.quizclient.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.varun.quizclient.service.AuthService;

public class LoginController {
    @FXML
    private Label titleLabel;
    @FXML
    private TextField logInField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button logInButton;
    @FXML
    private Label errorLabel;

    private final AuthService authService=new AuthService();

    @FXML
    public void onLoginClick() {
        try {
            String message= authService.login(logInField.getText(),passwordField.getText());
            errorLabel.setStyle("-fx-text-fill: green");
            errorLabel.setText(message);
        } catch (Exception e) {
            errorLabel.setStyle("-fx-text-fill: red");
            errorLabel.setText(e.getMessage());
        }
    }
}
