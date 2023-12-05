drop table if exists ranking CASCADE;


CREATE TABLE ranking (
                         summonerName VARCHAR(100),
                         riotTagline varchar(50),
                         wins INT,
                         losses INT,
                         tier VARCHAR(30),
                         leaguePoints INT,
                         profileIconId INT,
                         championId1 INT,
                         championId2 INT,
                         championId3 INT,
                         gametype INT,
                         PRIMARY KEY (summonerName),
                         FOREIGN KEY (championId1) REFERENCES champion(championId),
                         FOREIGN KEY (championId2) REFERENCES champion(championId),
                         FOREIGN KEY (championId3) REFERENCES champion(championId)
);
