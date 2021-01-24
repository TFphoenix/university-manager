package gui.sessions.professormenu.discipline;

import daoimpl.ProfessorDaoImpl;
import entities.DisciplineTableEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import util.LoginSession;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DisciplineController implements Initializable {

    // Table
    public TableView<DisciplineTableEntry> tableView;
    public TableColumn<DisciplineTableEntry, String> numeColumn;
    public TableColumn<DisciplineTableEntry, Integer> crediteColumn;
    public TableColumn<DisciplineTableEntry, Integer> anColumn;
    public TableColumn<DisciplineTableEntry, Integer> semestruColumn;

    // Info
    public Label disciplineLabel;
    private int disciplineCount;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Table initialization
        numeColumn.setMinWidth(300);
        numeColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));
        crediteColumn.setMinWidth(50);
        crediteColumn.setCellValueFactory(new PropertyValueFactory<>("credite"));
        anColumn.setMinWidth(50);
        anColumn.setCellValueFactory(new PropertyValueFactory<>("an"));
        semestruColumn.setMinWidth(50);
        semestruColumn.setCellValueFactory(new PropertyValueFactory<>("semestru"));
        tableView.setItems(generateList());

        // Info
        disciplineLabel.setText(String.valueOf(disciplineCount));
    }

    private ObservableList<DisciplineTableEntry> generateList() {
        List<DisciplineTableEntry> list = new ArrayList<>();
        ProfessorDaoImpl pdi = new ProfessorDaoImpl();

        list.addAll(pdi.getDisciplineTableEntries(LoginSession.getEntityId()));

        // Info
        disciplineCount = list.size();

        // Table View List
        return FXCollections.observableArrayList(list);
    }
}
