package gui.sessions.studentmenu.date;

import daoimpl.StudentDaoImpl;
import entities.Group;
import entities.Registry;
import entities.Student;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import util.LoginSession;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class DateController implements Initializable {

    private Student student;
    private Group group;
    private List<Registry> registry;

    public Label nameLabel;
    public Label badgeLabel;
    public Label cnpLabel;
    public Label domicileLabel;
    public Label birthLabel;
    public Label registrationLabel;
    public Label groupNameLabel;
    public Label groupSpecializationLabel;
    public Label userIdLabel;
    public Label userMailLabel;

    public DateController() {
        StudentDaoImpl sdi = new StudentDaoImpl();
        student = sdi.selectByID(LoginSession.getEntityId());
        group = sdi.getStudentGroup(student.getIdGrupa());
        registry = sdi.getStudentRegistry(student.getId());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLabel.setText(student.getName());
        badgeLabel.setText(student.getBadge());
        cnpLabel.setText(student.getCnp());
        domicileLabel.setText(student.getDomicile());
        birthLabel.setText(student.getDateOfBirth().toString());
        registrationLabel.setText(student.getDateOfRegistration().toString());
        groupNameLabel.setText(group.getCode());
        groupSpecializationLabel.setText(group.getSpecialization());
        userIdLabel.setText(String.valueOf(LoginSession.getId()));
        userMailLabel.setText(LoginSession.getUsername());
    }
}
