CREATE TABLE COURSES
(
    id int NOT NULL,
    name varchar(100) NOT NULL,
    category varchar(20) NOT NULL ,
    rating int NOT NULL,
    description varchar(1000) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE USERS(
    username varchar(50) not null primary key,
    password varchar(500) not null,
    enabled boolean not null
);

CREATE TABLE AUTHORITIES(
    username varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username) references users(username)
);

create unique index ix_auth_username on AUTHORITIES (username, authority);

create table ct_users(
     ID	BIGINT	NOT NULL,
     EMAIL	VARCHAR(255)	NOT NULL,
     FIRST_NAME	VARCHAR(255) NOT NULL,
     LAST_NAME	VARCHAR(255) NOT NULL,
     PASSWORD	VARCHAR(255) NOT NULL,
     USERNAME	VARCHAR(255) NOT NULL,
     VERIFIED	BOOLEAN NOT NULL,
     LOCKED BOOLEAN NOT NULL,
     ACC_CRED_EXPIRED BOOLEAN NOT NULL,
     PRIMARY KEY (ID)
);