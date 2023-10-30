# Laboration 1: Socketar & Trådar

## Uppgift
Uppgiften består i att skriva ett socket-baserat chatsystem med hjälp av klasserna java.net.Socket och java.net.ServerSocket. Det kan bestå av bl a föjande klasser, förslagsvis:

### ChatServer:
 En instans av denna representerar servern. Har en tråd till var och en av de klienter som för närvarande är anslutna men också en tråd för att lyssna efter nya inkommande anslutningar från nya klienter, dessa bör representeras av en instans ClientHandler
### ChatClient: 
En instans av denna representerar klienten. Har två trådar, en för att lyssna efter inkommande meddelanden från servern och en för att skicka meddelanden till servern.
Det finns inget krav på att hantera användarinloggning eller chattrum. Lätt och enkel. Dessa är dock krav:

Klienter ska kunna lämna chatten utan att krascha servern.
Om servern går ner ska klienterna ge ett meddelande istället för att krascha.
Både klient och server ska kunna köras på olika JVM. 
Testa att programmet verkligen fungerar genom att skapa en server och två klienter, du kommer alltså att ha tre stycken samtidiga javaprogram som körs. Samtliga program ska kunna köras från olika datorer, detta kan ni testa genom att använda t ex ssh:a mot t ex student-shell.sys.kth.se för att ansluta tillbaka till er dator.

## Extrauppgift

Installera en nätverkssniffer (förslag Wireshark), spela in lite trafik från din chatt. Frågor
- Vad betyder TCP-headers i datagrammen?
- Vad betyder flaggorna ACK/SYN/SEQ/PSH/FIN?


Användbara klasser:
java.net.SocketLinks to an external site.
java.net.ServerSocketLinks to an external site.

Notera:

Om ni arbetar i par ska bara en av er ladda upp koden, annars blir det plagiatmatchning.
Ladda upp koden före (eller under) presentationen, annars kommer betyget försvinna (ny canvasinlämning kräver ny betygsättning)