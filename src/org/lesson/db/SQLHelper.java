package org.lesson.db;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.jar.Pack200.Packer.PASS;

public class SQLHelper {

    static final String databaseURL = "jdbc:sqlserver://"+Config.serverName+";database="+Config.databaseName;

    public static List<Clinic> getAllClinic() throws SQLException {
        List<Clinic> clinics = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(databaseURL, Config.username, Config.password)) {
            //create statement
            Statement stmnt = connection.createStatement();
            //execute query
            ResultSet rs = stmnt.executeQuery("SELECT * FROM Polikliniky");
            //process result
            while (rs.next()) {
                int id = rs.getInt(Clinic.KEY_ID);
                int address = rs.getInt(Address.KEY_ID);
                clinics.add(new Clinic(id,address));
            }
        }
        return clinics;
    }

    public static List<Employee> getEmployeesFrom(Clinic c) {
        return null;
    }

    /*
    try (Connection connection = DriverManager.getConnection(databaseURL, Config.username, Config.password)) {
            //create statement
            Statement stmnt = connection.createStatement();
            //execute query
            ResultSet rs = stmnt.executeQuery("SELECT * FROM Studenti");
            //process result
            while (rs.next()) {
                String name = rs.getString("sJmeno");
                double prumer = rs.getDouble("prumer");
                System.out.format("%-10s %4.2f%n",name, prumer);

            }
        }
     */

}
