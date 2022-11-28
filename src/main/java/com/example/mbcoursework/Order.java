package com.example.mbcoursework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Order {
    String numberOrderCell;
    String fioCell;
    String name;
    String count;
    String amount;

//    public Order(int numberOrderCell, String fioCell, String[] name, String[] count, String[] amount) {
//        this.numberOrderCell = numberOrderCell;
//        this.fioCell = fioCell;
//        this.name = name;
//        this.count = count;
//        this.amount = amount;
//    }

    public Order(){}

    public Order(String numberOrderCell, String fioCell) {
        this.numberOrderCell = numberOrderCell;
        this.fioCell = fioCell;
    }

    public Order(String name, String count, String amount) {
        this.name = name;
        this.count = count;
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNumberOrderCell() {
        return numberOrderCell;
    }

    public void setNumberOrderCell(String numberOrderCell) {
        this.numberOrderCell = numberOrderCell;
    }

    public String getFioCell() {
        return fioCell;
    }

    public void setFioCell(String fioCell) {
        this.fioCell = fioCell;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
