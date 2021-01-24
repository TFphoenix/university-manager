package gui.sessions.professormenu.date;

import daoimpl.ProfessorDaoImpl;
import entities.Professor;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import util.LoginSession;

import java.net.URL;
import java.util.ResourceBundle;


public class DateController implements Initializable {

    private Professor professor;

    public Label nameLabel;
    public Label titleLabel;
    public Label userIdLabel;
    public Label userMailLabel;

    public DateController() {
        ProfessorDaoImpl pdi = new ProfessorDaoImpl();
        professor = pdi.selectByID(LoginSession.getEntityId());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLabel.setText(professor.getName());
        titleLabel.setText(professor.getTitle());
        userIdLabel.setText(String.valueOf(LoginSession.getId()));
        userMailLabel.setText(LoginSession.getUsername());
    }
}
