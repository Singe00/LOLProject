insert into users (USER_ID, email, password, ACTIVATED)
values (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 1);


insert into user_authority (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_USER');
insert into user_authority (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_ADMIN');