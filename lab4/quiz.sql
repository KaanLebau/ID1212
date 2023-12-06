--DROP TABLE results;
--DROP TABLE selector;
--DROP TABLE questions;
--DROP TABLE quizzes;
--DROP TABLE users;

CREATE TABLE users (
	id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	username VARCHAR(32) UNIQUE NOT NULL,
	password VARCHAR(32) NOT NULL
);
INSERT INTO users (username,password) VALUES ('ada@kth.se', 'qwerty');
INSERT INTO users (username,password) VALUES ('beda@kth.se', 'asdfgh');

CREATE TABLE questions (
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	text VARCHAR(64) NOT NULL,
	options VARCHAR(64) NOT NULL,
	answer VARCHAR(64) NOT NULL
);

INSERT INTO questions (text,options,answer) VALUES ('Which planets are larger than earth?', 'Mercury/Mars/Saturn', '0/0/1');
INSERT INTO questions (text,options,answer) VALUES ('Which planets are farther away from the sun than earth?', 'Mercury/Mars/Saturn', '0/1/1');
INSERT INTO questions (text,options,answer) VALUES ('Which planets have rings?', 'Mercury/Mars/Saturn', '0/0/1');

CREATE TABLE quizzes (
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	subject VARCHAR(64) NOT NULL
);

INSERT INTO quizzes (subject) VALUES ('Astronomy');

CREATE TABLE selector(
	quiz_id INT NOT NULL REFERENCES quizzes(id),
	question_id INT NOT NULL REFERENCES questions(id)
);
INSERT INTO selector (quiz_id, question_id) VALUES (1,1);
INSERT INTO selector (quiz_id, question_id) VALUES (1,2);
INSERT INTO selector (quiz_id, question_id) VALUES (1,3);

CREATE TABLE results(
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	user_id INT NOT NULL REFERENCES users(id),
	quiz_id INT NOT NULL REFERENCES quizzes(id),
	score INT NOT NULL
);
INSERT INTO results (user_id,quiz_id,score) VALUES (1,1,0);

