package gui.sessions.studentmenu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StudentMenuController {

    public void onDateSelect() {
        loadResource("date");
    }

    public void onSituatieSelect() {
        loadResource("situatie");
    }

    public void onClasamenteSelect() {
        loadResource("clasamente");
    }

    public void onCalculareSelect() {
        loadResource("calculare");
    }

    private void loadResource(String resourceName) {
        Parent fxmlScene = null;
        try {
            fxmlScene = FXMLLoader.load(getClass().getResource("/gui/sessions/studentmenu/" + resourceName + "/" + resourceName + ".fxml"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {
            if (fxmlScene != null) {
                Scene resourceScene = new Scene(fxmlScene);
                switch (resourceName) {
                    case "date":
                        initializeWindow(resourceScene, "Date personale", 620, 480, false);
                        break;
                    case "situatie":
                        initializeWindow(resourceScene, "Situatie scolara personala", 800, 500, true);
                        break;
                    case "clasamente":
                        initializeWindow(resourceScene, "Clasamente studenti", 400, 400, false);
                        break;
                    case "calculare":
                        initializeWindow(resourceScene, "Calculare sanse", 400, 400, false);
                        break;
                    default:
                        return;
                }
            }
        }
    }

    private void initializeWindow(Scene resourceScene, String windowTitle, int width, int height, Boolean resizable) {
        Stage window = new Stage();
        window.setScene(resourceScene);
        window.setTitle(windowTitle);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(width);
        window.setMinHeight(height);
        window.setResizable(resizable);
        window.show();
    }
}