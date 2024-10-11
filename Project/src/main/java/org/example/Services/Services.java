package org.example.Services;

import  javax.persistence.*;

@Entity
public class Services {
    @Id
    @GeneratedValue
    private int id;
    private String item;
    private int price;
    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        Belepo,Szolgaltatas;
    }

    public Services(String item, int price,  Type type) {
        this.price = price;
        this.item = item;
        this.type = type;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
