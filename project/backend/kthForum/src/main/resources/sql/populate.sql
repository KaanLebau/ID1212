INSERT INTO roles(role_name)VALUES('ADMIN');
INSERT INTO roles(role_name)VALUES('TEACHER');
INSERT INTO roles(role_name)VALUES('TA');
INSERT INTO roles(role_name)VALUES('STUDENT');

INSERT INTO users(display_name, email, username)VALUES('Dan L', 'danljun@kth.se', 'danljun');
INSERT INTO users(display_name, email, username)VALUES('Admin', 'admin@kth.se', 'admin');
INSERT INTO users(display_name, email, username)VALUES('Stefan', 'stefan@kth.se', 'stefan');
INSERT INTO users(display_name, email, username)VALUES('Alice', 'alice@kth.se', 'alice');
INSERT INTO users(display_name, email, username)VALUES('Johan', 'johan@kth.se', 'johan');
INSERT INTO users(display_name, email, username)VALUES('Sarah', 'sarah@kth.se', 'sarah');
INSERT INTO users(display_name, email, username)VALUES('Jocke', 'jocke@kth.se', 'jocke');
INSERT INTO users(display_name, email, username)VALUES('Per', 'per@kth.se', 'per');
INSERT INTO users(display_name, email, username)VALUES('Sofia', 'sofia@kth.se', 'sofia');
INSERT INTO users(display_name, email, username)VALUES('Johanna', 'johanna@kth.se', 'johanna');

INSERT INTO course_user_roles(course_id, user_id, role_id)VALUES(1,1,1);
INSERT INTO course_user_roles(course_id, user_id, role_id)VALUES(2,1,1);
INSERT INTO course_user_roles(course_id, user_id, role_id)VALUES(3,1,1);
INSERT INTO course_user_roles(course_id, user_id, role_id)VALUES(4,1,1);
INSERT INTO course_user_roles(course_id, user_id, role_id)VALUES(5,2,1);

INSERT INTO courses(course_id, course_name, course_desc)VALUES('ID1206', 'Operating Systems', 'This course will deepen students knowledge in designing Operating Systems (OS) and Systems Programming. The course consists of five modules each focusing on a specific topic: Process management, Process synchronization, Memory management, Storage management and file systems, I/O management');
INSERT INTO courses(course_id, course_name, course_desc)VALUES('ID1212', 'Nätverkprogrammering', 'Kursen behandlar grundläggande begrepp för program som kommunicerar över sockets med TCP och UDP (+ HTTP), Hantering av trådar, Krypterade sockets med SMTP/IMAP/HTTPS, Introduktion till applikationsservrar, Distribuerade program som kommunicerar över RMI (SMTP/POP/IMAP), HTTP/2 och websockets.');
INSERT INTO courses(course_id, course_name, course_desc)VALUES('IE1204', 'Digital design', 'I den här kursen lär vi oss att bygga kombinatoriska och sekvensiella digitala kretsar, som utgör grunden för att i senare kurser bygga datorer. Vi lär oss både att analysera och designa med grundläggande byggblock som OR, NOR, AND, NAND och olika vippor, och prövar våra kretsar i datorsimuleringar och i praktiska kopplingar.')
INSERT INTO courses(course_id, course_name, course_desc)VALUES('IX1500', 'Diskret matematik', 'Kursen ger en introduktion till diskret matematik och dess tillämpningar. Matematikundervisningen sker problemorienterat och med datorstöd. Kursen är uppdelad i fyra delområden: Kombinatorik och mängdlära, heltal, relationer och ringar och grafteori. Undervisningen består av föreläsningar, övningar och projekt med redovisning.')
INSERT INTO courses(course_id, course_name, course_desc)VALUES('IL1331', 'VHDL Design', 'VHDL design, design, analysis, simulation, and synthesis in digital electronics.')

