INSERT INTO courses(course_id, course_name, course_desc)VALUES('ID1206', 'Operating Systems', 'This course will deepen students knowledge in designing Operating Systems (OS) and Systems Programming. The course consists of five modules each focusing on a specific topic: Process management, Process synchronization, Memory management, Storage management and file systems, I/O management');
INSERT INTO courses(course_id, course_name, course_desc)VALUES('ID1212', 'Nätverkprogrammering', 'Kursen behandlar grundläggande begrepp för program som kommunicerar över sockets med TCP och UDP (+ HTTP), Hantering av trådar, Krypterade sockets med SMTP/IMAP/HTTPS, Introduktion till applikationsservrar, Distribuerade program som kommunicerar över RMI (SMTP/POP/IMAP), HTTP/2 och websockets.');
INSERT INTO topics(topic_name, courses_id)VALUES('Process management', 2);
INSERT INTO topics(topic_name, courses_id)VALUES('Process synchronization', 2);
INSERT INTO topics(topic_name, courses_id)VALUES('Memory management', 2);
INSERT INTO topics(topic_name, courses_id)VALUES('Module 1', 1);
INSERT INTO topics(topic_name, courses_id)VALUES('Module 2', 1);
INSERT INTO topics(topic_name, courses_id)VALUES('Lab 5', 1);
INSERT INTO topics(topic_name, courses_id)VALUES('Project', 1);
INSERT INTO roles(role_name)VALUES('ADMIN');
INSERT INTO roles(role_name)VALUES('TEACHER');
INSERT INTO roles(role_name)VALUES('TA');
INSERT INTO roles(role_name)VALUES('STUDENT');

INSERT INTO users(username)VALUES('admin');
INSERT INTO user_roles(user_id, role_id)VALUES(1,1);