package org.lesson.db;

public class Ordinace {
    public static String KEY_NAME = "nazev";
    public static String KEY_ID = "id_ordinace";

    private int id;
    private String name;
    private int clinic;

    @Override
    public String toString() {
        return "Ordinace{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", clinic=" + clinic +
                '}';
    }

    public Ordinace(int id, String name, int clinic) {
        this.id = id;
        this.name = name;
        this.clinic = clinic;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getClinic() {
        return clinic;
    }
}
