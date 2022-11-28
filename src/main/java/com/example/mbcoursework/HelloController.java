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

            MainMenuController mainMenuController = new MainMenuController();
            Stage stage = (Stage) enter.getScene().getWindow();
            mainMenuController.start(stage);
//
//            ViewPatientStage viewPatientStage = new ViewPatientStage();
//            Stage stage = (Stage) enter.getScene().getWindow();
//            viewPatientStage.start(stage);

        }else {
            welcomeText.setText("Данные введены не правильно");
        }
    }

    public void onCancelButtonClick(ActionEvent actionEvent) {
        textField1.setText("");
        pas.setText("");

    }

    @FXML
    public void openNewStage(ActionEvent actionEvent) throws Exception {
        RegisterStage registerStage = new RegisterStage();
        Stage stage = (Stage) register.getScene().getWindow();
        registerStage.start(stage);
    }
}