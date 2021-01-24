package gui.sessions.secretarymenu.adauga;

import daoimpl.StudentDaoImpl;
import daoimpl.UtilizatorDaoImpl;
import entities.Group;
import entities.Student;
import entities.Utilizator;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;

public class AdaugaController implements Initializable {

    // Student
    public ChoiceBox<String> groupChoiceBox;
    public TextField badgeTextField;
    public TextField nameTextField;
    public TextField cnpTextField;
    public TextField domicileTextField;
    public DatePicker birthDateDatePicker;
    public DatePicker registrationDateDatePicker;

    // User
    public TextField mailTextField;
    public TextField passwordTextField;

    // Logical
    private List<Group> groupsList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StudentDaoImpl sdi = new StudentDaoImpl();
        groupsList = sdi.selectAllGroups();

        for (var group : groupsList) {
            groupChoiceBox.getItems().add(group.getCode());
        }

        registrationDateDatePicker.setValue(LocalDate.now());
    }

    public void onAddButtonPressed() {
        // Check for empty cells
        if (groupChoiceBox.getValue().isEmpty() ||
                badgeTextField.getText().isEmpty() ||
                nameTextField.getText().isEmpty() ||
                cnpTextField.getText().isEmpty() ||
                domicileTextField.getText().isEmpty() ||
                birthDateDatePicker.getEditor().getText().isEmpty() ||
                registrationDateDatePicker.getEditor().getText().isEmpty() ||
                mailTextField.getText().isEmpty() ||
                passwordTextField.getText().isEmpty()) {
            return;
        }

        // Find chosen group
        int chosenGroupId = 0;
        for (var group : groupsList) {
            if (group.getCode().equals(groupChoiceBox.getValue())) {
                chosenGroupId = group.getId();
            }
        }

        // Build new student
        Student newStudent = new Student();
        newStudent.setBadge(badgeTextField.getText());
        newStudent.setName(nameTextField.getText());
        newStudent.setCnp(cnpTextField.getText());
        newStudent.setDomicile(domicileTextField.getText());
        newStudent.setDateOfBirth(java.util.Date.from(birthDateDatePicker.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        newStudent.setDateOfRegistration(java.util.Date.from(registrationDateDatePicker.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        newStudent.setIdGrupa(chosenGroupId);

        // Build new user
        Utilizator newUtilizator = new Utilizator();
        newUtilizator.setMail(mailTextField.getText());
        newUtilizator.setParola(passwordTextField.getText());
        newUtilizator.setDrepturi("student");

        // Add new user to the DB
        UtilizatorDaoImpl udi = new UtilizatorDaoImpl();
        udi.insert(newUtilizator);
        int utilizatorId = udi.selectByMail(newUtilizator.getMail()).getId();

        // Add new student to the DB
        StudentDaoImpl sdi = new StudentDaoImpl();
        newStudent.setIdUtilizator(utilizatorId);
        sdi.insert(newStudent);
    }

}
