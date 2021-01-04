package org.lesson.db;

public class Patient {
    private int id;
    private int id_address;
    private String name;

    public Patient(int id, int id_address, String name) {
        this.id = id;
        this.id_address = id_address;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getId_address() {
        return id_address;
    }

    public String getName() {
        return name;
    }
}
