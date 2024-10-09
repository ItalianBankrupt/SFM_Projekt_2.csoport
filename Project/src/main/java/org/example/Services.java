package org.example;

import  javax.persistence.*;

@Entity
public class Services {
    @Id
    @GeneratedValue
    private int id;
    private String item;
    private String price;
    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        Belepo,Szolgaltatas;
    }

    public Services(String price, String item, Type type) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
