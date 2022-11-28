package com.example.mbcoursework;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class Purchases {
    String idOrder, fio, name, count;
    Date date;

    public void getAllOrder(){
        try(FileReader fileReader = new FileReader("C:\\Users\\abram\\Desktop\\order.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while (bufferedReader.ready()){
                String str = "";
                str += bufferedReader.readLine();
                System.out.println(str);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  void searchOrder( String id){
        try(FileReader fileReader = new FileReader("C:\\Users\\abram\\Desktop\\order.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while (bufferedReader.ready()){
                if (Objects.equals(id, bufferedReader.readLine())){
                    System.out.println("Заказ найден:");
                    idOrder = id;
                    System.out.println("Номер заказа " + idOrder);
                    fio = bufferedReader.readLine();
                    System.out.println("fio " + fio);
                    name = bufferedReader.readLine();
                    System.out.println("name " +  name);
                    count = bufferedReader.readLine();
                    System.out.println("Товары в заказе " + count);
                }
                else{
                    bufferedReader.readLine();
                    bufferedReader.readLine();
                    bufferedReader.readLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
