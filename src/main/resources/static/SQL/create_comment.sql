drop table if exists comment CASCADE;

CREATE TABLE comment (
                      commentid INT AUTO_INCREMENT PRIMARY KEY,
                      text VARCHAR(255),
                      userid INT,
                      postid INT,
                      FOREIGN KEY (postid) REFERENCES post(postid) ON DELETE CASCADE,
                      FOREIGN KEY (userid) REFERENCES user(userid) ON DELETE CASCADE
);
