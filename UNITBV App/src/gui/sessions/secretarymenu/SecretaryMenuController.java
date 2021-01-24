package gui.sessions.secretarymenu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SecretaryMenuController {

    public void onListaSelect() {
        loadResource("lista");
    }

    public void onAdaugaSelect() {
        loadResource("adauga");
    }

    public void onCatalogSelect() {
        loadResource("catalog");
    }

    public void onBurseSelect() {
        loadResource("burse");
    }

    private void loadResource(String resourceName) {
        Parent fxmlScene = null;
        try {
            fxmlScene = FXMLLoader.load(getClass().getResource("/gui/sessions/secretarymenu/" + resourceName + "/" + resourceName + ".fxml"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {
            if (fxmlScene != null) {
                Scene resourceScene = new Scene(fxmlScene);
                switch (resourceName) {
                    case "lista":
                        initializeWindow(resourceScene, "Lista studenti", 1024, 480, true);
                        break;
                    case "adauga":
                        initializeWindow(resourceScene, "Adaugare student", 480, 760, false);
                        break;
                    case "catalog":
                        initializeWindow(resourceScene, "Catalog", 800, 480, true);
                        break;
                    case "burse":
                        initializeWindow(resourceScene, "Burse", 400, 400, false);
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
