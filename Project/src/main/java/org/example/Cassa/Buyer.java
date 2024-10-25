package org.example.Cassa;

public class Buyer {
    private String Id;
    private String Name;
    private String City;
    private String Street;
    private String PostCode;

    public Buyer(String id, String name, String city, String street, String postCode) {
        Id = id;
        Name = name;
        City = city;
        Street = street;
        PostCode = postCode;
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
}
