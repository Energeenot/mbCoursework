package com.example.mbcoursework;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class RegisterStage {
    public Stage stage;

//    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterStage.class.getResource("register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 460, 300);
//        stage.getIcons().add(new Image("C:\\Users\\abram\\Downloads\\registered.png"));
        stage.setTitle("Регистрация");
        stage.setScene(scene);
        stage.show();
    }
}
