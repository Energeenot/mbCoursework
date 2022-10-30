package com.example.mbcoursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class HelloController {

    @FXML
    public Hyperlink register;
    @FXML
    private Label welcomeText;
    @FXML
    private TextField textField1;
    @FXML
    private PasswordField pas;

    @FXML
    protected void onHelloButtonClick() throws FileNotFoundException {
        String log = textField1.getText();
        System.out.println(log);
        String pass = pas.getText();
        System.out.println(pass);
        User user = new User(log, pass);
        if (user.entrance()){
            welcomeText.setText("Entering...");
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