-- Topics for Operating Systems
INSERT INTO topics (topic_name, courses_id) VALUES ('Process management', 1);
INSERT INTO topics (topic_name, courses_id) VALUES ('Process synchronization', 1);
INSERT INTO topics (topic_name, courses_id) VALUES ('Memory management', 1);
INSERT INTO topics (topic_name, courses_id) VALUES ('Storage management', 1);
INSERT INTO topics (topic_name, courses_id) VALUES ('I/O management', 1);

-- Topics for Nätverkprogrammering
INSERT INTO topics (topic_name, courses_id) VALUES ('TCP och UDP', 2);
INSERT INTO topics (topic_name, courses_id) VALUES ('Applikationsservrar', 2);
INSERT INTO topics (topic_name, courses_id) VALUES ('Distribuerade program', 2);
INSERT INTO topics (topic_name, courses_id) VALUES ('Websockets', 2);

-- Topics for Digital design
INSERT INTO topics (topic_name, courses_id) VALUES ('Sekvensiella Kretsar', 3);
INSERT INTO topics (topic_name, courses_id) VALUES ('Grundläggande Byggblock', 3);
INSERT INTO topics (topic_name, courses_id) VALUES ('Praktiska Kopplingar', 3);
INSERT INTO topics (topic_name, courses_id) VALUES ('Datorsimuleringar', 3);

-- Topics for Diskret matematik
INSERT INTO topics (topic_name, courses_id) VALUES ('Kombinatorik', 4);
INSERT INTO topics (topic_name, courses_id) VALUES ('Heltal', 4);
INSERT INTO topics (topic_name, courses_id) VALUES ('Relationer och Ringar', 4);
INSERT INTO topics (topic_name, courses_id) VALUES ('Grafteori', 4);

-- Posts for 'Process management'
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Understanding Process Scheduling', 'Exploring various algorithms for process scheduling in operating systems.', '2021-06-10', '2021-06-30', 1, 1);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Concurrency: Pros and Cons', 'A discussion on the benefits and challenges of concurrent process execution.', '2021-07-01', '2021-07-21', 1, 4);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Process Creation and Termination', 'The lifecycle of a process from creation to termination explained.', '2021-07-15', '2021-08-05', 1, 3);

-- Posts for 'Process synchronization'
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Mutexes vs. Semaphores', 'Comparing mutexes and semaphores for process synchronization.', '2021-08-10', '2021-08-30', 2, 4);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Deadlock Prevention Strategies', 'Techniques and strategies to prevent deadlocks in multi-processing environments.', null, '2021-09-22', 2, 1);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Implementing Condition Variables', 'A guide on using condition variables for synchronization.', '2021-10-01', '2021-10-21', 2, 6);

-- Posts for 'Memory management'
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Exploring Paging Techniques', 'An in-depth look at paging and its impact on memory management.', null, '2021-11-30', 3, 4);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Understanding Segmentation', 'Segmentation as a memory management scheme in operating systems.', '2021-12-01', '2021-12-21', 3, 1);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Virtual Memory and Swapping', 'How virtual memory expands physical memory and the role of swapping.', '2022-01-05', '2022-01-25', 3, 6);

-- Posts for 'Storage management'
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('RAID Levels Explained', 'A breakdown of different RAID levels and their use cases in storage management.', '2022-02-10', '2022-03-02', 4, 8);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('File System Design', 'The architecture and design choices behind modern file systems.', '2022-03-15', '2022-04-04', 4, 1);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Disk Scheduling Algorithms', 'An exploration of how disk scheduling works and its algorithms.', '2022-04-10', '2022-04-30', 4, 9);

-- Posts for 'I/O management'
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Interrupts and I/O', 'How interrupts drive the modern I/O management strategies.', '2022-05-10', '2022-05-30', 5, 7);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Direct Memory Access', 'Understanding DMA and its role in efficient I/O operations.', '2022-06-01', '2022-06-21', 5, 10);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Device Drivers and Management', 'The importance of device drivers in the ecosystem of I/O management.', '2022-07-05', '2022-07-25', 5, 1);

