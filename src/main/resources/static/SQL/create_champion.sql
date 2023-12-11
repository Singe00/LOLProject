DROP TABLE IF EXISTS champion CASCADE;

CREATE TABLE champion (
                          championId INT,
                          championName VARCHAR(50),
                          championNameKr varchar(50),
                          nickName varchar(50),
                          rotation boolean,
                          position varchar(50),
                          initial varchar(50),
                          PRIMARY KEY (championId)
);



INSERT INTO champion (championId, championName, championNameKr, nickName, rotation, position, initial)
VALUES (910, 'Hwei', '흐웨이', null, false, 'AM', 'ㅎㅇㅇ');
