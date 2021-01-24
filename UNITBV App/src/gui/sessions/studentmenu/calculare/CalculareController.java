package gui.sessions.studentmenu.calculare;

import daoimpl.StudentDaoImpl;
import entities.Student;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import util.LoginSession;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculareController implements Initializable {

    private Student student;

    public Label sanseRealeLabel;
    public Label sansePotentialeLabel;
    public Label predictieLabel;

    private Color correctColor;
    private Color wrongColor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StudentDaoImpl sdi = new StudentDaoImpl();
        student = sdi.selectByID(LoginSession.getEntityId());

        int studentRank = sdi.getStudentGroupRank(student.getId());
        int scholarshipsCount = sdi.getGradeScholarshipsCount(student.getIdGrupa());
        int studentsCount = sdi.getGroupStudentsCount(student.getIdGrupa());

        // Sanse reale
        float sanseReale = 0;
        if (studentRank <= scholarshipsCount) {
            sanseReale = 100 / scholarshipsCount * (scholarshipsCount - studentRank + 1);
        }
        sanseRealeLabel.setText(String.valueOf(sanseReale) + "%");

        // Sanse potentiale
        float sansePotentiale = 100 / studentsCount * (studentsCount - studentRank + 1);
        sansePotentialeLabel.setText(String.valueOf(sansePotentiale) + "%");

        // Predictie
        correctColor = Color.DARKGREEN;
        wrongColor = Color.DARKRED;
        float predictie = (sansePotentiale + sanseReale) / 2;
        if (predictie >= 50) {
            predictieLabel.setText("DA");
            predictieLabel.setTextFill(correctColor);
        } else {
            predictieLabel.setText("NU");
            predictieLabel.setTextFill(wrongColor);
        }
    }
}
