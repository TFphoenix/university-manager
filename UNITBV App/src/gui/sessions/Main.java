package gui.sessions;

import daoimpl.StudentDaoImpl;
import daoimpl.UtilizatorDaoImpl;//
import entities.Utilizator;//
import gui.primitives.ConfirmBox;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;//

public class Main extends Application {

    //Login Window
    private Stage window;
    static final int windowWidth = 400;
    static final int windowHeight = 500;

    public static void main(String[] args) {
        UtilizatorDaoImpl udi = new UtilizatorDaoImpl();

        List<Utilizator> utilizatori = udi.selectAll();
        for (Utilizator u : utilizatori) {
            System.out.println(u.getId() + ", " + u.getMail() + ", " + u.getParola() + ", " + u.getDrepturi());
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        Parent fxmlScene = FXMLLoader.load(getClass().getResource("/gui/sessions/login/login.fxml"));
        Scene loginScene = new Scene(fxmlScene);
        initializeLoginWindow();
        window.setScene(loginScene);
        window.show();
    }

    public void initializeLoginWindow() {
        window.setTitle("UNITBV App - Login");
        window.setWidth(windowWidth);
        window.setHeight(windowHeight);
        window.setResizable(false);
        window.setOnCloseRequest(event -> {
            event.consume();
            closeWindow();
        });
    }

    private void closeWindow() {
        Boolean answer = ConfirmBox.display("UNITBV App - Close", "Esti sigur ca doresti sa inchizi aplicatia?", "Da", "Nu");
        if (answer)
            window.close();
    }
}
