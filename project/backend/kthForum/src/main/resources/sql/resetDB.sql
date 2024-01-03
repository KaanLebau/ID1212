DROP TABLE users;
DROP TABLE course_user_roles;
DROP TABLE roles;
DROP TABLE posts;
DROP TABLE users;
DROP TABLE topics;
DROP TABLE courses;

TRUNCATE TABLE 
    course_user_roles,
    roles,
	comments,
	posts,
    users,
    topics,
    courses,
	users;

ALTER SEQUENCE courses_id_seq RESTART WITH 1;
ALTER SEQUENCE topics_id_seq RESTART WITH 1;
ALTER SEQUENCE posts_id_seq RESTART WITH 1;
ALTER SEQUENCE comments_id_seq RESTART WITH 1;
ALTER SEQUENCE users_id_seq RESTART WITH 1;
ALTER SEQUENCE roles_id_seq RESTART WITH 1;