-- Posts for 'TCP och UDP'
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Skillnader mellan TCP och UDP', 'En djupgående jämförelse mellan TCP och UDP protokoll och deras användningsområden.', '2021-06-10', '2021-06-30', 6, 1);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('TCP:s pålitlighet mot UDP:s hastighet', 'Diskussion om TCP:s pålitlighet och hur det står i kontrast till UDP:s överföringshastighet.', '2021-07-01', '2021-07-21', 6, 3);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Användningsfall för UDP', 'Exempel på situationer där UDP är att föredra framför TCP.', '2021-07-15', '2021-08-05', 6, 4);

-- Posts for 'Applikationsservrar'
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Välja rätt applikationsserver', 'Viktiga faktorer att överväga när man väljer applikationsserver för sin nästa projekt.', '2021-08-10', '2021-08-30', 7, 1);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Applikationsservrar och Microservices arkitektur', 'Hur applikationsservrar kan användas effektivt i en microservices-arkitektur.', '2021-09-02', '2021-09-22', 7, 5);

-- Posts for 'Distribuerade program'
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Utmaningar med distribuerade system', 'Gemensamma utmaningar som uppstår när man utvecklar och underhåller distribuerade program.', '2021-10-01', '2021-10-21', 8, 1);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Fördelar med distribuerade program', 'Diskussion om fördelarna med att använda distribuerade program över monolitiska applikationer.', '2021-11-10', '2021-11-30', 8, 6);

-- Posts for 'Websockets'
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Introduktion till Websockets', 'En introduktion till Websockets och hur de möjliggör tvåvägskommunikation i realtid.', '2021-12-01', '2021-12-21', 9, 1);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Websockets mot traditionell polling', 'Jämförelse av Websockets med traditionell polling när det gäller prestanda och serverbelastning.', '2022-01-05', '2022-01-25', 9, 7);

-- Posts for 'Sekvensiella Kretsar'
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Grundstenar för Sekvensiella Kretsar', 'En introduktion till sekvensiella kretsars värld och dess grundläggande komponenter.', '2022-02-10', '2022-02-20', 10, 1);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Flip-flop och dess användningar', 'Utforskar de olika typerna av flip-flops och deras roll i sekvensiella kretsar.', '2022-03-01', '2022-03-15', 10, 8);

-- Posts for 'Grundläggande Byggblock'
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Byggblock för digitala kretsar', 'En djupdykning i de grundläggande byggblocken som används för att konstruera digitala kretsar.', '2022-04-10', '2022-04-25', 11, 1);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Integrerade kretsar och deras evolution', 'Hur integrerade kretsar har utvecklats över tid och revolutionerat elektronikindustrin.', '2022-05-05', '2022-05-20', 11, 9);

-- Posts for 'Praktiska Kopplingar'
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Kopplingsscheman för nybörjare', 'En guide för nybörjare om hur man skapar och läser kopplingsscheman för praktiska projekt.', '2022-06-01', '2022-06-15', 12, 1);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Felkällor i praktiska kopplingar', 'Vanliga problem och felkällor man kan stöta på när man arbetar med praktiska kopplingar.', '2022-07-10', '2022-07-25', 12, 10);

-- Posts for 'Datorsimuleringar'
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Simuleringsmjukvara för elektronik', 'Översikt av populära datorsimuleringsprogram för elektronik och deras funktioner.', '2022-08-05', '2022-08-20', 13, 1);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Simulering vs Verklighet', 'Jämför verkliga resultat med datorsimuleringar inom elektronik.', '2022-09-01', '2022-09-15', 13, 4);

-- Posts for 'Kombinatorik'
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Kombinatorikens grunder', 'En introduktion till kombinatorik och de grundläggande principerna bakom.', '2022-10-05', '2022-10-20', 14, 1);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Tillämpningar av kombinatorik', 'Utforskar hur kombinatorik används i olika fält som datavetenskap och matematik.', '2022-11-10', '2022-11-25', 14, 5);

-- Posts for 'Heltal'
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Talteori och Heltal', 'En diskussion om talteori och dess relation till egenskaper av heltal.', '2022-12-01', '2022-12-15', 15, 1);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Mystiken med primtal', 'Utforska primtalens värld och deras betydelse i dagens kryptografi.', '2023-01-05', '2023-01-20', 15, 6);

