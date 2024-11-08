package com.spa.demo.frontend.Cassa;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.*;

public class Buyer {
    private String Id;
    private String Name;
    private String City;
    private String Street;
    private String PostCode;
    private int NumberOfGeneratedId = 0;
    public ObservableList<String> Ids = FXCollections.observableArrayList();

    public Buyer(String id, String name, String city, String street, String postCode) {
        Id = id;
        Name = name;
        City = city;
        Street = street;
        PostCode = postCode;

    }

    public void UpdateList(int size)
    {
        Ids.clear();
        for(int i = 0; i < size; i++)
        {
            GenerateId();
        }
    }


    public int getNumberOfGeneratedId() {
        return NumberOfGeneratedId;
    }

    public void setNumberOfGeneratedId(int numberOfGeneratedId) {
        NumberOfGeneratedId = numberOfGeneratedId;
    }

    public ObservableList<String> getIds() {
        return Ids;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getCity() {
        return City;
    }

    public String getStreet() {
        return Street;
    }

    public String getPostCode() {
        return PostCode;
    }

    public void RemoveId(String id)
    {
        this.Ids.remove(id);
    }

    public void setId(String id) {
        Id =id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public void setPostCode(String postCode) {
        PostCode = postCode;
    }

    public void GenerateId()
    {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        String year = (calendar.get(Calendar.YEAR) + "").substring(2);
        String month = (calendar.get(Calendar.MONTH)+1) + "";
        month = (month.length() == 1) ? "0".concat(month) : month;
        String day = calendar.get(Calendar.DAY_OF_MONTH) + "";
        day = (day.length() == 1) ? "0".concat(day) : day;
        String hour = calendar.get(Calendar.HOUR_OF_DAY) + "";
        hour = (hour.length() == 1) ? "0".concat(hour) : hour;
        String min = calendar.get(Calendar.MINUTE) + "";
        min = (min.length() == 1) ? "0".concat(min) : min;
        String sec = calendar.get(Calendar.SECOND) + "";
        sec = (sec.length() == 1) ? "0".concat(sec) : sec;
        String lastChar = (char)(this.NumberOfGeneratedId + 'A')+ "";
        String id = "";
        id = year + month + day + this.Id + hour + min + sec + lastChar;
        NumberOfGeneratedId++;
        Ids.add(id);
    }

}
