package org.example.Restaurant;

import javax.persistence.*;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue
    private int id;
    private String item;
    private int price;
    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type{
        Eloetel,Leves,Foetel,Hal_etel,Teszta,Desszert,Kave,Udito,Sor;
    }

    public Restaurant(String item, int price, Type type) {
        this.item = item;
        this.price = price;
        this.type = type;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }

    public Type getType() {
        return type;
    }
}
