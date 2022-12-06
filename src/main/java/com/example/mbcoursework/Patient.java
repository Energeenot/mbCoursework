package com.example.mbcoursework;

public class Patient {
    String fio;
    String dateBirth;
    private String area;
    private String dateApplication;
    private String complaints;
    private String diagnosis;
    private String medic;

    public Patient(String fio, String dateBirth, String area, String dateApplication, String complaints, String diagnosis, String medic) {
        this.fio = fio;
        this.dateBirth = dateBirth;
        this.area = area;
        this.dateApplication = dateApplication;
        this.complaints = complaints;
        this.diagnosis = diagnosis;
        this.medic = medic;
    }

    public Patient() {}

    public String getMedic() {
        return medic;
    }

    public void setMedic(String medic) {
        this.medic = medic;
    }

    public String getFio() {
        return fio;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public String getArea() {
        return area;
    }

    public String getDateApplication() {
        return dateApplication;
    }

    public String getComplaints() {
        return complaints;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setDateApplication(String dateApplication) {
        this.dateApplication = dateApplication;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "ФИО " + fio + ", + \n" +
                "Дата рождения " + dateBirth + ", \n" +
                "Даты обращения " + dateApplication + ", \n" +
                "Жалобы " + complaints + ", \n" +
                "Диагноз " + diagnosis + ", \n" +
                "Врач " + medic + ", \n";
    }
}
