package gui.sessions.login;

import gui.primitives.AlertBox;
import gui.primitives.ConfirmBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.LoginSession;

public class LoginController {

    public TextField username;
    public TextField password;
    public Label errorLabel;

    static final int menuWidth = 600;
    static final int menuHeight = 500;
    static final String menuTitle = "UNITBV App - Main Menu";

    public void onLogin() {
        LoginSession.setUsername(username.getText());
        LoginSession.setPassword(password.getText());
        if (LoginSession.checkLoginCredentials()) {
            closeWindow();
            switch (LoginSession.getRights()) {
                case "student":
                    loadMainMenuWindow("studentmenu");
                    break;
                case "profesor":
                    loadMainMenuWindow("professormenu");
                    break;
                case "secretara":
                    loadMainMenuWindow("secretarymenu");
                    break;
                case "administrator":
                    loadMainMenuWindow("adminmenu");
                    break;
                default:
                    onLoginErrorAlertBox();
            }
        } else {
            invalidCredentials(true);
            username.setText("");
            password.setText("");
        }
    }

    public void onRegister() {
        loadRegisterWindow();
    }

    public void onInput() {
        invalidCredentials(false);
    }

    private void invalidCredentials(boolean active) {
        if (active) {
            errorLabel.setText("Date de conectare incorecte");
            errorLabel.setVisible(true);
        } else {
            errorLabel.setVisible(false);
        }
    }

    private void closeWindow() {
        ((Stage) (username.getScene().getWindow())).close();
    }

    private void loadRegisterWindow() {
        Parent fxmlScene = null;
        try {
            fxmlScene = FXMLLoader.load(getClass().getResource("/gui/sessions/register/register.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {
            if (fxmlScene != null) {
                Scene registerScene = new Scene(fxmlScene);
                initializeRegisterWindow(registerScene);
            }
        }
    }

    private void initializeRegisterWindow(Scene registerScene) {
        Stage window = new Stage();
        window.setTitle("UNITBV App - Register");
        window.initModality(Modality.APPLICATION_MODAL);
        window.setWidth(350);
        window.setHeight(400);
        window.setResizable(false);
        window.setScene(registerScene);
        window.showAndWait();
    }

    private void loadMainMenuWindow(String menuName) {
        Parent fxmlScene = null;
        try {
            fxmlScene = FXMLLoader.load(getClass().getResource("/gui/sessions/" + menuName + "/" + menuName + ".fxml"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {
            if (fxmlScene != null) {
                Scene mainMenuScene = new Scene(fxmlScene);
                initializeMainMenuWindow(mainMenuScene);
            }
        }
    }

    private void initializeMainMenuWindow(Scene mainMenuScene) {
        Stage window = new Stage();
        window.setScene(mainMenuScene);
        window.setTitle(menuTitle);
        window.setWidth(menuWidth);
        window.setHeight(menuHeight);
        window.setResizable(false);
        window.setOnCloseRequest(event -> {
            event.consume();
            onCloseAlertBox(window);
        });
        window.show();
    }

    private static void onCloseAlertBox(Stage window) {
        Boolean answer = ConfirmBox.display("UNITBV App - Close", "Esti sigur ca doresti sa inchizi aplicatia?", "Da", "Nu");
        if (answer)
            window.close();
    }

    private static void onLoginErrorAlertBox() {
        AlertBox.display("UNITBV App - FATAL ERROR", "Aplicatia a intampinat o eroare la incercarea de a se conecta a utilizatorului.\nInchidere fortata aplicatie...");
    }
}
