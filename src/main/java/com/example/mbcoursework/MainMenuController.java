package com.example.mbcoursework;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.Objects;


import static com.example.mbcoursework.ViewPatientStage.patientData;

public class MainMenuController{

    @FXML
    private Tab catalogTab;
    @FXML
    private Tab tabOrder;
    @FXML
    private TabPane tabPanel;
    @FXML
    private ComboBox<String>comboBox1;
    ObservableList<String> list = FXCollections.observableArrayList();
    protected static final ObservableList<Order> orderData = FXCollections.observableArrayList();
    protected static final ObservableList<Order> orderNCAData = FXCollections.observableArrayList();
    @FXML
    private Label numberOrder;
    @FXML
    private Label amountOrder;
    @FXML
    private TableColumn<Product, String> fioCell;
    @FXML
    private TableColumn<Product, String> numberOrderCell;
    @FXML
    private TableView<Order> tableNumberAndFio;
    @FXML
    private TableView<Order> tableNCA;
    @FXML
    private TableColumn<Order, String> name;
    @FXML
    private TableColumn<Order, String> count;
    @FXML
    private TableColumn<Order, String> amount;
    @FXML
    private void selectCatalogTab(ActionEvent actionEvent){
        tabPanel.getSelectionModel().select(catalogTab);
    }
    @FXML
    private void selectOrderTab(ActionEvent actionEvent){
        tabPanel.getSelectionModel().select(tabOrder);
    }

    @FXML
    private void patientsNewWindow(ActionEvent actionEvent){
//        ViewPatientStage.listTable.getItems().clear();
        Stage stage = new Stage();
        stage.setTitle("Пациенты");
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("viewPatient.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleNewPatient(ActionEvent actionEvent) throws IOException{
        ViewPatientStage viewPatientStage = new ViewPatientStage();

        Patient tempPatient = new Patient();
        boolean saveClicked = showPatientEditDialog(tempPatient);
        if (saveClicked){
            patientData.add(tempPatient);
            viewPatientStage.getListTable().setItems(patientData);
//            listTable.setItems(patientData);
            try (FileWriter fileWriter = new FileWriter("C:\\Users\\abram\\Desktop\\patients.txt");
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){
                for (Patient patient : viewPatientStage.getListTable().getItems()){
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
    private void aboutProgram(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("О программе");
        alert.setHeaderText("Программа учебная. Версия 1.0");
        alert.showAndWait();
    }

public void start(Stage stage) throws  IOException{
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainMenu.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 700, 400);
    stage.getIcons().add(new Image("C:\\Users\\abram\\Downloads\\medical.png"));
//    stage.setTitle("Вход");
    stage.setScene(scene);
    stage.setTitle("Пациенты и точка.");
    stage.show();
}

    public void initialize() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("viewPatient.fxml"));
        try{
            catalogTab.setContent(loader.load());
        }catch (IOException e){}
        comboBox1.setDisable(true);

        try(FileReader fileReader = new FileReader("C:\\Users\\abram\\Desktop\\order.txt")){
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()){
                String numberOrderCell = bufferedReader.readLine();
                String fioCell = bufferedReader.readLine();
                bufferedReader.readLine();
                bufferedReader.readLine();
                bufferedReader.readLine();
                orderData.add(new Order(numberOrderCell, fioCell));
            }
        } catch (IOException e){}

        numberOrderCell.setCellValueFactory(new PropertyValueFactory<>("numberOrderCell"));
        fioCell.setCellValueFactory(new PropertyValueFactory<>("fioCell"));
        tableNumberAndFio.setItems(orderData);

        showOrderDetails(null);
        tableNumberAndFio.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showOrderDetails(newValue));
    }

    @FXML
    private void changeStat(){
        comboBox1.setDisable(false);
        list.add("Выполнено");
        list.add("Ждёт выполнения");
        comboBox1.setItems(list);
    }

    private void showOrderDetails(Order order){
        if (order != null){
            int summa = 0;
            numberOrder.setText(order.getNumberOrderCell());
            ObservableList orderNCAData = FXCollections.observableArrayList();
            try(FileReader fileReader = new FileReader("C:\\Users\\abram\\Desktop\\order.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader)){
                while (bufferedReader.ready()){
                    if (Objects.equals(order.getNumberOrderCell(), bufferedReader.readLine())){
                        bufferedReader.readLine();
                        String[] name = bufferedReader.readLine().split(" ");
                        String[] count = bufferedReader.readLine().split(" ");
                        String[] amount = bufferedReader.readLine().split(" ");

                        for (int i = 0; i < name.length; i++){
                            summa += Integer.parseInt(amount[i]);
                            orderNCAData.add(new Order(name[i], count[i], amount[i]));
                        }
                    }
                    else {
                        bufferedReader.readLine();
                        bufferedReader.readLine();
                        bufferedReader.readLine();
                        bufferedReader.readLine();
                    }
                }
                name.setCellValueFactory(new PropertyValueFactory<>("name"));
                count.setCellValueFactory(new PropertyValueFactory<>("count"));
                amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
                tableNCA.setItems(orderNCAData);
                amountOrder.setText(String.valueOf(summa));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //TODO: (необязательно) потыкать бд(sql or mysql) или какие там требуют на джуниор разраба
    //TODO: анализ заболеваемости по месяцам и районам (при нажатии на кнопку анализ должно открыться чек бокс в котором я выберу месяц или район и дальше в методе считывать с файла либо одно либо другое и в алерт сую эти данные
}