-- Posts for 'Relationer och Ringar'
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Grundläggande om algebraiska strukturer', 'En översikt av relationer och ringar inom algebra.', '2023-02-10', '2023-02-25', 16, 1);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Ringteori i kryptografi', 'Användningen av ringteori inom kryptografi och dess betydelse för datasäkerhet.', '2023-03-15', '2023-03-30', 16, 7);

-- Posts for 'Grafteori'
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Introduktion till Grafteori', 'Grundläggande koncept i grafteori och dess matematiska definitioner.', '2023-04-10', '2023-04-25', 17, 1);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Användning av grafer i nätverk', 'Hur grafteori tillämpas för att lösa problem inom nätverkstopologi och internetrouting.', '2023-05-10', '2023-05-25', 17, 8);

-- Comments for Post ID 1 (Process management)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Fascinating discussion on scheduling. Looking forward to more insights.', '2021-06-11', '2021-08-22', 1, 1);
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('Can we apply these algorithms in distributed systems as well?', '2021-06-12', 1, 3);

-- Comments for Post ID 2 (Process management)
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('Concurrency is often misunderstood. Thanks for shedding light on this.', '2021-07-02', 2, 4);
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('Your pros and cons list is very clear and helpful.', '2021-07-03', 2, 5);

-- Comments for Post ID 3 (Process management)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Process termination is a critical concept. Thanks for explaining!', '2021-07-16', '2021-08-12', 3, 1);
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('What happens to the child processes when a parent process is terminated?', '2021-07-17', 3, 7);

-- Comments for Post ID 4 (Process synchronization)
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('Mutexes and semaphores are key in sync, great comparison.', '2021-08-11', 4, 3);
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('How can we choose between mutexes and semaphores in a new project?', '2021-08-12', 4, 1);

-- Comments for Post ID 5 (Process synchronization)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Deadlocks are tricky. This prevention strategy list is a lifesaver.', '2021-09-23', '2021-10-12', 5, 4);
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('Could you also touch upon deadlock detection?', '2021-09-24', 5, 1);

-- Comments for Post ID 6 (Process synchronization)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Condition variables are a new topic for me. Thanks for the info!', '2021-10-02', '2021-11-12', 6, 6);
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('Is there a performance impact when using condition variables?', '2021-10-03', 6, 1);

-- Comments for Post ID 7 (Memory management)
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('Paging techniques are so important in OS design.', '2021-12-02', 7, 1);
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('Could you explain more about page replacement algorithms?', '2021-12-03', 7, 8);

-- Comments for Post ID 8 (Memory management)
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('Segmentation is a complex topic, but this post clarifies a lot!', '2022-01-06', 8, 5);
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('How does segmentation differ from paging in practical terms?', '2022-01-07', 8, 1);

-- Comments for Post ID 9 (Memory management)
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('Virtual memory is such an interesting concept in computing.', '2022-01-26', 9, 6);
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('I would love to see more on swapping techniques!', '2022-01-27', 9, 1);

-- Comments for Post ID 10 (Storage)
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('RAID is a game-changer for data redundancy and performance.', '2022-03-03', 10, 8);
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('Great breakdown of the different RAID levels!', '2022-03-04', 10, 1);

-- Comments for Post ID 11 (Storage)
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('File system design is so crucial for efficiency. Loved this read.', '2022-04-05', 11, 5);
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('Can you talk about distributed file systems in the future?', '2022-04-06', 11, 1);

-- Comments for Post ID 12 (Storage)
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('Disk scheduling can get complex, this exploration is helpful.', '2022-05-01', 12, 4);
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('Any tips on optimizing scheduling for SSDs?', '2022-05-02', 12, 1);

-- Comments for Post ID 13 (I/O management)
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('Interrupts are a cornerstone of I/O. Thanks for this post!', '2022-05-31', 13, 7);
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('I never understood I/O management until this post, thanks!', '2022-06-01', 13, 1);

