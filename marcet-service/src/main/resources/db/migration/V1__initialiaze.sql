create table product
(
    id    bigserial primary key,
    title varchar(255),
    cost  int
);

create table users
(
    id       bigserial primary key,
    username varchar(80)  not null unique,
    password varchar(150) not null,
    email    varchar(80)  not null,
);

create table roles
(
    id   bigserial primary key,
    name varchar(50) not null unique
);
create table users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

create table orders
(
    id           bigserial primary key,
    id_user      bigint not null references users (id),
    id_product   bigint not null references product (id),
    order_number bigint,
    sum_cost     int,
    adres        varchar(30),
);

-- insert into orders (id_user, id_product, order_number, sum_cost, adres) values(1, 1, 1,  200, 'Shtuchki')

-- select ORDERS.ORDER_NUMBER  ,USERS .USERNAME , PRODUCT .TITLE , PRODUCT.COST , ORDERS.SUM_COST  ,ORDERS .ADRES from orders
-- inner join PRODUCT on ORDERS .ID_PRODUCT = PRODUCT .ID
-- inner join USERS on ORDERS .ID_USER  = USERS .ID
-- where USERS .USERNAME = 'user1'

insert into users (username, password, email)
values ('user1', '$2a$12$KQvocrh3mFd3Kq3YhF3bVebVAQkHTukjXKY.5z8tuDatxKQqqIac2', 'user1@gmail.com'),
       ('user2', '$2a$12$KQvocrh3mFd3Kq3YhF3bVebVAQkHTukjXKY.5z8tuDatxKQqqIac2', 'user2@gmail.com');

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');



insert into product (title, cost)
values ('Картошка', 10),
       ('Капуста', 20),
       ('Макароны', 30),
       ('Огурцы', 40),
       ('Помидроры', 50);

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);