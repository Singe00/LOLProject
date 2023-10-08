DROP TABLE IF EXISTS champion CASCADE;

CREATE TABLE champion (
                          championId INT,
                          championName VARCHAR(255),
                          championNameKr varchar(255),
                          nickName varchar(50),
                          rotation boolean,
                          PRIMARY KEY (championId)
);
