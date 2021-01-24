package gui.sessions.studentmenu.situatie;

import daoimpl.StudentDaoImpl;
import entities.RegistryTableEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import util.LoginSession;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SituatieController implements Initializable {

    // Filters
    public RadioMenuItem generalView;
    public RadioMenuItem selectiveView;
    public Menu yearMenu;
    public Menu semesterMenu;
    public CheckMenuItem year1;
    public CheckMenuItem year2;
    public CheckMenuItem year3;
    public CheckMenuItem year4;
    public CheckMenuItem semester1;
    public CheckMenuItem semester2;

    // Table
    public TableView<RegistryTableEntry> tableView;
    public TableColumn<RegistryTableEntry, String> materieColumn;
    public TableColumn<RegistryTableEntry, Integer> crediteColumn;
    public TableColumn<RegistryTableEntry, String> profesorColumn;
    public TableColumn<RegistryTableEntry, String> statusColumn;
    public TableColumn<RegistryTableEntry, String> notaColumn;
    public TableColumn<RegistryTableEntry, String> dataNotariiColumn;
    public TableColumn<RegistryTableEntry, Integer> anColumn;
    public TableColumn<RegistryTableEntry, Integer> semestruColumn;

    // Info
    public Label medieLabel;
    public Label restanteLabel;
    public Label nenotateLabel;
    private Color correctColor;
    private Color wrongColor;
    private Color intermediateColor;
    private int restante;
    private float medie;
    private int nenotate;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Filters initialization
        ToggleGroup viewToggleGroup = new ToggleGroup();
        generalView.setSelected(true);
        generalView.setToggleGroup(viewToggleGroup);
        selectiveView.setToggleGroup(viewToggleGroup);
        yearMenu.setDisable(true);
        semesterMenu.setDisable(true);

        // Table initialization
        materieColumn.setMinWidth(200);
        materieColumn.setCellValueFactory(new PropertyValueFactory<>("materie"));
        crediteColumn.setMinWidth(50);
        crediteColumn.setCellValueFactory(new PropertyValueFactory<>("credite"));
        profesorColumn.setMinWidth(100);
        profesorColumn.setCellValueFactory(new PropertyValueFactory<>("profesor"));
        statusColumn.setMinWidth(50);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        notaColumn.setMinWidth(50);
        notaColumn.setCellValueFactory(new PropertyValueFactory<>("nota"));
        dataNotariiColumn.setMinWidth(100);
        dataNotariiColumn.setCellValueFactory(new PropertyValueFactory<>("dataNotarii"));
        anColumn.setMinWidth(50);
        anColumn.setCellValueFactory(new PropertyValueFactory<>("an"));
        semestruColumn.setMinWidth(50);
        semestruColumn.setCellValueFactory(new PropertyValueFactory<>("semestru"));
        updateTableView();

        // Info
        correctColor = Color.DARKGREEN;
        wrongColor = Color.DARKRED;
        intermediateColor = Color.DARKORANGE;
        updateInfo();
    }

    public void onSelectiveView() {
        yearMenu.setDisable(false);
        semesterMenu.setDisable(false);
        updateTableView();
        updateInfo();
    }

    public void onGeneralView() {
        yearMenu.setDisable(true);
        semesterMenu.setDisable(true);
        updateTableView();
        updateInfo();
    }

    public void updateTableView() {

        if (generalView.isSelected()) {
            tableView.setItems(generateList());
        } else {
            ArrayList<Integer> years = new ArrayList<>();
            ArrayList<Integer> semesters = new ArrayList<>();

            // years
            if (year1.isSelected()) {
                years.add(1);
            }
            if (year2.isSelected()) {
                years.add(2);
            }
            if (year3.isSelected()) {
                years.add(3);
            }
            if (year4.isSelected()) {
                years.add(4);
            }

            // semesters
            if (semester1.isSelected()) {
                semesters.add(1);
            }
            if (semester2.isSelected()) {
                semesters.add(2);
            }

            tableView.setItems(generateList(years, semesters));
        }

    }


    private void updateInfo() {

        // Medie
        medieLabel.setText(String.valueOf(medie));
        if (medie < 5) {
            medieLabel.setTextFill(wrongColor);
        } else if (medie >= 9) {
            medieLabel.setTextFill(correctColor);
        } else {
            medieLabel.setTextFill(intermediateColor);
        }

        // Restante
        restanteLabel.setText(String.valueOf(restante));
        if (restante == 0) {
            restanteLabel.setTextFill(correctColor);
        } else {
            restanteLabel.setTextFill(wrongColor);
        }

        // Nenotate
        nenotateLabel.setText(String.valueOf(nenotate));
        if (nenotate == 0) {
            nenotateLabel.setTextFill(correctColor);
        } else {
            nenotateLabel.setTextFill(intermediateColor);
        }
    }

    // General View
    private ObservableList<RegistryTableEntry> generateList() {
        List<RegistryTableEntry> list = new ArrayList<>();
        StudentDaoImpl sdi = new StudentDaoImpl();

        for (int year = 1; year <= 4; ++year) {
            for (int semester = 1; semester <= 2; ++semester) {
                list.addAll(sdi.getStudentGradedRegistryTableEntries(LoginSession.getEntityId(), year, semester));
                list.addAll(sdi.getStudentUngradedRegistryTableEntries(LoginSession.getEntityId(), year, semester));
            }
        }

        // Info
        restante = 0;
        nenotate = 0;
        medie = 0;
        int notate = 0;
        for (var element : list) {
            if (element.getNota().equals("?")) {
                ++nenotate;
            } else {
                ++notate;
                int nota = Integer.parseInt(element.getNota());
                if (nota < 5) {
                    ++restante;
                }
                medie += nota;
            }
        }
        medie = medie / notate;

        // Table View List
        return FXCollections.observableArrayList(list);
    }

    // Selective View
    private ObservableList<RegistryTableEntry> generateList(ArrayList<Integer> years, ArrayList<Integer> semesters) {
        List<RegistryTableEntry> list = new ArrayList<>();
        StudentDaoImpl sdi = new StudentDaoImpl();

        for (int year = 1; year <= 4; ++year) {
            for (int selectedYear : years) {
                if (year == selectedYear) {
                    for (int semester = 1; semester <= 2; ++semester) {
                        for (int selectedSemester : semesters) {
                            if (selectedSemester == semester) {
                                list.addAll(sdi.getStudentGradedRegistryTableEntries(LoginSession.getEntityId(), selectedYear, selectedSemester));
                                list.addAll(sdi.getStudentUngradedRegistryTableEntries(LoginSession.getEntityId(), selectedYear, selectedSemester));
                            }
                        }
                    }
                }
            }
        }

        // Info
        restante = 0;
        nenotate = 0;
        medie = 0;
        int notate = 0;
        for (var element : list) {
            if (element.getNota().equals("?")) {
                ++nenotate;
            } else {
                ++notate;
                int nota = Integer.parseInt(element.getNota());
                if (nota < 5) {
                    ++restante;
                }
                medie += nota;
            }
        }
        medie = medie / notate;

        // Table View List
        return FXCollections.observableArrayList(list);
    }

}
