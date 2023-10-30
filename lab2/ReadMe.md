# Laboration 2, En simpel HTTP-server med socket:ar

Din uppgift är att skriva ett gissningsspel med sockets där dialogen kommer att vara enligt följande (när du ansluter till din webbläsare):

https://www.csc.kth.se/~stene/guess.php

## Krav på programmet: Det ska bestå av minst tre klasser:

en klass för inkommande HTTP-requests från webläsaren (Controller).
en klass för spellogiken (sessionsid för klienten, antal gissningar, hemliga talet, denna utgör Model)
en klass för att att generera HTTP-response med HTML (View).
Det ska använda java.net.Socket och java.net.ServerSocket-klasserna.
Notera:

Varje ny klient som ansluter ska leda till en ny instans av spelet ( = ett nytt spellogik-objekt) och ett "Set-Cookie"-fält lagt till i http-svaret.

Det ska vara en tråd per webbläsar-klient (går att återanvända kod från föregående laboration).

Webbläsaren skapar en ytterligare begäran om bokmärkesikonen "favicon.ico" (beroende webbläsare, testa och prova). Denna behöver ni filtrera bort på något vis.

Ett nytt webbläsarfönster (men inte flik) skapar vanligtvis en ny session (webbläsarberoende, testa och prova)

Du ska endast använda Java SE i lösningen, Java EE är ej tillåtet (det kommer i senare labb).

## Extrauppgift: 
Använd java.net.HttpURLConnectionLinks to an external site. för att simulera en webbläsare och spela spelet 100 gånger och presentera det genomsnittliga antalet gissningar.

## Notera:

Om du använder JDK 11 eller högre kan du använda java.net.http.HttpClientLinks to an external site.. klass (istället för HttpURLConnection).

Det finns inget krav på att göra en flertrådig lösning.