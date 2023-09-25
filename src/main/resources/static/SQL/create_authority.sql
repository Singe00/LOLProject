drop table if exists authority CASCADE;

create table authority(
                    authority_name varchar(300)
);

insert into authority (AUTHORITY_NAME) values ('ROLE_USER');
insert into authority (AUTHORITY_NAME) values ('ROLE_ADMIN');