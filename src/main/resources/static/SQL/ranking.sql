drop table if exists ranking CASCADE;

create table ranking(
    summonerName varchar(100),
    wins int,
    losses int,
    tier varchar(30),
    leaguePoints int,
    profileIconId int,
    championId1 int,
    championId2 int,
    championId3 int,
    gametype int,
    primary key (summonerName)
);