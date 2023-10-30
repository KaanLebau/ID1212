# Laboration 3: SSL/TLS-krypterade socket:ar

Din uppgift är att skriva ett program som ansluter till ditt @kth.se-konto, listar innehållet och sedan hämtar ett godtyckligt mejl. Du får (ännu) inte använda JavaMail (aldrig hört talas om det? bra! ) utan ska istället göra det "manuellt" enligt IMAP-protokollet. Du ska också skicka ett mail till dig själv med hjälp av SMTP-protokollet. Webbmejlen har följande konfiguration (hämtat från KTH-intranät):

Inställningar för att ta emot e-post (inkommande)

<b>Server:</b> webmail.kth.se

<b>Port:</b> 993

<b>Protokoll:</b> SSL/TLS

<b>Autentisering:</b> Normalt lösenord

Inställningar för att skicka e-post (utgående)


<b>Server:</b> smt.kth.se

<b>Port:</b> 587

<b>Protokoll:</b> STARTTLS

<b>Autentisering:</b> Normalt lösenord


## Notera:

I det första fallet (IMAP med SSL/TLS) börjar du med en krypterad session och i det andra fallet (SMTP med STARTTLS) går du över till kryptering under sessionen.
Fullständig dokumentation av IMAP finns i rfc3501Links to an external site. men för att lösa uppgiften räcker det att jämföra med en IMAP-session till exempel den här: https://en.wikipedia.org/wiki/Internet_Message_Access_ProtocolLinks to an external site.
Användbara exempel på SMTP-sessioner finns här:
https://www.samlogic.net/articles/smtp-commands-reference.htmLinks to an external site.
För att skicka användarnamn och lösenord i SMTP behöver du använda Base64-encoding och då kan https://docs.oracle.com/javase/8/docs/api/java/util/Base64.htmlLinks to an external site. vara användbart.
För att undvika att dela lösenord av olyckshändelse: Placera detta i en separat fil och läs in den till programmet.
Du behöver inget certifikat för denna deluppgift (autentiseringen sker endast med lösenord).

## Krav:
 Ni ska kunna förklara hur publika och privata nycklar i ett ett assymmetriskt chiffer + ett symmeriskt chiffer  erbjuder en säker överföring av symmetrisk nyckel (Alice och Bob...) samt hur + kryptografiska hash ger dataintegritet.

 

## Extrauppgift:
 Ändra nummergissningsspelet i L2 så att det använder kryptering och verifiera att en kommersiell (inte ditt eget hack) webbläsare kan ansluta (till https://localhost) och spela spelet. Eftersom det är kryptering på *servern* du ställer in, måste du ändra spelet för att använda kryptering med ett certifikat (använd nyckelverktyget keytool för att skapa ett självsignerat certifikat).


#### Krav:
 Utöver implementationsdelen (som ganska mycket följer en standardförfarande) ska du/ni även kunna förklara hur signering och verifiering med CA-fungerar.

