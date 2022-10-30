package com.example.mbcoursework;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegisterStage {
    public Stage stage;

//    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterStage.class.getResource("register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 460, 300);
        stage.setTitle("Регистрация");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        launch();
    }

//    @Override
//    public void start(Stage stage) throws Exception {

//    }
}
