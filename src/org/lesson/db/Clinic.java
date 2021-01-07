package org.lesson.db;

public class Clinic {
    public static String KEY_ID = "id_poliklinika";
    private int id;
    private int id_address;

    public Clinic(int id, int id_address) {
        this.id = id;
        this.id_address = id_address;
    }

    public int getId() {
        return id;
    }

    public int getId_address() {
        return id_address;
    }

    @Override
    public String toString() {
        return "Clinic{" +
                "id=" + id +
                ", id_address=" + id_address +
                '}';
    }
}
