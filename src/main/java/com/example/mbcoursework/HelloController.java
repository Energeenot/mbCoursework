package com.example.mbcoursework;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField textField2;
    @FXML
    private Label welcomeText;
    @FXML
    private TextField textField1;

    @FXML
    protected void onHelloButtonClick() {
        String log = textField1.getText();
        System.out.println(log);
        String pass = textField2.getText();
        System.out.println(pass);
        User user = new User(log, pass);
        if (user.entrance()){
            welcomeText.setText("Entering...");
        }else welcomeText.setText("The data entering incorrectly");

    }
}