package gui.sessions.professormenu.note;

import daoimpl.ProfessorDaoImpl;
import entities.ProfessorRegistryTableEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import util.LoginSession;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NoteController implements Initializable {

    // Table
    public TableView<ProfessorRegistryTableEntry> tableView;
    public TableColumn<ProfessorRegistryTableEntry, String> disciplinaColumn;
    public TableColumn<ProfessorRegistryTableEntry, Integer> notaColumn;
    public TableColumn<ProfessorRegistryTableEntry, String> dataNotariiColumn;
    public TableColumn<ProfessorRegistryTableEntry, String> grupaColumn;
    public TableColumn<ProfessorRegistryTableEntry, String> studentColumn;

    // Toolbar
    public TextField gradeTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Table initialization
        disciplinaColumn.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        notaColumn.setCellValueFactory(new PropertyValueFactory<>("nota"));
        dataNotariiColumn.setCellValueFactory(new PropertyValueFactory<>("dataNotarii"));
        grupaColumn.setCellValueFactory(new PropertyValueFactory<>("grupa"));
        studentColumn.setCellValueFactory(new PropertyValueFactory<>("student"));
        updateTableView();
    }

    private void updateTableView() {
        tableView.setItems(generateList());
    }

    private ObservableList<ProfessorRegistryTableEntry> generateList() {
        List<ProfessorRegistryTableEntry> list = new ArrayList<>();
        ProfessorDaoImpl pdi = new ProfessorDaoImpl();

        list.addAll(pdi.getProfessorRegistryTableEntries(LoginSession.getEntityId()));

        // Table View List
        return FXCollections.observableArrayList(list);
    }

    public void onDeleteButton() {
        ObservableList<ProfessorRegistryTableEntry> regSelected, allRegs;
        allRegs = tableView.getItems();
        regSelected = tableView.getSelectionModel().getSelectedItems();

        ProfessorDaoImpl pdi = new ProfessorDaoImpl();
        for (var reg : regSelected) {
            pdi.deleteRegistry(reg.getId());
        }
        regSelected.forEach(allRegs::remove);
    }

    public void onUpdateButton() {
        // Check New Grade Input
        if (gradeTextField.getText().equals("")) {
            return;
        }
        int newGrade;
        try {
            newGrade = Integer.parseInt(gradeTextField.getText());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (newGrade < 1 || newGrade > 10) {
            return;
        }

        // Update
        ObservableList<ProfessorRegistryTableEntry> regSelected;
        regSelected = tableView.getSelectionModel().getSelectedItems();

        ProfessorDaoImpl pdi = new ProfessorDaoImpl();
        for (var reg : regSelected) {
            pdi.updateRegistryGrade(reg.getId(), Integer.parseInt(gradeTextField.getText()));
        }
        updateTableView();
    }
}
