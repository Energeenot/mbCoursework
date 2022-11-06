package com.example.mbcoursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    public Hyperlink register;
    @FXML
    public Button enter;
    @FXML
    private Label welcomeText;
    @FXML
    private TextField textField1;
    @FXML
    private PasswordField pas;

    @FXML
    protected void onHelloButtonClick() throws Exception {
        String log = textField1.getText();
        System.out.println(log);
        String pass = pas.getText();
        System.out.println(pass);
        User user = new User(log, pass);
        if (user.entrance()){
            welcomeText.setText("Entering...");
            ViewPatientStage viewPatientStage = new ViewPatientStage();
            Stage stage = (Stage) enter.getScene().getWindow();
            viewPatientStage.start(stage);

        }else {
            welcomeText.setText("The data entering incorrectly");
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            welcomeText.setText("");
        }

    }

    public void onCancelButtonClick(ActionEvent actionEvent) {
        textField1.setText("");
        pas.setText("");

    }

    @FXML
    public void openNewStage(ActionEvent actionEvent) throws Exception {
        RegisterStage registerStage = new RegisterStage();
//        try {
//            registerStage.start();
//        }catch (Exception e){}
//        Stage stage = new Stage();
//        stage.setTitle("Регистрация");

        Stage stage = (Stage) register.getScene().getWindow();
        registerStage.start(stage);
       
        
//        registerStage.stage.show();

//        stage = (Stage) dialog.getDialogPane().getScene().getWindow();
//        stage.getIcons().add(new Image(this.getClass().getResource("C:\\Users\\abram\\Downloads\\login.png").toString()));

    }
}