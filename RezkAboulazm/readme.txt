Unsere Implementierung befindet sich in Planner/webapps/Planner.war
Der Rest der Datei ist für den Apache Tomcat Server

Benutzung:
	Gebe folgenden Befehl in das Terminal ein:

	<filepath>/Planner/bin/startup.sh (alternativ im Ordner /Planner/bin/ "bash startup.sh" eingeben)

	Öffne einen Webbrowser und gebe : "localhost:8080/Planner" in die Adresszeile ein.
	Du wirst aufgefordert den Dateipfad zur germany Datei in den Browser einzugeben. (bei mir /home/rezk/Documents/Programmierprojekt/Phase_1_Backend/germany)

		Nun siehst du die Website
		In der unteren linken Ecke befinden sich 5 Buttons

		Mit klick auf "Load Graph" wird die germany Datei gelesen, die Knotenliste sortiert, und die Adjazenzliste aufgebaut (ca. 60 Sekunden) es wird ein alert gesendet sobald die Datei verarbeitet wurde		
		Mit klick auf "Select Start" kann man mit dem darauffolgenden Mausklick auf der Karte innerhalb Deutschlands einen Startpunkt auswählen
		Mit klick auf "Select Destination" kann man mit dem darauffolgenden Mausklick auf der Karte innerhalb Deutschlands einen Endpunkt auswählen
		Mit klick auf "Navigate" wird der kürzeste Pfad auf die Karte gelegt
		Mit klick auf "Clear" werden die Marker und der Pfad gelöscht

	Der Server lässt sich mit <filepath>/Planner/bin/shutdown.sh beenden. (alternativ im Ordner /Planner/bin/ "bash shutdown.sh" eingeben)
