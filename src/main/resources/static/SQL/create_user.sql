drop table if exists user CASCADE;

CREATE TABLE user (
                      userid INT AUTO_INCREMENT PRIMARY KEY,
                      email VARCHAR(100) NOT NULL UNIQUE,
                      oauth_type VARCHAR(50)
);
