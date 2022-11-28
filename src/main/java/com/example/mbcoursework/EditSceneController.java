package com.example.mbcoursework;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditSceneController{

    @FXML
    private TextField fio;
    @FXML
    private TextField dateBirth;
    @FXML
    private TextField area;
    @FXML
    private TextField dateApplication;
    @FXML
    private TextField complaints;
    @FXML
    private TextField diagnosis;
    @FXML
    private TextField medic;
    @FXML
    private TableColumn<Product, String> fioCell;
    @FXML
    private TableColumn<Product, String> numberOrderCell;

    private Stage dialogStage;
    private Patient patient = new Patient("", "", "", "", "", "", "");
    private boolean saveClicked = false;

    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    public void setPatient(Patient patient){
        this.patient = patient;

        fio.setText(patient.getFio());
        dateBirth.setText(patient.getDateBirth());
        area.setText(patient.getArea());
        dateApplication.setText(patient.getDateApplication());
        complaints.setText(patient.getComplaints());
        diagnosis.setText(patient.getDiagnosis());
        medic.setText(patient.getMedic());
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    private boolean isInputValid(){
        String errorMessage = "";

        if (fio.getText() == null || fio.getText().length() == 0){
            errorMessage += "Поле ФИО не может быть пустым\n";
        }
        if (dateBirth.getText() == null || dateBirth.getText().length() == 0){
            errorMessage += "Поле 'Дата рождения' не может быть пустым\n";
        }
        if (area.getText() == null || area.getText().length() == 0){
            errorMessage += "Поле 'Район' не может быть пустым\n";
        }
        if (dateApplication.getText() == null || dateApplication.getText().length() == 0){
            errorMessage += "Поле 'Даты обращений' не может быть пустым\n";
        }
        if (complaints.getText() == null || complaints.getText().length() == 0){
            errorMessage += "Поле 'Жалобы' не может быть пустым\n";
        }
        if (diagnosis.getText() == null || diagnosis.getText().length() == 0){
            errorMessage += "Поле 'Диагноз' не может быть пустым\n";
        }
        if (medic.getText() == null || medic.getText().length() == 0){
            errorMessage += "Поле 'Врач' не может быть пустым\n";
        }

        if (errorMessage.length() == 0){
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Некорректные поля");
            alert.setHeaderText("Внесите корректную информацию");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;

        }
    }

    @FXML
    private void handleSave(){
        if (isInputValid()){
            patient.setFio(fio.getText());
            patient.setDateBirth(dateBirth.getText());
            patient.setArea(area.getText());
            patient.setDateApplication(dateApplication.getText());
            patient.setComplaints(complaints.getText());
            patient.setDiagnosis(diagnosis.getText());
            patient.setMedic(medic.getText());

//            Patient.addPatient(patient);
//            ViewPatientStage.patientData.add(patient);
            saveClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    @FXML
    public void initialize() {
//        try(FileReader fileReader = new FileReader("C:\\Users\\abram\\Desktop\\patients.txt")){
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            while (bufferedReader.ready()){
//                String fio = bufferedReader.readLine();
//                String dateBirth = bufferedReader.readLine();
//                String area = bufferedReader.readLine();
//                String dateApplication = bufferedReader.readLine();
//                String complaints = bufferedReader.readLine();
//                String diagnosis = bufferedReader.readLine();
//                String medic = bufferedReader.readLine();
//                patientData.add(new Patient(fio, dateBirth, area, dateApplication, complaints, diagnosis, medic));
//            }
//        } catch (IOException e){}

//        showPatientDetails(null);

//        listTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPatientDetails(newValue));
    }
}
