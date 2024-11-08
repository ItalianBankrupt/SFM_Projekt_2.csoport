package com.spa.demo.frontend.Cassa;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Buyer {
    private String Id;
    private String Name;
    private String City;
    private String Street;
    private String PostCode;
    private int NumberOfGeneratedId = 0;

    public Buyer(String id, String name, String city, String street, String postCode) {
        Id = id;
        Name = name;
        City = city;
        Street = street;
        PostCode = postCode;
    }

    public int getNumberOfGeneratedId() {
        return NumberOfGeneratedId;
    }

    public void setNumberOfGeneratedId(int numberOfGeneratedId) {
        NumberOfGeneratedId = numberOfGeneratedId;
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

    public String GenerateId()
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
        String id = "";
        id = year + month + day + this.Id + hour + min + sec + this.NumberOfGeneratedId;
        NumberOfGeneratedId++;
        return id;
    }
}
