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


INSERT INTO courses (course_id, course_name, course_desc) VALUES ('CS2943', 'Network Programming', 'An in-depth course covering the fundamentals of network programming.');
INSERT INTO courses (course_id, course_name, course_desc) VALUES ('DH1452', 'Data Science Fundamentals', 'Introduction to the core concepts and techniques in data science.');
INSERT INTO courses (course_id, course_name, course_desc) VALUES ('MA4602', 'Machine Learning', 'Exploring machine learning algorithms and their applications.');
INSERT INTO courses (course_id, course_name, course_desc) VALUES ('DH9541', 'Cybersecurity', 'Comprehensive study of cybersecurity principles and practices.');
INSERT INTO courses (course_id, course_name, course_desc) VALUES ('CS8623', 'Data Science Fundamentals', 'Introduction to the core concepts and techniques in data science.');

INSERT INTO topics (topic_name, courses_id) VALUES ('Programming Basics', 1);
INSERT INTO topics (topic_name, courses_id) VALUES ('Quantum Algorithms', 2);
INSERT INTO topics (topic_name, courses_id) VALUES ('Quantum Algorithms', 3);
INSERT INTO topics (topic_name, courses_id) VALUES ('Quantum Algorithms', 4);
INSERT INTO topics (topic_name, courses_id) VALUES ('Programming Basics', 5);

INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('The impact of AI on modern society', 'This is a sample content for the post titled: The impact of AI on modern society', '2021-06-09', '2021-06-29', 1, 2);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Methods in data analysis and their effectiveness', 'This is a sample content for the post titled: Methods in data analysis and their effectiveness', '2021-06-01', '2021-06-02', 2, 2);
INSERT INTO posts (title, content, created, updated, topic_id, user_id) VALUES ('Methods in data analysis and their effectiveness', 'This is a sample content for the post titled: Methods in data analysis and their effectiveness', '2022-05-11', '2022-06-09', 3, 1);

INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('This topic is quite complex. Looking forward to more discussion.', '2022-06-07', '2022-06-22', 1, 6);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('I have a question about this topic...', '2022-05-02', '2022-05-07', 1, 8);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('This topic is quite complex. Looking forward to more discussion.', '2020-01-17', '2020-01-24', 2, 4);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('I have a question about this topic...', '2021-03-10', '2021-04-08', 2, 9);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('This is an insightful perspective. Thanks for sharing!', '2020-02-14', '2020-03-15', 3, 9);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('This is an insightful perspective. Thanks for sharing!', '2020-05-17', '2020-05-19', 3, 9);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('This topic is quite complex. Looking forward to more discussion.', '2022-06-07', '2022-06-22', 4, 6);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('I have a question about this topic...', '2022-05-02', '2022-05-07', 4, 8);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('This topic is quite complex. Looking forward to more discussion.', '2020-01-17', '2020-01-24', 5, 4);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('I have a question about this topic...', '2021-03-10', '2021-04-08', 5, 9);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('This is an insightful perspective. Thanks for sharing!', '2020-02-14', '2020-03-15', 6, 9);
INSERT INTO comments (comment, created, updated, posts_id, user_id) VALUES ('This is an insightful perspective. Thanks for sharing!', '2020-05-17', '2020-05-19', 6, 9);