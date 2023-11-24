DROP TABLE IF EXISTS dataset CASCADE;

CREATE TABLE dataset (
                          dataId int,
                          championId INT,
                          championName VARCHAR(50),
                          teamPosition varchar(8),
                          win INT,
                          lose INT,
                          timeS varchar(200),
                          spell varchar(50),
                          skill varchar(250),
                          skEvole varchar(60),
                          startItems varchar(70),
                          core1 varchar(100),
                          core2 varchar(100),
                          core3 varchar(150),
                          core4 varchar(150),
                          fragment varchar(150),
                          mr varchar(200),
                          sr varchar(150),
                          primary key (dataId)
);


