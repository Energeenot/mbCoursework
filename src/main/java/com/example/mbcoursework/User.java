package com.example.mbcoursework;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class User {

    private int lines;
    private String currentLogin;
    private String currentPassword;
    private List<String> data;

    public User(){}

    public User(String currentLogin, String currentPassword){
        this.currentLogin = currentLogin;
        this.currentPassword = currentPassword;
    }

    public void setLogin(String currentLogin) {
        this.currentLogin = currentLogin;
    }

    public void setPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getLogin() {
        return currentLogin;
    }

    public String getPassword() {
        return currentPassword;
    }

    public boolean entrance() throws FileNotFoundException {
        lines = getLineCount("C:\\Users\\abram\\Desktop\\entrance.txt");
        String login = null;
        String password = null;

        File file = new File("C:\\Users\\abram\\Desktop\\entrance.txt");
        Charset charset = StandardCharsets.US_ASCII;
        try (BufferedReader reader = Files.newBufferedReader(file.toPath(), charset)) {
            String line = null;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
            for (int i = 0; i < lines; i++){
                line = reader.readLine();
                if (i == 0 || i % 2 ==0){
                    login = line;
                }
                if (i % 2 != 0){
                    password = line;
                }
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

//        System.out.println("");
//        System.out.println(login + " " + password);
        if (!Objects.equals(currentLogin, login) && !Objects.equals(currentPassword, password)) {
            System.out.println("fck");
            return false;
        } else{
            System.out.println("kayf");
            return true;
        }
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
