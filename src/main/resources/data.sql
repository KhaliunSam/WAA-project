insert into Role values (1, 'ROLE_SELLER')
insert into Role values (2, 'ROLE_BUYER')
insert into Role values (3, 'ROLE_ADMIN')

insert into user (email, first_name, last_name, password, point, role_role_id, status, user_id) values ('khaliun@gmail.com', 'Khaliun', 'Samdanjamts', '$2a$10$JA3U4.h6ZXQOsxtSXndcq.QZ1dClsRP.I.hJw47IrpgRIhaZEgjwO', null, 1, 0, 101)
insert into user (email, first_name, last_name, password, point, role_role_id, status, user_id) values ('ochi@gmail.com', 'Ochi', 'Samand', '$2a$10$JA3U4.h6ZXQOsxtSXndcq.QZ1dClsRP.I.hJw47IrpgRIhaZEgjwO', null, 1, 0, 102)
insert into user (email, first_name, last_name, password, point, role_role_id, status, user_id) values ('seller@gmail.com', 'Barrack', 'Obama', '$2a$10$JA3U4.h6ZXQOsxtSXndcq.QZ1dClsRP.I.hJw47IrpgRIhaZEgjwO', null, 1, 1, 103)

insert into user (email, first_name, last_name, password, point, role_role_id, status, user_id) values ('buyer@gmail.com', 'Donald', 'Trump', '$2a$10$Zn/2KFw8h6eNAZDeBQQxIeAnx0YXP0nsoCcMQcQZoXduyARNFyt2u', 0, 2, 1, 104)

insert into user (email, first_name, last_name, password, point, role_role_id, status, user_id) values ('admin@gmail.com', 'George', 'Bush', '$2a$10$ZHKZ5Wx6Nb5Z.D667M0iu..FH3SYQ0GTMH56EmDbx7Swu2w3qvdji', null, 3, 1, 100)