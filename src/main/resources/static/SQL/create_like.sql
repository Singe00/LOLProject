drop table if exists likepost CASCADE;

CREATE TABLE likepost (
                      likeid INT AUTO_INCREMENT PRIMARY KEY,
                      postid INT,
                      userid INT,
                      FOREIGN KEY (postid) REFERENCES post(postid) ON DELETE CASCADE,
                      FOREIGN KEY (userid) REFERENCES user(userid) ON DELETE CASCADE
);
