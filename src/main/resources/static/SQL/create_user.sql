drop table if exists user CASCADE;

CREATE TABLE user (
                      email VARCHAR(100) NOT NULL,
                      oauth_type VARCHAR(50),
                      PRIMARY KEY (email)
);
