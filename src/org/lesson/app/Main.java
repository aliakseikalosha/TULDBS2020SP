package org.lesson.app;
import org.lesson.db.Clinic;
import org.lesson.db.Employee;
import org.lesson.db.SQLHelper;

import java.io.File;
import java.sql.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final char POSITIVE_ANSWER = 'a';
    private static final char NEGATIVE_ANSWER = 'n';


    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        GenerateName();
        boolean run = false;
        while (run) {
            printMainMenu();
            int input = getInput();
            switch (input) {
                case 0:
                    run = false;
                    break;
                case 1:
                    listEmployeeInClinic();
                    break;
                case 2:
                    visitCountInDoctorOffice();
                    break;
                case 3:
                    listPatientsInCity();
                    break;
                case 4:
                    listPatientCountInCities();
                    break;
                case 5:
                    listNotWorkingDoctorsFromTo();
                    break;
                case 6:
                    listEmployeeAndPatientsSameCity();
                    break;
                default:
                    System.out.println("Zadana neplatna volba");
            }
        }
    }
    static int[] addressid = {1,20};
    static String[] rcAll = {"6615019611","8860270338","1578904862","4717368948","4106301511","0117149123","7422367885","9378977559","5186706552","6023896125","4529709148","8266047540","5670928165","7933441605","0897426073","4065265421","5888434886","5614808227","1633263596","1088427069","8406376730","6105475933","2194035905","4140673944","4231229481","6052687893","6681552557","2316413772","7509930312","7070794070","4461036837","4566173454","0546293865","6085636021","6263132232","8937891625","4107448488","7107892542","5600705982","0420395760","0175204515","7926610549","6411308689","5536572194","3112915799","1838499091","7281304263","6415138472","9266961337","6362731963"}

    private static void GenerateName() {
        String[] fname  = {"Pepe", "Sarka", "Dana", "David","Masa","Losa","Lenka","Veronika","Vojta"};
        String[] lname  = {"Nepomuk", "Novak", "Safarik", "Kanas","Kalosa","Prvni","Osterich","David","Dalni","Musil"};
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        StringBuilder rcsb = new StringBuilder().append("{\"");
        for (int i = 0; i < 50; i++) {
            String rc = randomRC(r);
            sb.append(String.format("INSERT INTO Pacienti VALUES ('%s', %d, '%s')%n",rc,i%addressid[1]+1, random(fname,r)+" "+random(lname,r)));
            rcsb.append(rc).append("\",\"");
        }
        System.out.println(sb.toString());
        System.out.println(rcsb.append("}").toString());
    }

    private static String randomRC(Random r) {
        String c = "";
        for (int i = 0; i < 10; i++) {
            c+=r.nextInt(10);
        }
        return c;
    }

    private static String random(String[] a, Random r){
        return a[r.nextInt(a.length)];
    }

    private static void listEmployeeAndPatientsSameCity() {
        System.out.println("6. Seznam pacientu a doktoru ktery pochazi ze stejneho mesta.");

    }

    private static void listNotWorkingDoctorsFromTo() {
        System.out.println("5. Vypis seznam doktoru ktery nemeli nastevu v urcitem odbodi.");
    }

    private static void listPatientCountInCities() {
        System.out.println("4. Vypis pocet pacientu ve vsech menstach.");
    }

    private static void listPatientsInCity() {
        System.out.println("3. Vypis seznam pacientu ve meste.");
    }

    private static void visitCountInDoctorOffice() {
        System.out.println("2. Vypis pocet navstev pocientem ordinaci za urcitou dobu.");
    }

    private static void listEmployeeInClinic() {
        System.out.println("1. Vypis seznam zamestnancu v poliklinice.");
        List<Clinic> clinics = null;
        try {
            clinics = SQLHelper.getAllClinic();
            if(clinics.size()>0){
                for (int i = 0, clinicsSize = clinics.size(); i < clinicsSize; i++) {
                    Clinic c = clinics.get(i);
                    System.out.format("%d. Clinic %d\n",i,c.getId());
                }
                Clinic c = clinics.get(getInput(0, clinics.size()-1));
                List<Employee> employees  = SQLHelper.getEmployeesFrom(c);
                for (Employee e : employees) {
                    System.out.format(e.toString());
                }
            }else{
                System.out.println("NO Clinics in DB!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println("Hlavni menu programu");
        System.out.println("1. Vypis seznam zamestnancu v polikliniki.");
        System.out.println("2. Vypis pocet navstev pocientem ordinaci za urcitou dobu.");
        System.out.println("3. Vypis seznam pacientu ve meste.");
        System.out.println("4. Vypis pocet pacientu ve vsech menstach.");
        System.out.println("5. Vypis seznam doktoru ktery nemeli nastevu v urcitem odbodi.");
        System.out.println("6. Seznam pacientu a doktoru ktery pochazi ze stejneho mesta.");
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
        System.out.println("Zadej ano nebo ne");
        return getAnswer();
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
            System.out.printf("Hodnota musi byt mezi %d a %d", min, max);
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
            System.out.println("Zadana spatna volba");
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
            System.out.println("Zadana spatna volba");
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
            System.out.println("Zadana spatna volba");
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
            System.out.println("Zadana spatna volba");
        } finally {
            sc.nextLine();
        }
        return getInputFloat();
    }

    private static byte getInput(byte min, byte max) {
        byte i = getInputByte();
        if (i < min || i > max) {
            System.out.printf("Hodnota musi byt mezi %d a %d", min, max);
            return getInput(min, max);
        }
        return i;
    }

    private static short getInput(short min, short max) {
        short i = getInputShort();
        if (i < min || i > max) {
            System.out.printf("Hodnota musi byt mezi %d a %d", min, max);
            return getInput(min, max);
        }
        return i;
    }


    private static float getInput(float min, float max) {
        float i = getInputFloat();
        if (i < min || i > max) {
            System.out.printf("Hodnota musi byt mezi %d a %d", min, max);
            return getInput(min, max);
        }
        return i;
    }

    private static int getInput(int min, int max) {
        int i = getInput();
        if (i < min || i > max) {
            System.out.printf("Hodnota musi byt mezi %d a %d", min, max);
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
            System.out.println("Zadana spatna volba");
        } finally {
            sc.nextLine();
        }
        return i;
    }
}
