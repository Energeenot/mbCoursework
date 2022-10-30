package com.example.mbcoursework;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Scanner;

public class RegisterController {

    private int countAlert = 1;
    private int lines;
    @FXML
    private TextField log;
    @FXML
    private TextField pas;
    @FXML
    private Button reg;

    @FXML
    protected void onRegisterButtonClick() throws IOException {
        lines = getLineCount("C:\\Users\\abram\\Desktop\\entrance.txt");
        countAlert = 0;
        String login = log.getText();
        System.out.println(login);
        System.out.println(log);
        String password = pas.getText();

//        проверка на существующий логин если логин не существует тогда добавь +2 к счётчику строк

        File file = new File("C:\\Users\\abram\\Desktop\\entrance.txt");
        Charset charset = StandardCharsets.US_ASCII;
        try (BufferedReader reader = Files.newBufferedReader(file.toPath(), charset)) {
            String line = null;

            for (int i = 0; i < lines; i++){
                line = reader.readLine();
                System.out.println(line);
                if (i == 0 || i % 2 == 0){
                    if (Objects.equals(line, login)){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Ошибка");
                        alert.setContentText("Введённый вами логин существует.");
                        alert.showAndWait();
                        countAlert++;

                    }
                }
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        if (Objects.equals(login, "") || Objects.equals(password, "")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setContentText("Одно из полей для ввода пустое. Введите логин и пароль.");
            alert.showAndWait();
            countAlert++;
        }

        if (countAlert == 0){
//            запись в файл логина и пароля и вывод сообщения что вы успешно зарегистрировались

            String str = "World";
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\abram\\Desktop\\entrance.txt", true));
            writer.append("\n");
            writer.append(login);
            writer.append("\n");
            writer.append(password);
            writer.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успешная регистрация");
            alert.setContentText("Вы успешно зарегистрировались");
            alert.showAndWait();
            HelloApplication helloApplication = new HelloApplication();
            Stage stage = (Stage) reg.getScene().getWindow();
            helloApplication.start(stage);
        }

        //запись в файл логина и пароля
        //

        User user = new User(login, password);

    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public static int getLineCount(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        int lines = 0;

        while (scanner.hasNext()) {
            if (Objects.equals(scanner.nextLine(), "")){
                break;
            }
            lines++;
        }

        scanner.close();
        return lines;
    }
}
