create table users(
  id        bigserial primary key,
  username varchar(80) not null unique,
  password varchar(150) not null,
  email    varchar(80) not null,
);

create table roles(
    id      bigserial primary key,
    name    varchar(50) not null unique
);
create table users_roles(
  user_id bigint not null references  users(id),
  role_id bigint not null references  roles(id),
  primary key (user_id, role_id)
);

insert into users (username, password, email)
values
('user1', '$2a$12$KQvocrh3mFd3Kq3YhF3bVebVAQkHTukjXKY.5z8tuDatxKQqqIac2', 'user1@gmail.com'),
('user2', '$2a$12$KQvocrh3mFd3Kq3YhF3bVebVAQkHTukjXKY.5z8tuDatxKQqqIac2', 'user2@gmail.com');

insert into roles (name)
values
('ROLE_USER'),
('ROLE_ADMIN');

insert into users_roles (user_id, role_id)
values
(1,1),
(2,2);