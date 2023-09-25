drop table if exists users CASCADE;

create table users(
                       user_id int auto_increment,
                       email varchar(100),
                       password varchar(300),
                       activated boolean,
                       primary key (user_id)
);