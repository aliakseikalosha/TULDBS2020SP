package org.lesson.db;

public class Address {

    public static final String KEY_ID = "id_adresy";
    private int id;
    private String city;
    private String street;
    private String postcode;

    public Address(int id, String city, String street, String postcode) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.postcode = postcode;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getPostcode() {
        return postcode;
    }
}
