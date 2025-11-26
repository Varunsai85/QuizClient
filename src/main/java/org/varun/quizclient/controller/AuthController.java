package org.varun.quizclient.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.varun.quizclient.HelloApplication;
import org.varun.quizclient.service.AuthService;

import java.io.IOException;

public class AuthController {
    @FXML
    private TextField logInField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField codeField;

    private final AuthService authService = new AuthService();
    private final HelloApplication ha = new HelloApplication();

    @FXML
    public void onLogInClick() {
        try {
            String message = authService.logIn(logInField.getText(), passwordField.getText());
            greenErrorLabel(message);
        } catch (Exception e) {
            redErrorLabel(e.getMessage());
        }
    }

    @FXML
    public void onSignUpClick() {
        try {
            String message = authService.signUp(usernameField.getText(), emailField.getText(), passwordField.getText());
            greenErrorLabel(message);
            ha.changeScene("views/verify-view.fxml");
        } catch (Exception e) {
            redErrorLabel(e.getMessage());
        }
    }

    @FXML
    public void onVerificationClick() {
        try {
            String message = authService.verification(codeField.getText());
            greenErrorLabel(message);
            ha.changeScene("views/login-view.fxml");
        } catch (Exception e) {
            redErrorLabel(e.getMessage());
        }
    }

    @FXML
    public void onEmailVerificationClick() {
        try {
            String message = authService.emailVerification(emailField.getText(), codeField.getText());
            greenErrorLabel(message);
            ha.changeScene("views/login-view.fxml");
        } catch (Exception e) {
            redErrorLabel(e.getMessage());
        }
    }

    @FXML
    public void onResendClick() {
        try {
            String message = authService.resendVerificationCode(emailField.getText());
            greenErrorLabel(message);
            ha.changeScene("views/verify-view.fxml");
        } catch (Exception e) {
            redErrorLabel(e.getMessage());
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

    @FXML
    public void resendNavigate() throws IOException {
        ha.changeScene("views/resend-view.fxml");
    }

    @FXML
    private void verifyNavigate() throws IOException {
        ha.changeScene("views/verify-view.fxml");
    }

    private void greenErrorLabel(String message) {
        errorLabel.setStyle("-fx-text-fill: green");
        errorLabel.setText(message);
    }

    private void redErrorLabel(String message) {
        errorLabel.setStyle("-fx-text-fill: red");
        errorLabel.setText(message);
    }
}
