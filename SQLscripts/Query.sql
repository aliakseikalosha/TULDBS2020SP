--1 seznam zamìstnancù podle polikliniky(asi predelat)
--vnoøený SELECT v SELECT

--2 (poèet návštìv pacientu v ordinacích)
--ordinace se 2 a vice navstevami
--vnoøený SELECT ve FROM
--SELECT O.id_ordinace, COUNT(x.id_ordinace)
--FROM Ordinace O,
--	(
	
--		SELECT id_ordinace
--		FROM Navstevy

--	) x
--	WHERE x.id_ordinace = O.id_ordinace

--3 seznam pacientù v urèitém mìstì (tøeba Liberec)
-- jmena pacientu kteri navstivili v case od data:)
--vnoøený SELECT ve WHERE
SELECT jmeno
FROM Pacienti
WHERE rodne_cislo IN (
	SELECT pacienti_rodne_cislo
	FROM Navstevy
	WHERE datum > '2011-05-01 00:00:00'
)
--4 poèty pacientù ve mìstech
--GROUP BY

--5 seznam doktorù(!), kteøí nemìli návštìvy v urèitém období
--množinovou operaci

--6 seznam pacientù a zamìstnancù,kteøí pochází ze stejného mìsta
--LEFT JOIN