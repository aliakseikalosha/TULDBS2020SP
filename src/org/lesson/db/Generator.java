package org.lesson.db;

import java.util.Random;

public class Generator {

    static int[] addressid = {1,20};
    static int[] pos = {1,5};
    static int[] empId ={1,12};
    static int[] ordId ={3,20};
    static String[] rcAll = {"6615019611","8860270338","1578904862","4717368948","4106301511","0117149123","7422367885","9378977559","5186706552","6023896125","4529709148","8266047540","5670928165","7933441605","0897426073","4065265421","5888434886","5614808227","1633263596","1088427069","8406376730","6105475933","2194035905","4140673944","4231229481","6052687893","6681552557","2316413772","7509930312","7070794070","4461036837","4566173454","0546293865","6085636021","6263132232","8937891625","4107448488","7107892542","5600705982","0420395760","0175204515","7926610549","6411308689","5536572194","3112915799","1838499091","7281304263","6415138472","9266961337","6362731963"};


    public static void GeneratePatient() {
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

    public static void GenerateEmployee() {
        String[] fname  = {"Pepe", "Sarka", "Dana","Lenka","Veronika","Vojta"};
        String[] lname  = {"Nepomuk", "Novak","Prvni","Osterich","David","Dalni","Musil"};
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        StringBuilder rcsb = new StringBuilder().append("{\"");
        for (int i = 0; i < 10; i++) {
            String rc = randomRC(r);
            //insert into Zamestnanci values ('Pepr Per', 'Mudr', 5, 1)
            sb.append(String.format("INSERT INTO Zamestnanci VALUES ('%s', %s, %d, %d)%n", random(fname,r)+" "+random(lname,r),r.nextInt(10)<6?"'Mudr'":"NULL",i%addressid[1]+1,i%pos[1]+1));
            rcsb.append(rc).append("\",\"");
        }
        System.out.println(sb.toString());
        System.out.println(rcsb.append("}").toString());
    }

    public static void GenerateEmployeeClinic(){
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = empId[0]; i <= empId[1]; i++) {
            //insert into Zamestnanec_Ordinace values (1, 1, '2021-01-04 23:37:28', NULL)
            sb.append(String.format("INSERT INTO Zamestnanec_Ordinace VALUES (%d, %d, '%s', %s)%n", i,randInt(ordId, r),randTime(r),"NULL"));
        }
        System.out.println(sb.toString());
    }

    public static void GenerateVisit() {
        String[] popis = {"Prisel a zkontroloval jsem ho. Byl jako okurka","Prisel a zkontroloval jsem ho a nic.","Nic nestalo","To byl zazitek", "Nechtel bych to mit","Dobre pokecali", "Proste nevim co napsat","..."} ;
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        StringBuilder rcsb = new StringBuilder().append("{\"");
        for (int i = 0; i < 20; i++) {
            String rc = randomRC(r);
            //INSERT INTO Navstevy values('9845612345', empId,ordId, '2020-03-10 11:37:28', 'Prisel a zkontroloval jsem ho. Byl jako okurka')
            sb.append(String.format("INSERT INTO Navstevy VALUES ('%s', %d, %d,'%s', '%s')%n", random(rcAll,r),randInt(empId,r),randInt(ordId,r),randTime(r),random(popis,r)));
            rcsb.append(rc).append("\",\"");
        }
        System.out.println(sb.toString());
        System.out.println(rcsb.append("}").toString());
    }

    private static String randTime(Random r) {
        return "20"+randInt(new int[]{10,11},r)+"-0"+randInt(new int[]{1,9},r)+"-"+randInt(new int[]{10,28},r)+" "+(randInt(new int[]{10,18},r))+":"+randInt(new int[]{15,45},r)+":00";
    }

    private static int randInt(int[] a, Random r){
        return r.nextInt(a[1]-a[0]+1)+a[0];
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
}
