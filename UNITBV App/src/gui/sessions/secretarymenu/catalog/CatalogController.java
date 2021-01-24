package gui.sessions.secretarymenu.catalog;

import daoimpl.ProfessorDaoImpl;
import entities.ProfessorRegistryTableEntry;
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

public class CatalogController implements Initializable {

    // Table
    public TableView<ProfessorRegistryTableEntry> tableView;
    public TableColumn<ProfessorRegistryTableEntry, String> disciplinaColumn;
    public TableColumn<ProfessorRegistryTableEntry, Integer> notaColumn;
    public TableColumn<ProfessorRegistryTableEntry, String> dataNotariiColumn;
    public TableColumn<ProfessorRegistryTableEntry, String> grupaColumn;
    public TableColumn<ProfessorRegistryTableEntry, String> studentColumn;
    public TableColumn<ProfessorRegistryTableEntry, String> profesorColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Table initialization
        disciplinaColumn.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        notaColumn.setCellValueFactory(new PropertyValueFactory<>("nota"));
        dataNotariiColumn.setCellValueFactory(new PropertyValueFactory<>("dataNotarii"));
        grupaColumn.setCellValueFactory(new PropertyValueFactory<>("grupa"));
        studentColumn.setCellValueFactory(new PropertyValueFactory<>("student"));
        profesorColumn.setCellValueFactory(new PropertyValueFactory<>("profesor"));
        updateTableView();
    }

    private void updateTableView() {
        tableView.setItems(generateList());
    }

    private ObservableList<ProfessorRegistryTableEntry> generateList() {
        ProfessorDaoImpl pdi = new ProfessorDaoImpl();
        List<ProfessorRegistryTableEntry> list = new ArrayList<>(pdi.getAllProfessorRegistryTableEntries());

        // Table View List
        return FXCollections.observableArrayList(list);
    }

}
