package org.lesson.db;

public class Employee {
    public static final String KEY_NAME = "jmeno";
    public static final String KEY_DEGREE = "titul";
    public static final String KEY_WORK = "id_pozice";
    public static final String KEY_ID = "id_zamestnanec";

    private int id;
    private String name;
    private String degree;
    private int id_address;
    private int id_work;

    public Employee(int id, String name, String degree, int id_address, int id_work) {
        this.id = id;
        this.name = name;
        this.degree = degree;
        this.id_address = id_address;
        this.id_work = id_work;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDegree() {
        return degree;
    }

    public int getId_address() {
        return id_address;
    }

    public int getId_work() {
        return id_work;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", degree='" + degree + '\'' +
                ", id_address=" + id_address +
                ", id_work=" + id_work +
                '}';
    }
}
