Das Programmierprojekt - Routenplaner wurde wie folgt implementiert:

In der Klasse ReadFile wird die Graphdatei gelesen und in  zwei Arrays für Knoten und für Kanten gespeichert.

Anschließend wird der zur Eingabe nähste Knoten in  der Klasse FindNearesNode berechnet.
Hierzu wird das Array für die Knoten in der Klasse QuickSort nach dem Breitengrad sortiert.

Als nächstes werden one to one Anfragen in der Klasse Dijkstra abgearbeitet.
Hierzu wird zunächst einmalig die Adjazenzliste erstellt.
Die Dijkstra implementation basiert auf einer Adjazenzlisten Darstellung und einer PriorityQueue mit Binary Heap. 
Der Algorithmus wir nun korrekt frühzeitig abgebrochen wenn der Zielknoten aus der Priority Queue gezogen wird.
	
Schließlich wird die one to all Anfrage in der Klasse Dijkstra bearbeitet.
Diese ist gleich wie die one to one Anfrage implementiert, es wird jedoch nicht frühzeitig abgebrochen.

Anleitung zum ausführen für Ubuntu 20.04

	Das Projekt wird als .jar projekt geliefert
	Zum ausführen der Datei wird folgender Befehl benötigt:

	Befehl: (je Zeile mit Leerzeichen separiert)	
		java 
		-Xmx<size>M (Maximale heap größe die java bereitgestellt wird, ich habe mit Xmx5000M ausgeführt) 
		-jar -<filePath>/<fileName>.jar (hier den Pfad für die .jar Datei eingeben)
		-graph <filePath>/<fileName> (hier den Pfad für den Deutschlandgraphen eingeben)
		-lon <double> (hier den Breitengrad für die Suche des nähsten Knotens als double eingeben)
		-lat <double> (hier den Längengrad für die Suche des nähsten Knotens als double eingeben)
		-que <filePath>/<fileName>.que (hier den Pfad für das germany.que Dokument eingeben)
		-s <int> (hier den Startknoten für den oneToAll Dijkstra eingeben)
		-sol <filePath>/<fileName>.sol (hier den Pfad für das germany.sol Dokument eingeben)

Beispiel an meinem Rechner:

java -Xmx5000M -jar /home/user/Documents/Programmierprojekt/Phase_1_Backend/final.jar -graph /home/user/Documents/Programmierprojekt/Phase_1_Backend/germany -lon 9.098 -lat 48.746 -que /home/user/Documents/Programmierprojekt/Phase_1_Backend/germany.que -s 638394 -sol /home/user/Documents/Programmierprojekt/Phase_1_Backend/germany.sol


Auf meinem Rechner mit Intel Xeon E3-1231v3 ergeben sich die folgenden Zeiten:

	Lesen: ca. 40 sek
	Sortieren: ca. 5 sek
	FindNearestNode: ca 0,2 sek
	Je OneToOne: je nach Distanz ca. 1-11 sek
		Gesamtzeit für Deutschlandgraphen: 3,4 sek/oneToOne
	OneToAll: ca. 11 sek

Und einer max. Arbeitsspeichernutzung von ca. 5 GiB
