CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL
);

ALTER TABLE users ADD CONSTRAINT unique_username UNIQUE (username);