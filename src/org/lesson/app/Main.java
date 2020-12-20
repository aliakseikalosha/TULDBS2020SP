package org.lesson.app;

import java.io.File;
import java.util.Scanner;

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
        System.out.println("1. Vypis seznam zamestnancu v polikliniki.");
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
