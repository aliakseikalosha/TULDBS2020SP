--1 seznam zam�stnanc� podle polikliniky(asi predelat)
--vno�en� SELECT v SELECT

--2 (po�et n�v�t�v pacientu v ordinac�ch)
--ordinace se 2 a vice navstevami
--vno�en� SELECT ve FROM
--SELECT O.id_ordinace, COUNT(x.id_ordinace)
--FROM Ordinace O,
--	(
	
--		SELECT id_ordinace
--		FROM Navstevy

--	) x
--	WHERE x.id_ordinace = O.id_ordinace

--3 seznam pacient� v ur�it�m m�st� (t�eba Liberec)
-- jmena pacientu kteri navstivili v case od data:)
--vno�en� SELECT ve WHERE
SELECT jmeno
FROM Pacienti
WHERE rodne_cislo IN (
	SELECT pacienti_rodne_cislo
	FROM Navstevy
	WHERE datum > '2011-05-01 00:00:00'
)
--4 po�ty pacient� ve m�stech
--GROUP BY

--5 seznam doktor�(!), kte�� nem�li n�v�t�vy v ur�it�m obdob�
--mno�inovou operaci

--6 seznam pacient� a zam�stnanc�,kte�� poch�z� ze stejn�ho m�sta
--LEFT JOIN