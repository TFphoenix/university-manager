package gui.sessions.secretarymenu.burse;

import daoimpl.StudentDaoImpl;
import entities.Group;
import entities.Scholarship;
import entities.ScholarshipTableEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BurseController implements Initializable {

    // Info
    public Label burseMeritLabel;
    public Label burseSpecialeLabel;

    // Table
    public TableView<ScholarshipTableEntry> tableView;
    public TableColumn<ScholarshipTableEntry, String> grupaTableColumn;
    public TableColumn<ScholarshipTableEntry, Integer> burseMeritTableColumn;
    public TableColumn<ScholarshipTableEntry, Integer> burseSpecialeTableColumn;

    // Toolbar
    public ChoiceBox<String> grupeChoiceBox;
    public TextField burseMeritTextField;
    public TextField burseSpecialeTextField;

    // Logical
    private List<Group> groupsList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Table initialization
        grupaTableColumn.setCellValueFactory(new PropertyValueFactory<>("grupa"));
        burseMeritTableColumn.setCellValueFactory(new PropertyValueFactory<>("gradeScolarshipCount"));
        burseSpecialeTableColumn.setCellValueFactory(new PropertyValueFactory<>("specialScolarshipCount"));

        // ChoiceBox initialization
        StudentDaoImpl sdi = new StudentDaoImpl();
        groupsList = sdi.selectAllGroups();
        for (var group : groupsList) {
            grupeChoiceBox.getItems().add(group.getCode());
        }

        // Updates
        updateTableView();
        updateInfo();
    }

    private void updateTableView() {
        tableView.setItems(generateList());
    }

    private void updateInfo() {
        int gradeScholarshipCount = 0;
        int specialScholarshipCount = 0;

        var items = tableView.getItems();
        for (var item : items) {
            gradeScholarshipCount += item.getGradeScolarshipCount();
            specialScholarshipCount += item.getSpecialScolarshipCount();
        }

        burseMeritLabel.setText(Integer.toString(gradeScholarshipCount));
        burseSpecialeLabel.setText(Integer.toString(specialScholarshipCount));
    }

    public void onDeleteButton() {
        ObservableList<ScholarshipTableEntry> regSelected, allRegs;
        allRegs = tableView.getItems();
        regSelected = tableView.getSelectionModel().getSelectedItems();

        StudentDaoImpl sdi = new StudentDaoImpl();
        for (var reg : regSelected) {
            sdi.deleteScholarship(reg.getId());
        }
        regSelected.forEach(allRegs::remove);
        updateInfo();
    }

    public void onAddButton() {
        // Verify
        if (grupeChoiceBox.getValue().isEmpty() ||
                burseMeritTextField.getText().isEmpty() ||
                burseSpecialeLabel.getText().isEmpty()) {
            return;
        }

        // Insert
        StudentDaoImpl sdi = new StudentDaoImpl();
        Scholarship scholarship = new Scholarship();
        for (var group : groupsList) {
            if (group.getCode().equals(grupeChoiceBox.getValue())) {
                scholarship.setGrupaId(group.getId());
            }
        }
        scholarship.setGradeSCount(Integer.parseInt(burseMeritTextField.getText()));
        scholarship.setSpecialSCount(Integer.parseInt(burseSpecialeTextField.getText()));
        sdi.insertScholarship(scholarship);

        // Update
        updateTableView();
        updateInfo();
    }

    private ObservableList<ScholarshipTableEntry> generateList() {
        StudentDaoImpl sdi = new StudentDaoImpl();

        List<ScholarshipTableEntry> list = new ArrayList<>(sdi.getScholarshipTableEntries());

        // Table View List
        return FXCollections.observableArrayList(list);
    }
}
