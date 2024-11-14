package com.spa.demo.frontend.Cassa;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Buyer {
    private String Id;
    private String Name;
    private String City;
    private String Street;
    private String PostCode;
    private int Status; //1-adult 2-student 3-retired
    private int NumberOfGeneratedId = 0;
    public ObservableList<String> Ids = FXCollections.observableArrayList();

    public Buyer(String id, String name, String city, String street, String postCode, int status) {
        Id = id;
        Name = name;
        City = city;
        Street = street;
        PostCode = postCode;
        Status = status;
    }

    public void UpdateList(int size, String option)
    {
        List<String> helperList = new ArrayList<>();
        if(option.equals("All"))
        {
            helperList.addAll(Ids);
            Ids.clear();
            for (int i = 0; i < helperList.size(); i++)
            {
                String status = helperList.get(i).substring(0,1);
                if(status.equals("FE"))
                {
                    GenerateId(1);
                }
                else if(status.equals("NY")){
                    GenerateId(3);
                }
                else if(status.equals("DI")){
                    GenerateId(2);
                }
            }

        }
        else if(option.equals("FirstOnly"))
        {
            for (int i = 1; i < Ids.size(); i++)
            {
                helperList.add(Ids.get(i));
            }
            Ids.clear();
            GenerateId(this.Status);
            for (int i = 0; i < helperList.size(); i++)
            {
                Ids.add(helperList.get(i));
            }

        }
    }

    public void RemoveId(String id)
    {
        this.Ids.remove(id);
    }

    public void GenerateId(int status)
    {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        String month = (calendar.get(Calendar.MONTH)+1) + "";
        month = (month.length() == 1) ? "0".concat(month) : month;
        String day = calendar.get(Calendar.DAY_OF_MONTH) + "";
        day = (day.length() == 1) ? "0".concat(day) : day;
        String hour = calendar.get(Calendar.HOUR_OF_DAY) + "";
        hour = (hour.length() == 1) ? "0".concat(hour) : hour;
        String min = calendar.get(Calendar.MINUTE) + "";
        min = (min.length() == 1) ? "0".concat(min) : min;
        String sec = calendar.get(Calendar.SECOND) + "";
        String lastChar = (char)(this.NumberOfGeneratedId + 'A')+ "";
        String statusChar = "";
        if (status == 1)
        {
            statusChar = "FE";
        }
        else if (status == 2)
        {
            statusChar = "DI";
        }
        else if (status == 3)
        {
            statusChar = "NY";
        }
        String id = "";
        id = statusChar + month + day + this.Id + hour + min  + lastChar;
        NumberOfGeneratedId++;
        Ids.add(id);
    }

}
