package gui.sessions.professormenu.adauga;

import daoimpl.ProfessorDaoImpl;
import daoimpl.StudentDaoImpl;
import entities.Group;
import entities.Registry;
import entities.Student;
import entities.Subject;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import util.LoginSession;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AdaugaController implements Initializable {

    // GUI
    public ChoiceBox<String> disciplinaChoice;
    public ChoiceBox<String> grupaChoice;
    public ChoiceBox<String> studentChoice;
    public ChoiceBox<String> notaChoice;
    public DatePicker dataChoice;
    public Button addButton;

    // Logical
    private Subject selectedSubject;
    private Group selectedGroup;
    private Student selectedStudent;
    private List<Subject> disciplineList;
    private List<Group> grupeList;
    private List<Student> studentiList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Professor DAO
        ProfessorDaoImpl pdi = new ProfessorDaoImpl();

        // Populate choices
        disciplineList = pdi.getSubjects(LoginSession.getEntityId());
        for (var disciplina : disciplineList) {
            disciplinaChoice.getItems().add(disciplina.getName());
        }
        notaChoice.getItems().add("1");
        notaChoice.getItems().add("2");
        notaChoice.getItems().add("3");
        notaChoice.getItems().add("4");
        notaChoice.getItems().add("5");
        notaChoice.getItems().add("6");
        notaChoice.getItems().add("7");
        notaChoice.getItems().add("8");
        notaChoice.getItems().add("9");
        notaChoice.getItems().add("10");

        // Disable choices
        grupaChoice.setDisable(true);
        studentChoice.setDisable(true);
        notaChoice.setDisable(true);
        dataChoice.setDisable(true);
        addButton.setDisable(true);

        // Set events for choices
        disciplinaChoice.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> onDisciplinaSelected());
        grupaChoice.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> onGrupaSelected());
        studentChoice.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> onStudentSelected());
        notaChoice.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> onNotaSelected());
    }

    private void onDisciplinaSelected() {
        // Identify selected subject
        selectedSubject = new Subject();
        for (var disciplina : disciplineList) {
            if (disciplina.getName().equals(disciplinaChoice.getValue())) {
                selectedSubject = disciplina;
            }
        }

        // Populate choices
        ProfessorDaoImpl pdi = new ProfessorDaoImpl();
        grupaChoice.getItems().clear();
        grupeList = pdi.getGroupsBySubject(selectedSubject.getId());
        for (var grupa : grupeList) {
            grupaChoice.getItems().add(grupa.getCode());
        }

        // Enable
        grupaChoice.setDisable(false);
    }

    private void onGrupaSelected() {
        // Identify selected grupa
        selectedGroup = new Group();
        for (var grupa : grupeList) {
            if (grupa.getCode().equals(grupaChoice.getValue())) {
                selectedGroup = grupa;
            }
        }

        // Populate choices
        StudentDaoImpl sdi = new StudentDaoImpl();
        studentChoice.getItems().clear();
        studentiList = sdi.selectByGroupId(selectedGroup.getId());
        for (var student : studentiList) {
            studentChoice.getItems().add(student.getName());
        }

        // Enable
        studentChoice.setDisable(false);
    }

    private void onStudentSelected() {
        // Identify selected subject
        selectedStudent = new Student();
        for (var student : studentiList) {
            if (student.getName().equals(studentChoice.getValue())) {
                selectedStudent = student;
            }
        }

        // Enable
        notaChoice.setDisable(false);
    }

    private void onNotaSelected() {

        addButton.setDisable(false);
        dataChoice.setDisable(false);

        // data notarii implicit TODAY
        dataChoice.setValue(LocalDate.now());
    }

    public void onAddButtonPressed() {
        ProfessorDaoImpl pdi = new ProfessorDaoImpl();
        Registry reg = new Registry(0,
                Integer.parseInt(notaChoice.getValue()),
                java.util.Date.from(dataChoice.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                selectedSubject.getId(),
                selectedStudent.getId());
        pdi.insertRegistry(reg);
    }

}
