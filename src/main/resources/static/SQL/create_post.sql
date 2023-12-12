drop table if exists post CASCADE;

CREATE TABLE post (
                      postid INT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255),
                      text TEXT,
                      category INT,
                      userid INT,
                      like_count VARCHAR(255),
                      FOREIGN KEY (userid) REFERENCES user(userid) ON DELETE CASCADE
);

ALTER TABLE post
    ADD COLUMN category INT;

ALTER TABLE post
    ADD COLUMN look INT;

ALTER TABLE post
    CHANGE COLUMN text content TEXT;

