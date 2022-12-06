package com.example.mbcoursework;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;


public class ViewPatientStage {
    public Stage stage;
    protected static final ObservableList<Patient> patientData = FXCollections.observableArrayList();
    @FXML
    public TableView<Patient> listTable;
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
    @FXML
    private Label medic;
    @FXML
    private TextField searchFio;




    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(RegisterStage.class.getResource("viewPatient.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 500);
        stage.getIcons().add(new Image("C:\\Users\\abram\\Downloads\\medical.png"));
        stage.setTitle("Пациенты и информация.");
        stage.setScene(scene);
        stage.show();

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
                String medic = bufferedReader.readLine();
                patientData.add(new Patient(fio, dateBirth, area, dateApplication, complaints, diagnosis, medic));
            }
        } catch (IOException e){}
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
            medic.setText(patient.getMedic().toString());
        } else {
            fioLabel.setText("");
            dateBirthLabel.setText("");
            area.setText("");
            dateApplication.setText("");
            complaints.setText("");
            diagnosis.setText("");
            medic.setText("");
        }
    }

    public boolean showPatientEditDialog(Patient patient){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EditSceneController.class.getResource("editScene.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование данных");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(null);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            EditSceneController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPatient(patient);

            dialogStage.showAndWait();
            return controller.isSaveClicked();

        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void handleNewPatient() throws IOException{
        Patient tempPatient = new Patient();
        boolean saveClicked = showPatientEditDialog(tempPatient);
        if (saveClicked){
            patientData.add(tempPatient);
            listTable.setItems(patientData);
            try (FileWriter fileWriter = new FileWriter("C:\\Users\\abram\\Desktop\\patients.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){
                for (Patient patient : listTable.getItems()){
                    bufferedWriter.write(patient.getFio() + "\n");
                    bufferedWriter.write(patient.getDateApplication() + "\n");
                    bufferedWriter.write(patient.getArea() + "\n");
                    bufferedWriter.write(patient.getDateApplication() + "\n");
                    bufferedWriter.write(patient.getComplaints() + "\n");
                    bufferedWriter.write(patient.getDiagnosis() + "\n");
                    bufferedWriter.write(patient.getMedic() + "\n");
                }
            }
            catch(IOException e){}
        }
    }

    @FXML
    private void handleEditPatient(){
        Patient selectedPatient = listTable.getSelectionModel().getSelectedItem();
        if (selectedPatient != null){
            boolean saveClicked = showPatientEditDialog(selectedPatient);
            if (saveClicked){
                showPatientDetails(selectedPatient);
                int selectedIndex = listTable.getSelectionModel().getSelectedIndex();
                patientData.set(selectedIndex, selectedPatient);
                try (FileWriter fileWriter = new FileWriter("C:\\Users\\abram\\Desktop\\patients.txt");
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);){
                    for (Patient patient : listTable.getItems()){
                        bufferedWriter.write(patient.getFio() + "\n");
                        bufferedWriter.write(patient.getDateBirth() + "\n");
                        bufferedWriter.write(patient.getArea() + "\n");
                        bufferedWriter.write(patient.getDateApplication() + "\n");
                        bufferedWriter.write(patient.getComplaints() + "\n");
                        bufferedWriter.write(patient.getDiagnosis() + "\n");
                        bufferedWriter.write(patient.getMedic() + "\n");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("Никто не выбран");
            alert.setHeaderText("Нет выбранного пациента");
            alert.setContentText("Выберите пациента в таблице");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeletePatient(){
        int selectedIndex = listTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            listTable.getItems().remove(selectedIndex);
            try
                (FileWriter fileWriter = new FileWriter("C:\\Users\\abram\\Desktop\\patients.txt");
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);){
                for (Patient patient : listTable.getItems()){
                    bufferedWriter.write(patient.getFio() + "\n");
                    bufferedWriter.write(patient.getDateBirth() + "\n");
                    bufferedWriter.write(patient.getArea() + "\n");
                    bufferedWriter.write(patient.getDateApplication() + "\n");
                    bufferedWriter.write(patient.getComplaints() + "\n");
                    bufferedWriter.write(patient.getDiagnosis() + "\n");
                    bufferedWriter.write(patient.getMedic() + "\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("Не выделен");
            alert.setHeaderText("Не выбран пациент");
            alert.setContentText("Выберите пациента в таблице");
            alert.showAndWait();
        }
    }

    @FXML
    private void searchPatient(){
        String searchable = searchFio.getText();
        boolean found = false;
        int count = 0;
        try(FileReader fileReader = new FileReader("C:\\Users\\abram\\Desktop\\patients.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while (bufferedReader.ready()){
                count++;
                if (searchable.equals(bufferedReader.readLine())){
                    found = true;
                    Patient tempPatient = new Patient(searchable, bufferedReader.readLine().toString(),
                            bufferedReader.readLine().toString(), bufferedReader.readLine().toString(),
                            bufferedReader.readLine().toString(), bufferedReader.readLine().toString(),
                            bufferedReader.readLine().toString());
                    showPatientDetails(tempPatient);
                    //TODO: спросить  или нагуглить как програмно выбрать строку из tableview, чтобы не было проблемы пустого пациента
                    break;
                }
                else{
                    bufferedReader.readLine();
                    bufferedReader.readLine();
                    bufferedReader.readLine();
                    bufferedReader.readLine();
                    bufferedReader.readLine();
                    bufferedReader.readLine();
                }
            }
            if (!found){
                System.out.println("Данный пациент не найдена");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Пациент не найден");
                alert.setContentText("Пациент с таким ФИО не найден.");
                alert.showAndWait();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        searchFio.setText("");
    }

    @FXML
    private void issueCertificate(){
        Patient selectedPatient = listTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Справка");
        alert.setHeaderText("Настоящая справка");
        alert.setContentText(selectedPatient.toString() + "\n" +
                "  _____ " + "\n" +
                "/          \\ " + "\n" +
                "|печать|" + "\n" +
                "\\         /" + "\n" +
                " ------ ");
        alert.showAndWait();
    }
}
