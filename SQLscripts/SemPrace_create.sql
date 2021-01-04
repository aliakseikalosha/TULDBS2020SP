-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2020-12-17 23:09:02.563

-- tables
-- Table: Adresy
CREATE TABLE Adresy (
    mesto nvarchar(100)  NOT NULL,
    ulice nvarchar(100)  NOT NULL,
    psc int  NOT NULL,
    id_adresy int  NOT NULL,
    CONSTRAINT Adresy_pk PRIMARY KEY  (id_adresy)
);

-- Table: Navstevy
CREATE TABLE Navstevy (
    pacienti_rodne_cislo int  NOT NULL,
    id_zamestnanec int  NOT NULL,
    id_ordinace int  NOT NULL,
    datum datetime  NOT NULL,
    popis nvarchar(500)  NOT NULL,
    CONSTRAINT PK_Navstevy PRIMARY KEY  (id_zamestnanec,id_ordinace,pacienti_rodne_cislo,datum)
);

-- Table: Ordinace
CREATE TABLE Ordinace (
    id_ordinace int  NOT NULL IDENTITY,
    nazev nvarchar(100)  NOT NULL,
    id_poliklinika int  NOT NULL,
    CONSTRAINT Ordinace_pk PRIMARY KEY  (id_ordinace)
);

-- Table: Pacient_Ordinace
CREATE TABLE Pacient_Ordinace (
    id_ordinace int  NOT NULL,
    pacienti_rodne_cislo int  NOT NULL,
    CONSTRAINT Pacient_Ordinace_pk PRIMARY KEY  (id_ordinace,pacienti_rodne_cislo)
);

-- Table: Pacienti
CREATE TABLE Pacienti (
    rodne_cislo int  NOT NULL IDENTITY,
    id_adresy int  NOT NULL,
    jmeno nvarchar(100)  NOT NULL,
    CONSTRAINT Pacienti_pk PRIMARY KEY  (rodne_cislo)
);

-- Table: Polikliniky
CREATE TABLE Polikliniky (
    id_poliklinika int  NOT NULL IDENTITY,
    id_adresy int  NOT NULL,
    CONSTRAINT Polikliniky_pk PRIMARY KEY  (id_poliklinika)
);

-- Table: SeznamPozic
CREATE TABLE SeznamPozic (
    id_pozice int  NOT NULL IDENTITY,
    nazev nvarchar(100)  NOT NULL,
    CONSTRAINT SeznamPozic_pk PRIMARY KEY  (id_pozice)
);

-- Table: Zamestnanci
CREATE TABLE Zamestnanci (
    id_zamestnanec int  NOT NULL IDENTITY,
    jmeno nvarchar(100)  NOT NULL,
    titul nvarchar(10)  NULL,
    id_adresy int  NOT NULL,
    id_pozice int  NOT NULL,
    CONSTRAINT Zamestnanci_pk PRIMARY KEY  (id_zamestnanec)
);

-- Table: Zamestnanec_Ordinace
CREATE TABLE Zamestnanec_Ordinace (
    id_zamestnanec int  NOT NULL,
    id_ordinace int  NOT NULL,
    od datetime  NOT NULL,
    do datetime  NULL,
    CONSTRAINT Zamestnanec_Ordinace_pk PRIMARY KEY  (id_zamestnanec,id_ordinace)
);

-- foreign keys
-- Reference: Navstevy_Ordinace (table: Navstevy)
ALTER TABLE Navstevy ADD CONSTRAINT Navstevy_Ordinace
    FOREIGN KEY (id_ordinace)
    REFERENCES Ordinace (id_ordinace)
    ON DELETE  SET NULL;

-- Reference: Navstevy_Pacienti (table: Navstevy)
ALTER TABLE Navstevy ADD CONSTRAINT Navstevy_Pacienti
    FOREIGN KEY (pacienti_rodne_cislo)
    REFERENCES Pacienti (rodne_cislo);

-- Reference: Navstevy_Zamestnanci (table: Navstevy)
ALTER TABLE Navstevy ADD CONSTRAINT Navstevy_Zamestnanci
    FOREIGN KEY (id_zamestnanec)
    REFERENCES Zamestnanci (id_zamestnanec);

-- Reference: Ordinace_Polikliniky (table: Ordinace)
ALTER TABLE Ordinace ADD CONSTRAINT Ordinace_Polikliniky
    FOREIGN KEY (id_poliklinika)
    REFERENCES Polikliniky (id_poliklinika)
    ON DELETE  CASCADE;

-- Reference: Pacient_Ordinace_Ordinace (table: Pacient_Ordinace)
ALTER TABLE Pacient_Ordinace ADD CONSTRAINT Pacient_Ordinace_Ordinace
    FOREIGN KEY (id_ordinace)
    REFERENCES Ordinace (id_ordinace)
    ON DELETE  CASCADE;

-- Reference: Pacient_Ordinace_Pacienti (table: Pacient_Ordinace)
ALTER TABLE Pacient_Ordinace ADD CONSTRAINT Pacient_Ordinace_Pacienti
    FOREIGN KEY (pacienti_rodne_cislo)
    REFERENCES Pacienti (rodne_cislo)
    ON DELETE  CASCADE;

-- Reference: Pacienti_Adresy (table: Pacienti)
ALTER TABLE Pacienti ADD CONSTRAINT Pacienti_Adresy
    FOREIGN KEY (id_adresy)
    REFERENCES Adresy (id_adresy);

-- Reference: Polikliniky_Adresy (table: Polikliniky)
ALTER TABLE Polikliniky ADD CONSTRAINT Polikliniky_Adresy
    FOREIGN KEY (id_adresy)
    REFERENCES Adresy (id_adresy);

-- Reference: Table_15_Ordinace (table: Zamestnanec_Ordinace)
ALTER TABLE Zamestnanec_Ordinace ADD CONSTRAINT Table_15_Ordinace
    FOREIGN KEY (id_ordinace)
    REFERENCES Ordinace (id_ordinace)
    ON DELETE  CASCADE;

-- Reference: Table_15_Zamestnanci (table: Zamestnanec_Ordinace)
ALTER TABLE Zamestnanec_Ordinace ADD CONSTRAINT Table_15_Zamestnanci
    FOREIGN KEY (id_zamestnanec)
    REFERENCES Zamestnanci (id_zamestnanec);

-- Reference: Zamestnanci_Adresy (table: Zamestnanci)
ALTER TABLE Zamestnanci ADD CONSTRAINT Zamestnanci_Adresy
    FOREIGN KEY (id_adresy)
    REFERENCES Adresy (id_adresy);

-- Reference: Zamestnanci_SeznamPozic (table: Zamestnanci)
ALTER TABLE Zamestnanci ADD CONSTRAINT Zamestnanci_SeznamPozic
    FOREIGN KEY (id_pozice)
    REFERENCES SeznamPozic (id_pozice);

-- End of file.

