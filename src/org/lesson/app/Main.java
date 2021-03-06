package org.lesson.app;

import org.lesson.db.*;


import java.io.File;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Main {
    private static final char POSITIVE_ANSWER = 'a';
    private static final char NEGATIVE_ANSWER = 'n';


    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            printMainMenu();
            int input = getInput();
            switch (input) {
                case 0:
                    run = false;
                    break;
                case 1:
                    listNumberOfOrdinationsInClinics();
                    break;
                case 2:
                    visitCountInDoctorOffice();
                    break;
                case 3:
                    listPatientsWhoVisitedAfterDate();
                    break;
                case 4:
                    listPatientCountInCities();
                    break;
                case 5:
                    listNotWorkingDoctorsFromTo();
                    break;
                case 6:
                    listVisitCountInOrdination();
                    break;
                case 7:
                    addPatient();
                    break;
                case 8:
                    deletePatient();
                    break;
                case 9:
                    editPatient();
                    break;
                case 9001:
                    SHOW_ALL();
                    break;
                default:
                    System.out.println("Zadana neplatna volba.");
            }
        }
    }

    private static void SHOW_ALL() {
        List<Clinic> allClinics = new ArrayList<>();
        try {
            allClinics = SQLHelper.getAllClinics();
            for (Clinic c : allClinics) {
                System.out.println(c.toString());
            }
            System.out.println("-----------------");
            System.out.println(SQLHelper.getClinicBy(1).toString());
            System.out.println("-----------------");
            List<Employee> employees = SQLHelper.getAllEmployees();
            for (Employee e : employees) {
                System.out.println(e);
            }
            System.out.println("-----------------");
            System.out.println(SQLHelper.getEmployeeBy(1));
            System.out.println("-----------------");
            List<Ordinace> os = SQLHelper.getAllOrdinace();
            for (Ordinace o : os) {
                System.out.println(o);
            }
            System.out.println("-----------------");
            System.out.println(SQLHelper.getOrdinaceBy(1));
            List<Patient> patients = SQLHelper.getAllPatients();
            System.out.println("-----------------");
            for (Patient patient : patients) {
                System.out.println(patient);
            }
            System.out.println("-----------------");
            System.out.println(SQLHelper.getPatientBy("4231229481"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void editPatient() {
        String rc = getString("Zadejte rodne cislo.");
        String name = getString("Zadejte nove jmeno.");
        System.out.println("Zadejte id nove adresy.");
        int addres = getInput();
        try {
            SQLHelper.editPatient(rc,name,addres);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void deletePatient() {
        System.out.println("Zadejte rodne cislo pacienta.");
        String rc = sc.nextLine();
        try {
            SQLHelper.deletePatient(rc);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void addPatient() {
        String rc = getString("Zadejte rodne cislo.");
        String name = getString("Zadejte jmeno.");
        System.out.println("Zadejte id adresy.");
        int adress = getInput();
        try {
            SQLHelper.addPatient(new Patient(rc,adress,name));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static String getString(String text){
        System.out.println(text);
        String s = sc.nextLine();
        if(s.length() == 0){
            return getString(text);
        }
        return s;
    }

    private static void listVisitCountInOrdination() {
        System.out.println("6. Pocet navstev v ordinacich jednotlivych poliklinik.");
        try {
            Map<Ordinace, Integer> count = SQLHelper.getOrdinaceCount();
            for (Map.Entry<Ordinace, Integer> entry : count.entrySet()) {
                System.out.print(entry.getKey().toString());
                System.out.print("\t");
                System.out.println(entry.getValue());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void listNotWorkingDoctorsFromTo() {
        System.out.println("5. Vypis seznam doktoru kteri nemeli nastevu v urcitem odbodi.");
        Date from  = getDate();
        Date to  = getDate();
        try {
            List<Employee> employees = SQLHelper.getEmployeesWithoutWork(from,to);
            for (Employee e : employees) {
                System.out.println(e.toString());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private static void listPatientCountInCities() {
        System.out.println("4. Vypis pocet pacientu ve vsech mestech.");
        try {
            Map<String,Integer> patientCount = SQLHelper.getPatientCountInCities();
            for (Map.Entry<String, Integer> entry : patientCount.entrySet()) {
                System.out.print(entry.getKey());
                System.out.print("\t:\t");
                System.out.println(entry.getValue());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void listPatientsWhoVisitedAfterDate() {
        System.out.println("3. Vypis jmena pacientu kteri navstivili v case od zadaneho data.");
        Date after = getDate();
        try {
            List<Patient> patients = SQLHelper.getPatientsWhoVisited(after);
            for (Patient p : patients) {
                System.out.println(p.toString());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void visitCountInDoctorOffice() {
        System.out.println("2. Vypis ordinace ktere maji vic nez 2 navstevy.");
        Map<Ordinace, Integer> count = null;
        try {
            count = SQLHelper.getVisitCount(2);
            for (Map.Entry<Ordinace, Integer> entry : count.entrySet()) {
                System.out.print(entry.getKey().toString());
                System.out.print("\t");
                System.out.println(entry.getValue());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void listNumberOfOrdinationsInClinics() {
        System.out.println("1. Vypis pocty ordinaci v jednotlivych poliklinikach.");
        try {
            Map<Clinic, Integer> clinics = SQLHelper.getOrdinationCount();
            for (Map.Entry<Clinic, Integer> entry : clinics.entrySet()) {
                System.out.print(entry.getKey().toString());
                System.out.print("\t");
                System.out.println(entry.getValue());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println("Hlavni menu programu");
        System.out.println("1. Vypis pocty ordinaci v jednotlivych poliklinikach.");
        System.out.println("2. Vypis ordinace ktere maji vic nez 2 navstevy.");
        System.out.println("3. Vypis jmena pacientu kteri navstivili v case od zadaneho data.");
        System.out.println("4. Vypis pocet pacientu ve vsech mestech.");
        System.out.println("5. Vypis seznam doktoru kteri nemeli nastevu v urcitem odbodi.");
        System.out.println("6. Pocet navstev v ordinacich jednotlivych poliklinik.");
        System.out.println("7. Pridej noveho pacienta.");
        System.out.println("8. Smaz pacienta.");
        System.out.println("9. Editace pacienta.");
        System.out.println("9001. Chci videt vsechno.");
        System.out.println("0. ukoncit program.");
    }

    private static boolean getAnswer() {
        String line = sc.nextLine();
        if (line.length() > 0) {
            char c = line.toLowerCase().toCharArray()[0];
            if (c == POSITIVE_ANSWER) {
                return true;
            } else if (c == NEGATIVE_ANSWER) {
                return false;
            }
        }
        System.out.println("Zadej ano nebo ne.");
        return getAnswer();
    }

    private static Date getDate() {
        System.out.println("Zadej datum ve formatu dd/mm/rrrr.");
        String dateString = sc.nextLine();
        Date d = null;
        try {
            d = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
        } catch (ParseException e) {
            //e.printStackTrace();
            System.out.println("Zadano spatne datum.");
            return getDate();
        }
        return d;
    }

    private static File getWorkDirectory() {
        System.out.println("Enter path for working directory: ");
        File dir = new File(sc.nextLine());
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("This file path is not valid." + (dir.exists() ? "Path must lead to directory." : "Directory do not exist!"));
            return getWorkDirectory();
        }
        return dir;
    }

    private static long getInput(long min, long max) {
        long i = getInputLong();
        if (i < min || i > max) {
            System.out.printf("Hodnota musi byt mezi %d a %d!", min, max);
            return getInput(min, max);
        }
        return i;
    }

    private static byte getInputByte() {
        byte i = -1;
        try {
            i = sc.nextByte();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Zadana spatna volba.");
        } finally {
            sc.nextLine();
        }
        return i;
    }

    private static short getInputShort() {
        short i = -1;
        try {
            i = sc.nextShort();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Zadana spatna volba.");
        } finally {
            sc.nextLine();
        }
        return i;
    }

    private static long getInputLong() {
        long i = -1;
        try {
            i = sc.nextLong();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Zadana spatna volba.");
        } finally {
            sc.nextLine();
        }
        return i;
    }

    private static float getInputFloat() {
        float i;
        try {
            i = sc.nextFloat();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Zadana spatna volba.");
        } finally {
            sc.nextLine();
        }
        return getInputFloat();
    }

    private static byte getInput(byte min, byte max) {
        byte i = getInputByte();
        if (i < min || i > max) {
            System.out.printf("Hodnota musi byt mezi %d a %d.", min, max);
            return getInput(min, max);
        }
        return i;
    }

    private static short getInput(short min, short max) {
        short i = getInputShort();
        if (i < min || i > max) {
            System.out.printf("Hodnota musi byt mezi %d a %d.", min, max);
            return getInput(min, max);
        }
        return i;
    }


    private static float getInput(float min, float max) {
        float i = getInputFloat();
        if (i < min || i > max) {
            System.out.printf("Hodnota musi byt mezi %d a %d.", min, max);
            return getInput(min, max);
        }
        return i;
    }

    private static int getInput(int min, int max) {
        int i = getInput();
        if (i < min || i > max) {
            System.out.printf("Hodnota musi byt mezi %d a %d.", min, max);
            return getInput(min, max);
        }
        return i;
    }

    private static int getInput() {
        int i = -1;
        try {
            i = sc.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Zadana spatna volba.");
        } finally {
            sc.nextLine();
        }
        return i;
    }
}
