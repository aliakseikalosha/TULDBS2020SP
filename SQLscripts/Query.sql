--1 seznam zam�stnanc� podle polikliniky(asi predelat)
--vno�en� SELECT v SELECT
SELECT id_poliklinika, count = (
        SELECT COUNT(id_ordinace)
        FROM Ordinace o
        WHERE p.id_poliklinika = o.id_poliklinika)
FROM Polikliniky p
--2 (po�et n�v�t�v pacientu v ordinac�ch)
--ordinace se 2 a vice navstevami
--vno�en� SELECT ve FROM
SELECT id_ordinace,nazev,id_poliklinika,COUNT(visit) as count
FROM (SELECT  o.id_ordinace,o.nazev,o.id_poliklinika, x.id_ordinace as visit
    FROM Ordinace o JOIN (SELECT * FROM Navstevy  ) as x ON  o.id_ordinace = x.id_ordinace
    )  u
GROUP BY u.id_ordinace,u.nazev,id_poliklinika
HAVING COUNT(visit) > ?

--3. jmena pacientu kteri navstivili v case od data:)
--vno�en� SELECT ve WHERE
SELECT jmeno
FROM Pacienti
WHERE rodne_cislo IN (
	SELECT pacienti_rodne_cislo
	FROM Navstevy
	WHERE datum > '2011-05-01 00:00:00'
)
--4 po�ty pacient� ve meste
--GROUP BY
SELECT mesto,COUNT(mesto)
FROM Pacienti p RIGHT JOIN (
    SELECT id_adresy, mesto
    FROM Adresy
) as x ON p.id_adresy = x.id_adresy
GROUP BY mesto
--5 seznam doktor�(!), kte�� nem�li n�v�t�vy v ur�it�m obdob�
--mno�inovou operaci
SELECT * FROM Zamestnanci
WHERE id_zamestnanec IN (
    SELECT id_zamestnanec FROM Zamestnanci
        EXCEPT
    SELECT id_zamestnanec
    FROM Navstevy
    WHERE datum > '2010-07-19 13:29:00' AND datum < '2011-01-13 13:31:00'
)
--6 Pocet navstev jednotlibych ordinai, serazenych sestupne
-- --LEFT JOIN
SELECT o.nazev, o.id_ordinace, o.id_poliklinika, COUNT(n.id_ordinace) as count
FROM Ordinace o  LEFT JOIN Navstevy n ON o.id_ordinace = n.id_ordinace
GROUP BY o.nazev, n.id_ordinace, o.id_ordinace,o.id_poliklinika
ORDER BY count DESC