package gui.primitives;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.*;

public class ConfirmBox {

    static boolean answer;

    public static boolean display(String title, String message, String trueMessage, String falseMessage) {
        trueMessage = trueMessage == null ? "Yes" : trueMessage;
        falseMessage = falseMessage == null ? "No" : falseMessage;

        // Window
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(120);
        Label label = new Label();
        label.setText(message);

        // Buttons
        Button yesButton = new Button(trueMessage);
        yesButton.setMinWidth(100);
        Button noButton = new Button(falseMessage);
        noButton.setMinWidth(100);
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        // Layouts
        VBox layout = new VBox(10);
        HBox buttonsLayout = new HBox(10);
        buttonsLayout.getChildren().addAll(yesButton, noButton);
        buttonsLayout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, buttonsLayout);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

}
