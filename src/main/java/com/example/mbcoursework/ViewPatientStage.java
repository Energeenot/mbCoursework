package com.example.mbcoursework;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewPatientStage {
    public Stage stage;
    private final ObservableList<Patient> patientData = FXCollections.observableArrayList();
    @FXML
    private TableView<Patient> listTable;
    @FXML
    private TableColumn<Patient, String> fio;
    @FXML
    private TableColumn<Patient, String> dateBirth;
    @FXML
    private Label fioLabel;
    @FXML
    private Label dateBirthLabel;
    @FXML
    private Label area;
    @FXML
    private Label dateApplication;
    @FXML
    private Label complaints;
    @FXML
    private Label diagnosis;




    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(RegisterStage.class.getResource("viewPatient.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 500);
//        stage.getIcons().add(new Image("C:\\Users\\abram\\Downloads\\medical.png"));
        stage.setTitle("Пациенты и информация.");
        stage.setScene(scene);
        stage.show();
//        Patient patient = new Patient("Говорят палка стреляет", "06.11.2022", "Sovetskiy", "01.06.2022, 05.11.2022", "bolit golova i nasmork zadolbal", "prostyda");

    }

    @FXML
    void initialize(){
        try(FileReader fileReader = new FileReader("C:\\Users\\abram\\Desktop\\patients.txt")){
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()){
                String fio = bufferedReader.readLine();
                String dateBirth = bufferedReader.readLine();
                String area = bufferedReader.readLine();
                String dateApplication = bufferedReader.readLine();
                String complaints = bufferedReader.readLine();
                String diagnosis = bufferedReader.readLine();
                patientData.add(new Patient(fio, dateBirth, area, dateApplication, complaints, diagnosis));
            }
        } catch (IOException e){}
//        patientData.add(new Patient("Говорят палка стреляет", "06.11.2022", "Sovetskiy", "01.06.2022, 05.11.2022", "bolit golova i nasmork zadolbal", "prostyda"));
        fio.setCellValueFactory(new PropertyValueFactory<>("fio"));
        dateBirth.setCellValueFactory(new PropertyValueFactory<>("dateBirth"));
        listTable.setItems(patientData);

        showPatientDetails(null);

        listTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPatientDetails(newValue));
    }

    private void showPatientDetails(Patient patient){
        if (patient != null){
            fioLabel.setText(patient.getFio().toString());
            dateBirthLabel.setText(patient.getDateBirth().toString());
            area.setText(patient.getArea().toString());
            dateApplication.setText(patient.getDateApplication().toString());
            complaints.setText(patient.getComplaints().toString());
            diagnosis.setText(patient.getDiagnosis().toString());
        } else {
            fioLabel.setText("");
            dateBirthLabel.setText("");
            area.setText("");
            dateApplication.setText("");
            complaints.setText("");
            diagnosis.setText("");
        }
    }
}
