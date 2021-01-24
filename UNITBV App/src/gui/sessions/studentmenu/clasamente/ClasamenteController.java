package gui.sessions.studentmenu.clasamente;

import daoimpl.StudentDaoImpl;
import entities.Student;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import util.LoginSession;

import java.net.URL;
import java.util.ResourceBundle;

public class ClasamenteController implements Initializable {

    private Student student;

    public Label clasamentGrupaLabel;
    public Label clasamentFacultateLabel;
    public Label clasamentUniversitateLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StudentDaoImpl sdi = new StudentDaoImpl();
        student = sdi.selectByID(LoginSession.getEntityId());

        clasamentGrupaLabel.setText(String.valueOf(sdi.getStudentGroupRank(student.getId())) +
                "/" +
                String.valueOf(sdi.getGroupStudentsCount(student.getIdGrupa())));
        clasamentFacultateLabel.setText(String.valueOf(sdi.getStudentRank(student.getId())) +
                "/" +
                String.valueOf(sdi.getStudentsCount()));
        clasamentUniversitateLabel.setText("?/?");
    }
}
