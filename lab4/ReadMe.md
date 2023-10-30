## Java EE med tomcat
Grunduppgiften består av två deluppgifter. Den första deluppgiften är att implementera gissningsspelet från L2 (igen), fast denna gång med tomcat. Varje klient som ansluter till tomcat-servern får nu en egen tråd, d v s det är inget ni behöver hantera längre, dessutom kan ni läsa av parametrar via request.getParameter() och hantera sessioner via request.getSession(). Tanken med detta är att ni ska se hur mycket av det som tidigare gjordes "manuellt" som nu finns inbäddat i Java EE.

De viktigaste klasserna i Java EE som ni behöver för denna uppgift är främst HttpServletLinks to an external site.  där en instans är dedikerad åt att hantera http-requestLinks to an external site.:s och generera http-responseLinks to an external site.:s. När ni behöver spåra klienter från ett anrop till ett annat behöver ni även HttpSessionLinks to an external site., här lagras sådant som ni associerade med er kaka tidigare (tomcat använder under huven en kaka med namnet JSESSIONID för att spåra klienter mellan olika anrop.

Designen ska följa MVC-designmönster med

JavaBeans (som Model, återanvänd GuessBean från L2)
HttpServlet(s) (som Controller)
jsp-sidor (som View)
Det behövs inte många rader för att lösa uppgiften (jämfört med L2).


Den andra uppgiften är att skriva ett quiz-uppgift som i korthet fungerar enligt följande: Användaren anger användarnamn=epost(uppdaterad 14:e dec) och lösenord på en login-sida, efter inloggning presenteras användaren för valet att göra ett av de tillgängliga quiz:en tillsammans med en visning av tidigare resultat. Det finns inget krav på att ha ett administratörsgränssnitt för att lägga till användare, frågesporter eller frågor. Varje test har ett fast antal frågor där dessa är av flervalstyp (kryssrutor/checkbox). Det rekommenderas att du använder följande db-struktur (14:e dec: uppdaterad bild med samma struktur som tidigare):
    Skärmklipp-1.PNG

 

Notera: Netbeans/Tomcat/Derby är den enda miljö vi stödjer under laborationer. Det betyder inte att vi kräver att du använder den här kombinationen, men det är upp till dig att fixa en fungerande kombination av IDE/applikationsserver/DB om du väljer andra än dessa. En fungerande kombination finns under Filer till vänster.

### Extrauppgift: 
Använd Jakarta Persistence (JPA) för att spegla result-objekten i databasen. I stort sett innebär detta att det (utan SQL-kod) ska göras en insert i tabellen results när ett nytt result-objekt skapas. Uppdaterat 16:e december: Det finns under "Filer/L4" ett Netbeans-projekt "T_JPA" som skapar två db-speglade objekt (och raderar ett av dem), det behöver dock konfigureras om i "persistence.xml" för er db-anslutnings parametrar (db-namn, användarnamn et c).