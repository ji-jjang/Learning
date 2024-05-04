INSERT INTO COURSES(ID, NAME, CATEGORY, RATING, DESCRIPTION)
VALUES (1, 'Rapid Spring Boot Application Development', 'Spring', 4, 'Spring Boot gives all the power of the Spring Framework with');

INSERT INTO USERS(username, password, enabled)
values ('user', 'user', true);

INSERT INTO USERS(username, password, enabled)
values ('admin', 'admin', true);


INSERT INTO AUTHORITIES(username, authority) values ('user', 'USER');
INSERT INTO AUTHORITIES(username, authority) values ('admin', 'ADMIN');

INSERT INTO CT_USERS(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, EMAIL, VERIFIED, LOCKED, ACC_CRED_EXPIRED) VALUES('John', 'Socket', 'jsocket', 'password', 'jsocket@example.com', TRUE, FALSE, FALSE);
INSERT INTO CT_USERS(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, EMAIL, VERIFIED, LOCKED, ACC_CRED_EXPIRED) VALUES('Steve', 'Smith', 'smith', 'password', 'smith@example.com', FALSE, FALSE, FALSE);