-- Comments for Post ID 14 (I/O management)
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('DMA really speeds things up for I/O, doesn''t it?', '2022-06-22', 14, 10);
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('It would be great to see more on I/O optimization!', '2022-06-23', 14, 1);

-- Comments for Post ID 15 (I/O management)
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('Device drivers are so underrated. This was an informative read.', '2022-07-26', 15, 3);
INSERT INTO comments (comment, created, posts_id, user_id) VALUES ('Could you delve into kernel vs user space drivers?', '2022-07-27', 15, 1);

-- Comments for Post ID 16 (TCP och UDP)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('TCP garanterar att alla paket kommer fram i rätt ordning.', '2021-07-02', NULL, 16, 3);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('UDP är verkligen snabbare men inte lika pålitlig.', '2021-07-03', '2021-07-10', 16, 5);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('För spel är UDP ofta det bästa valet på grund av dess låga latens.', '2021-07-04', NULL, 16, 4);

-- Comments for Post ID 17 (TCP och UDP)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('TCP:s flödeskontroll är en viktig egenskap för tillförlitliga nätverk.', '2021-07-16', NULL, 17, 6);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Hur balanserar man bäst mellan TCP och UDP i en applikation?', '2021-07-17', '2021-07-24', 17, 7);

-- Comments for Post ID 18 (TCP och UDP)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('UDP används ofta för streaming och spel.', '2021-07-15', NULL, 18, 8);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('För videokonferenser kan en kombination av TCP och UDP vara effektiv.', '2021-07-16', NULL, 18, 9);

-- Comments for Post ID 19 (Applikationsservrar)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Vilka är de bästa applikationsserverna för Java EE?', '2021-08-31', NULL, 19, 10);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Mikrotjänster har verkligen ändrat spelreglerna för serverarkitektur.', '2021-09-01', NULL, 19, 3);

-- Comments for Post ID 20 (Applikationsservrar)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Hur skalar man en applikationsserver effektivt?', '2021-09-03', '2021-09-10', 20, 4);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Kubernetes verkar vara en bra passning för att hantera applikationsservrar.', '2021-09-04', NULL, 20, 5);

-- Comments for Post ID 21 (Distribuerade program)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Consistency, Availability, Partition tolerance - CAP-teoremet är nyckeln.', '2021-10-22', NULL, 21, 3);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Att hantera tillstånd i distribuerade system är en av de största utmaningarna.', '2021-10-23', NULL, 21, 4);

-- Comments for Post ID 22 (Distribuerade program)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Distribuerade databaser är intressanta i detta sammanhang.', '2021-11-01', '2021-11-08', 22, 5);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Finns det någon bra litteratur om designmönster för distribuerade program?', '2021-11-02', NULL, 22, 6);

-- Comments for Post ID 23 (Websockets)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Websockets är perfekta för chattapplikationer.', '2021-12-22', NULL, 23, 7);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Kan websockets användas tillsammans med REST API:er?', '2021-12-23', '2021-12-30', 23, 8);

-- Comments for Post ID 24 (Websockets)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Polling fungerar, men websockets är så mycket bättre för realtid.', '2022-01-26', NULL, 24, 9);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Med websockets känns det som att webben äntligen är en live-plattform.', '2022-01-27', NULL, 24, 10);

-- Comments for Post ID 25 (Sekvensiella Kretsar)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Flip-flops är grundstenar i tidsberoende kretsar.', '2022-03-16', NULL, 25, 1);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Finns det goda resurser för att lära sig mer om sekvensiella kretsar?', '2022-03-17', NULL, 25, 3);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Att förstå sekvensiella kretsar är nyckeln till att utforma effektiva digitala system.', '2022-03-18', '2022-03-25', 25, 4);

-- Comments for Post ID 26 (Sekvensiella Kretsar)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Jag har alltid undrat hur data lagras i sekvensiella kretsar.', '2022-04-11', NULL, 26, 1);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Flip-flops och latches är så intressanta!', '2022-04-12', '2022-04-19', 26, 5);

