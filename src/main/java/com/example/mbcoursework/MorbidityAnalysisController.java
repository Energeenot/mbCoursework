package com.example.mbcoursework;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class MorbidityAnalysisController {
    @FXML
    private Button cancel;
    @FXML
    private Button byMonth;
    @FXML
    private Button byDistricts;
    String[] counts;

    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MorbidityAnalysisController.class.getResource("MorbidityAnalysis.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 460, 300);
        stage.getIcons().add(new Image("C:\\Users\\abram\\Downloads\\medical.png"));
        stage.setTitle("Анализ");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected  void onBackButtonClick() throws IOException {
        MainMenuController mainMenuController= new MainMenuController();
        Stage stage = (Stage) cancel.getScene().getWindow();
        mainMenuController.start(stage);
    }

    @FXML
    protected  void onByMonthClick()throws IOException{
        int[] count1 = {0,0,0,0,0,0,0,0,0,0,0,0};
        try(FileReader fileReader = new FileReader("C:\\Users\\abram\\Desktop\\patients.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader)){
            while (bufferedReader.ready()){
                bufferedReader.readLine();
                bufferedReader.readLine();
                bufferedReader.readLine();
                String[] gg = bufferedReader.readLine().split("\\.");
                for (int i = 0; i < count1.length+1; i++){
                    if (Integer.parseInt(gg[1]) == i){
                        count1[i-1]++;
                    }
                }
//                if (Integer.parseInt(gg[1]) == 1){
//                    count1[0]++;
//                }
//                if (Integer.parseInt(gg[1]) == 2){
//                    count1[1]++;
//                }
//                if (Integer.parseInt(gg[1]) == 3){
//                    count1[2]++;
//                }
//                if (Integer.parseInt(gg[1]) == 4){
//                    count1[3]++;
//                }
//                if (Integer.parseInt(gg[1]) == 5){
//                    count1[4]++;
//                }
//                if (Integer.parseInt(gg[1]) == 6){
//                    count1[5]++;
//                }
//                if (Integer.parseInt(gg[1]) == 7){
//                    count1[6]++;
//                }
//                if (Integer.parseInt(gg[1]) == 8){
//                    count1[7]++;
//                }
//                if (Integer.parseInt(gg[1]) == 9){
//                    count1[8]++;
//                }
//                if (Integer.parseInt(gg[1]) == 10){
//                    count1[9]++;
//                }
//                if (Integer.parseInt(gg[1]) == 11){
//                    count1[10]++;
//                }
//                if (Integer.parseInt(gg[1]) == 12){
//                    count1[11]++;
//                }
                bufferedReader.readLine();
                bufferedReader.readLine();
                bufferedReader.readLine();
            }
        }
        // в коунт 1 хранится число обращений по месяцам, где первый месяц это 0 индекс
        for(int i = 0; i < count1.length; i++){
            System.out.println(count1[i]);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Анализ");
        alert.setHeaderText("Анализ заболеваемости по месяцам");
        alert.setContentText("Январь Февраль Март" +  "\n" +
                             "     " + count1[0] + "          " + count1[1] + "          " + count1[2] + "\n" +
                            "Апрель Май Июнь"  + "\n" +
                             "    " + count1[3] + "          " + count1[4] + "          " + count1[5] + "\n" +
                            "Июль Август Сентябрь"  + "\n" +
                            "   " + count1[6] + "          " + count1[7] + "          " + count1[8] + "\n" +
                            "Октябрь Ноябрь Декабрь" + "\n" +
                            "     " + count1[9] + "          " + count1[10] + "             " + count1[11] + "\n" );
        alert.showAndWait();
    }

    @FXML
    protected void onByDistrictsClick() throws IOException {
        int[] count1 = {0,0,0,0};
        int countS = 1;
        int countNS = 1;
        int countA = 1;
        int countP = 1;
        try(FileReader fileReader = new FileReader("C:\\Users\\abram\\Desktop\\patients.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader)){
            while (bufferedReader.ready()){
                bufferedReader.readLine();
                bufferedReader.readLine();
                String gg = bufferedReader.readLine();
//                    Советский Ново-Савиновский Авиастроительный Приволжский
                if (gg.equals("Советский")){
                    count1[0] = countS;
                    countS++;
                } else if (gg.equals("Ново-Савиновский")) {
                    count1[1] = countNS;
                    countNS++;
                } else if (gg.equals("Авиастроительный")) {
                    count1[2] = countA;
                    countA++;
                } else if (gg.equals("Приволжский")) {
                    count1[3] = countP;
                    countP++;
                }

                bufferedReader.readLine();
                bufferedReader.readLine();
                bufferedReader.readLine();
                bufferedReader.readLine();
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Анализ");
        alert.setHeaderText("Анализ заболеваемости по районам");
        alert.setContentText("Советский Ново-Савиновский" + "\n" +
                "          " + count1[0] + "                       " + count1[1] + "\n" +
                "Авиастроительный Приволжский" +  "\n" +
                "             " + count1[2] + "                           " + count1[3] + "\n");
        alert.showAndWait();
    }
}

