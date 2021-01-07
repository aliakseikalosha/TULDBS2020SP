package org.lesson.db;

public class Patient {
    public static final String KEY_NAME = "jmeno";
    public static final String KEY_ID = "rodne_cislo";
    private String id;
    private int id_address;
    private String name;

    public Patient(String id, int id_address, String name) {
        this.id = id;
        this.id_address = id_address;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public int getId_address() {
        return id_address;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", id_address=" + id_address +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }
}
