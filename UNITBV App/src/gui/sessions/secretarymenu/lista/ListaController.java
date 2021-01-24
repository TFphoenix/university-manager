package gui.sessions.secretarymenu.lista;

import daoimpl.StudentDaoImpl;
import daoimpl.UtilizatorDaoImpl;
import entities.StudentTableEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListaController implements Initializable {

    // Table
    public TableView<StudentTableEntry> tableView;
    public TableColumn<StudentTableEntry, String> badgeColumn;
    public TableColumn<StudentTableEntry, String> nameColumn;
    public TableColumn<StudentTableEntry, String> cnpColumn;
    public TableColumn<StudentTableEntry, String> domicileColumn;
    public TableColumn<StudentTableEntry, String> dateOfBirthColumn;
    public TableColumn<StudentTableEntry, String> dateOfRegistrationColumn;
    public TableColumn<StudentTableEntry, String> mailColumn;
    public TableColumn<StudentTableEntry, String> groupColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Table initialization
        badgeColumn.setCellValueFactory(new PropertyValueFactory<>("badge"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cnpColumn.setCellValueFactory(new PropertyValueFactory<>("cnp"));
        domicileColumn.setCellValueFactory(new PropertyValueFactory<>("domicile"));
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        dateOfRegistrationColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfRegistration"));
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));

        updateTableView();
    }

    private void updateTableView() {
        tableView.setItems(generateList());
    }

    private ObservableList<StudentTableEntry> generateList() {
        StudentDaoImpl sdi = new StudentDaoImpl();

        List<StudentTableEntry> list = new ArrayList<>(sdi.getAllStudentTableEntries());

        // Table View List
        return FXCollections.observableArrayList(list);
    }

    public void onDeleteButton() {
        ObservableList<StudentTableEntry> regSelected, allRegs;
        allRegs = tableView.getItems();
        regSelected = tableView.getSelectionModel().getSelectedItems();

        UtilizatorDaoImpl udi = new UtilizatorDaoImpl();
        for (var reg : regSelected) {
            udi.delete(reg.getUtilizatorId());
        }
        regSelected.forEach(allRegs::remove);
    }

}
