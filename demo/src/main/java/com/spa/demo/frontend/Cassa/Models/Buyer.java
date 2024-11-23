package com.spa.demo.frontend.Cassa.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
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
    private int Status; //1-adult 2-student 3-pensioner
    private int NumberOfGeneratedId = 0;
    public ObservableList<String> Ids = FXCollections.observableArrayList();

    //Konstruktor a NumberOfGeneratedId és IDs lista nélkül.
    //NumberOfGeneratedId inicializáláskor mindig 0, az IDs meg majd a GenerateId metodus fogja feltölteni
    public Buyer(String id, String name, String city, String street, String postCode, int status) {
        Id = id;
        Name = name;
        City = city;
        Street = street;
        PostCode = postCode;
        Status = status;
    }

    //első paramétere a lista mérete, második paraméter egy kapcsoló
    //ha a kapcsoló All akkor a listába lévő összes azonosítót újragenerálja
    //ha a kapcsolóFirstOnly akkor csak az első a buyer saját id-ját generálja újra
    //A buyer példányhoz tartozó Ids listát generálja újra a megváltozott adatokhoz
    public void UpdateList(int size, String option)
    {
        List<String> helperList = new ArrayList<>();
        if(option.equals("All"))
        {
            helperList.addAll(Ids);
            Ids.clear();
            for (String s : helperList) {
                String status = s.substring(0, 2);
                //Ha a régi azonosító első 2 karakter FE akkor újrageneráláskor a felnőtthöz tartozó 1-es paraméterrel generál új id-t
                //Ha NY akkor 3-as akkor nyugdíjas és ezzel generál új id-t, ha DI akkor 2 és diák
                switch (status) {
                    case "FE" -> {
                        GenerateId(1);
                    }
                    case "NY" -> {
                        GenerateId(3);
                    }
                    case "DI" -> {
                        GenerateId(2);
                    }
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
            Ids.addAll(helperList);
        }
    }

    //kitörli a paraméterül kapott id-t az IDS listből
    public void RemoveId(String id)
    {
        this.Ids.remove(id);
    }

    //Paraméter egy szám, ha 1 akkor adult, ha 2 akkor student, ha 3-pensioner tipusú azonosítót generál
    //Az azonosító első 2 karaktere az azonosító tipusát határozza meg
    //következő 2 karakter a hónap, utána lévő 2 a napot. Majd az egyediség miatt a személyigazolványszáma a vevőnek
    //következő 2 az óra ,majd perc, utolsó karakter a buyer-hez tartozó jegyeket azonosítja, az első jegy A utána B, C és így tovább
    //az utolsó karakterrel van biztosítva hogy az azonosító biztos mindig egyedi lesz
    //A legenerált azonosítót hozzáadja az Ids listához
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
