package gui.sessions.professormenu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProfessorMenuController {

    public void onDateSelect() {
        loadResource("date");
    }

    public void onDisciplineSelect() {
        loadResource("discipline");
    }

    public void onAdaugaSelect() {
        loadResource("adauga");
    }

    public void onNoteSelect() {
        loadResource("note");
    }

    private void loadResource(String resourceName) {
        Parent fxmlScene = null;
        try {
            fxmlScene = FXMLLoader.load(getClass().getResource("/gui/sessions/professormenu/" + resourceName + "/" + resourceName + ".fxml"));
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
                    case "discipline":
                        initializeWindow(resourceScene, "Discipline predate", 800, 500, true);
                        break;
                    case "adauga":
                        initializeWindow(resourceScene, "Adauga nota", 480, 620, false);
                        break;
                    case "note":
                        initializeWindow(resourceScene, "Note adaugate", 800, 500, true);
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
