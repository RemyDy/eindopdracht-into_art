# Into Art

## Beschrijving van de applicatie

Into Art is een schoolproject dat dient als proof of concept voor het maken van een REST api.
Het doel van de applicatie is om kunstwerken te catalogiseren in een beveiligde omgeving.
Daarnaast biedt het de mogelijkheid om in een niet beveiligde omgeving te abonneren op nieuwsbrieven.

De applicatie is geschreven in de programmeertaal Java.
Lees hieronder verder voor uitleg om de applicatie lokaal te kunnen draaien.

* Java
* IDE
* Maven
* Git & Github
* Database
* Spring Boot
* Postman

## Java Development Kit
De applicatie is geschreven met de op dit moment hoogste LTS (Long Term Support) versie, namelijk Java 17.
Er zijn verschillende aanbieders waar gebruik gemaakt van kan worden, de applicatie is geschreven met Oracle OpenJDK.
Meer informatie is hier te vinden (https://openjdk.org/projects/jdk/17/)

## IDE installeren
Als eerste dien je een code-editor te downloaden en te installeren om de app te kunnen starten.
Zo'n editor wordt ook wel een Integrated Development Environment (IDE) genoemd.
Er zijn meerdere mogelijkheden zoals o.a. Eclipse, maar zelf gebruik ik IntelliJ IDEA van de uitgever JetBrains.
Je kunt IntelliJ downloaden en installeren via deze link: (https://www.jetbrains.com/idea/download)

Let op: wil je de gratis versie gebruiken dien je de Community versie te downloaden.
De gratis versie is voldoende voor deze applicatie.

Voor meer mogelijkheden kan je de betaalde versie genaamd Ultimate downloaden.
Een uitgebreide uitleg over de installatie procedure en het instellen kan je vinden op de website van JetBrains.

## Maven
Deze applicatie maakt gebruik van Maven om benodigde bibliotheken te managen.
Dit houdt in dat door het aanpassen van de benodigde dependencies in het POM.XML-bestand de benodigde
afhankelijkheden worden gedownload en geïnstalleerd.
Meer informatie kan je hier vinden (https://maven.apache.org/what-is-maven.html).
Bij het klonen van de repository wordt deze automatisch meegeïnstalleerd.

## Github
Git en Github is de meest gebruikte manier om versiebeheer en backups van softwareapplicaties te managen. 
Je kan de applicatie binnenhalen door de url van het project te kopiëren van de github site en deze te klonen in je IDE.
Dat kan je in IntelliJ doen via de menubalk en te klikken op File - New - Project from version control.
Je IDE zal de applicatie downloaden en alle benodigde dependencies automatisch installeren.

## Database installeren 
Er zijn in de applicatie entities gemaakt die via de IDE een verbinding kunnen maken met een relationele database.
De database zorgt ervoor dat gegevens die gegenereerd en aangemaakt worden door de applicatie opgeslagen kunnen worden.
Deze gegevens kunnen dan weer opgevraagd worden door het uitvoeren queries.
Er zijn vele soorten database applicaties waar gebruik van gemaakt kan worden.
Voorbeelden hiervan zijn o.a. SQL Server en MySql, maar zelf maak ik gebruik ik PostgreSQL.

PostgreSQL is een gratis database programma en kan je op elk gewenst systeem installeren.
Het database programma kan je installeren via (https://www.postgresql.org/download).
Via het programma pgAdmin kan je de database bevragen, deze kan je installeren via (https://www.pgadmin.org/).
Uitgebreide informatie over de installatie kun je o.a. vinden op de officiële website van de leverancier.

## Spring Boot
Spring Boot neemt de configuratie uit handen door dit automatisch te implementeren.
Hierdoor hoef je niet alles zelf te coderen, je kan gebruik maken van de hulpmiddelen die Spring Boot biedt. 
Into Art is geschreven met behulp van het framework Spring Boot.
Bij het klonen van de applicatie zal Spring Boot meegenomen worden in de installatie.
Zelf een project opzetten kan heel eenvoudig via Spring Initialzr (https://start.spring.io/).

## Postman
Postman is een applicatie die het mogelijk maakt om software die nog in productie is te testen.
Door Into Art te starten via Localhost-Port 8080 kan je endpoints benaderen via Postman.
Dit kan je doen door een Http request te sturen naar de url van het betreffende endpoint dat je wilt bereiken. 
Postman kan je installeren via https://www.postman.com/product/rest-client.

## Contact
Wil je mij contacteren?
remco.schut@novi-education.nl