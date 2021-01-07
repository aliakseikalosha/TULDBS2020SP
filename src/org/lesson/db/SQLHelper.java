package org.lesson.db;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class SQLHelper {

    static final String databaseURL = "jdbc:sqlserver://" + Config.serverName + ";database=" + Config.databaseName;

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
                clinics.add(new Clinic(id, address));
            }
        }
        return clinics;
    }

    public static List<Employee> getEmployeesFrom(Clinic c) throws SQLException {
        try (Connection connection = DriverManager.getConnection(databaseURL, Config.username, Config.password)) {
            List<Employee> employees = new ArrayList<>();
            //create statement
            Statement stmnt = connection.createStatement();
            String sql = "SELECT * FROM Zamestnanci WHERE id_zamestnanec IN ( SELECT id_zamestnanec FROM Zamestnanec_Ordinace WHERE id_ordinace IN ( SELECT id_ordinace FROM Ordinace WHERE id_poliklinika = ? ) )";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, c.getId());
            //execute query
            ResultSet rs = preparedStatement.executeQuery();
            //process result
            while (rs.next()) {
                int id = rs.getInt(Employee.KEY_ID);
                int address = rs.getInt(Address.KEY_ID);
                String name = rs.getString(Employee.KEY_NAME);
                String degree = rs.getString(Employee.KEY_DEGREE);
                int work = rs.getInt(Employee.KEY_WORK);
                employees.add(new Employee(id, name, degree, address, work));
            }
            return employees;
        }
    }

    public static List<Employee> getEmployeesWithoutWork(Date from, Date to) throws SQLException {
        try (Connection connection = DriverManager.getConnection(databaseURL, Config.username, Config.password)) {
            List<Employee> employees = new ArrayList<>();
            //create statement
            Statement stmnt = connection.createStatement();
            String sql = "SELECT * FROM Zamestnanci WHERE id_zamestnanec IN (SELECT id_zamestnanec FROM Zamestnanci EXCEPT SELECT id_zamestnanec FROM Navstevy WHERE datum > ? AND datum < ?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(from.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(to.getTime()));
            //execute query
            ResultSet rs = preparedStatement.executeQuery();
            //process result
            while (rs.next()) {
                int id = rs.getInt(Employee.KEY_ID);
                int address = rs.getInt(Address.KEY_ID);
                String name = rs.getString(Employee.KEY_NAME);
                String degree = rs.getString(Employee.KEY_DEGREE);
                int work = rs.getInt(Employee.KEY_WORK);
                employees.add(new Employee(id, name, degree, address, work));
            }
            return employees;
        }
    }

    public static Map<Ordinace, Integer> getVisitCount(int moreThan) throws SQLException {
        try (Connection connection = DriverManager.getConnection(databaseURL, Config.username, Config.password)) {
            Map<Ordinace, Integer> list = new HashMap<>();
            //create statement
            Statement stmnt = connection.createStatement();
            String sql = "SELECT id_ordinace,nazev,id_poliklinika,COUNT(visit) as count FROM (SELECT  o.id_ordinace,o.nazev,o.id_poliklinika, x.id_ordinace as visit FROM Ordinace o JOIN (SELECT * FROM Navstevy) as x ON  o.id_ordinace = x.id_ordinace)  u GROUP BY u.id_ordinace,u.nazev,id_poliklinika HAVING COUNT(visit) > ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, moreThan);
            //execute query
            ResultSet rs = preparedStatement.executeQuery();
            //process result
            while (rs.next()) {
                int id = rs.getInt(Ordinace.KEY_ID);
                int clinic = rs.getInt(Clinic.KEY_ID);
                String name = rs.getString(Ordinace.KEY_NAME);
                int count = rs.getInt("count");
                list.put(new Ordinace(id, name, clinic), new Integer(count));
            }
            return list;
        }
    }


    public static List<Patient> getPatientWhoVisit(Date from) throws SQLException {
        try (Connection connection = DriverManager.getConnection(databaseURL, Config.username, Config.password)) {
            List<Patient> patients = new ArrayList<>();
            //create statement
            Statement stmnt = connection.createStatement();
            String sql = "SELECT * FROM Pacienti WHERE rodne_cislo IN (SELECT pacienti_rodne_cislo FROM Navstevy WHERE datum > ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(from.getTime()));
            //execute query
            ResultSet rs = preparedStatement.executeQuery();
            //process result
            while (rs.next()) {
                String id = rs.getString(Patient.KEY_ID);
                int address = rs.getInt(Address.KEY_ID);
                String name = rs.getString(Patient.KEY_NAME);
                patients.add(new Patient(id, address, name));
            }
            return patients;
        }
    }

    public static Map<String, Integer> getPatientCountInCities() throws SQLException {
        try (Connection connection = DriverManager.getConnection(databaseURL, Config.username, Config.password)) {
            Map<String, Integer> map = new HashMap<>();
            //create statement
            Statement stmnt = connection.createStatement();
            String sql = "SELECT mesto,COUNT(mesto) as count FROM Pacienti p RIGHT JOIN ( SELECT id_adresy, mesto FROM Adresy ) as x ON p.id_adresy = x.id_adresy GROUP BY mesto";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //execute query
            ResultSet rs = preparedStatement.executeQuery();
            //process result
            while (rs.next()) {
                String name = rs.getString(Address.KEY_CITY);
                int count = rs.getInt("count");
                map.put(name, new Integer(count));
            }
            return map;
        }
    }

    public static Map<Ordinace, Integer> getOrdinaceCount() throws SQLException {
        try (Connection connection = DriverManager.getConnection(databaseURL, Config.username, Config.password)) {
            Map<Ordinace, Integer> list = new HashMap<>();
            //create statement
            Statement stmnt = connection.createStatement();
            String sql = "SELECT o.nazev, o.id_ordinace, o.id_poliklinika, COUNT(n.id_ordinace) as count FROM Ordinace o  LEFT JOIN Navstevy n ON o.id_ordinace = n.id_ordinace GROUP BY o.nazev, n.id_ordinace, o.id_ordinace,o.id_poliklinika";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //execute query
            ResultSet rs = preparedStatement.executeQuery();
            //process result
            while (rs.next()) {
                int id = rs.getInt(Ordinace.KEY_ID);
                int clinic = rs.getInt(Clinic.KEY_ID);
                String name = rs.getString(Ordinace.KEY_NAME);
                int count = rs.getInt("count");
                list.put(new Ordinace(id, name, clinic), new Integer(count));
            }
            return list;
        }
    }

    public static Map<Clinic, Integer> getOrdinationCount() throws SQLException {
        Map<Clinic, Integer> clinics = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(databaseURL, Config.username, Config.password)) {
            //create statement
            Statement stmnt = connection.createStatement();
            //execute query
            ResultSet rs = stmnt.executeQuery("SELECT id_poliklinika,id_adresy, count = (SELECT COUNT(id_ordinace) FROM Ordinace o WHERE p.id_poliklinika = o.id_poliklinika) FROM Polikliniky p ");
            //process result
            while (rs.next()) {
                int id = rs.getInt(Clinic.KEY_ID);
                int address = rs.getInt(Address.KEY_ID);
                int count = rs.getInt("count");
                clinics.put(new Clinic(id, address), new Integer(count));
            }
        }
        return clinics;
    }

    public static void deletePatient(String rc) throws SQLException {
        try (Connection connection = DriverManager.getConnection(databaseURL, Config.username, Config.password)) {
            Statement stmnt = connection.createStatement();
            String sql = ("DELETE FROM Pacienti WHERE rodne_cislo = ?");
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,rc);
            preparedStatement.executeUpdate();
        }
    }

    public static void addPatient(Patient patient) throws SQLException {
        try(Connection connection = DriverManager.getConnection(databaseURL, Config.username, Config.password)){
            String sql = "INSERT INTO Pacienti VALUES ( ? ,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,patient.getId());
            preparedStatement.setInt(2,patient.getId_address());
            preparedStatement.setString(3,patient.getName());
            preparedStatement.executeUpdate();
        }
    }

    public static void editPatient(String rc, String name, int addres) throws SQLException {
        try(Connection connection = DriverManager.getConnection(databaseURL, Config.username, Config.password)){
            String sql = "UPDATE Pacienti SET  jmeno = ?, id_adresy = ? WHERE rodne_cislo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,addres);
            preparedStatement.setString(3,rc);
            preparedStatement.executeUpdate();
        }
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
