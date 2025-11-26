package org.varun.quizclient.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private Label titleLabel;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button logInButton;
    @FXML
    private Label errorLabel;

    @FXML
    public void onLoginClick() {
        if (username.getText().equals("varun") && password.getText().equals("123")) {
            System.out.printf("User %s logged in successfully", username.getText());
            errorLabel.setText("Login Successful");
            errorLabel.setStyle("-fx-text-fill: green");
        } else {
            System.out.printf("User %s log in failed", username.getText());
            errorLabel.setText("Wrong credentials");
            errorLabel.setStyle("-fx-text-fill: red");
        }
    }
}