-- Comments for Post ID 27 (Grundläggande Byggblock)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Integrerade kretsar har revolutionerat elektronikbranschen.', '2022-05-21', NULL, 27, 1);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Hur började utvecklingen av integrerade kretsar?', '2022-05-22', NULL, 27, 3);

-- Comments for Post ID 28 (Grundläggande Byggblock)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Digitala kretsar är verkligen grunden för all modern teknologi.', '2022-06-16', NULL, 28, 1);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Byggblocken i digitala kretsar är så fascinerande.', '2022-06-17', NULL, 28, 6);

-- Comments for Post ID 29 (Praktiska Kopplingar)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Det är viktigt att börja med grundläggande kopplingsscheman.', '2022-07-26', NULL, 29, 1);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Felkällor kan vara frustrerande, men de är också lärorika.', '2022-07-27', '2022-08-03', 29, 7);

-- Comments for Post ID 30 (Praktiska Kopplingar)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Praktiska kopplingar är ett utmärkt sätt att lära sig elektronik.', '2022-08-21', NULL, 30, 1);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Vilka verktyg rekommenderas för att bygga praktiska kopplingar?', '2022-08-22', NULL, 30, 8);

-- Comments for Post ID 31 (Datorsimuleringar)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Simuleringar är så användbara för att testa teorier innan praktisk implementering.', '2022-09-16', NULL, 31, 1);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Finns det gratis simuleringprogramvara som rekommenderas för nybörjare?', '2022-09-17', NULL, 31, 9);

-- Comments for Post ID 32 (Datorsimuleringar)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Simuleringar ger oss en förståelse för hur saker kommer att fungera i den verkliga världen.', '2022-10-21', NULL, 32, 1);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Jag föredrar alltid att testa mina kretsar i en simulering först.', '2022-10-22', NULL, 32, 10);

-- Comments for Post ID 33 (Kombinatorik)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Kombinatorik är så relevant i många matematiska och tekniska fält.', '2022-11-26', NULL, 33, 1);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Jag har alltid funnit kombinatorik fascinerande, speciellt dess tillämpningar.', '2022-11-27', NULL, 33, 3);

-- Comments for Post ID 34 (Kombinatorik)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Kombinatorik hjälper oss att förstå mönster och strukturer på djupet.', '2022-12-16', NULL, 34, 1);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Tillämpningarna av kombinatorik är verkligen breda och varierade.', '2022-12-17', NULL, 34, 4);

-- Comments for Post ID 35 (Heltal)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Primtal är verkligen mystiska men otroligt viktiga för kryptografi.', '2023-01-21', NULL, 35, 1);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Hur fungerar primtal inom kryptografiska algoritmer?', '2023-01-22', NULL, 35, 5);

-- Comments for Post ID 36 (Heltal)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Talteori ger en sådan fascinerande insikt i egenskaperna hos heltal.', '2023-02-26', NULL, 36, 1);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Jag älskar att utforska heltal och deras unika egenskaper.', '2023-02-27', NULL, 36, 6);

-- Comments for Post ID 37 (Relationer och Ringar)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Algebraiska strukturer som relationer och ringar är så viktiga i matematiken.', '2023-03-31', NULL, 37, 1);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Kan någon rekommendera bra resurser för att lära sig mer om ringteori?', '2023-04-01', NULL, 37, 7);

-- Comments for Post ID 38 (Relationer och Ringar)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Ringteori är så relevant inom områden som kryptografi.', '2023-05-26', NULL, 38, 1);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Jag är fascinerad av hur matematik appliceras på datasäkerhet.', '2023-05-27', NULL, 38, 8);

-- Comments for Post ID 39 (Grafteori)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Grafteori är en sådan grundsten inom datavetenskap.', '2023-04-26', NULL, 39, 1);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Grafer hjälper verkligen till att visualisera komplexa nätverksstrukturer.', '2023-04-27', NULL, 39, 9);

-- Comments for Post ID 40 (Grafteori)
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Användningen av grafer i nätverk är så intressant.', '2023-05-26', NULL, 40, 1);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('Jag gillar hur grafteori kan tillämpas för att lösa reala problem.', '2023-05-27', NULL, 40, 10)