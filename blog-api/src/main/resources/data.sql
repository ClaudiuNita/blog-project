INSERT INTO USERS (id, username, email, password) VALUES (0, 'admin', '', 'admin');
INSERT INTO USERS (id, username, email, password) VALUES (default, 'claudiu', 'claudiu@tremend.com', 'ccc');
INSERT INTO USERS (id, username, email, password) VALUES (default, 'maria', 'maria@tremend.com', 'ccc');
INSERT INTO USERS (id, username, email, password) VALUES (default, 'dragos', 'dragos@tremend.com', 'ccc');
INSERT INTO USER_DETAILS (id, username, email, password, age, gender, country, user_id) VALUES (default, 'claudiu', 'claudiu@tremend.com', 'ccc', 19, 'male', 'Romania', 1);
INSERT INTO USER_DETAILS (id, username, email, password, age, gender, country, user_id) VALUES (default, 'maria', 'maria@tremend.com', 'ccc', 19, 'female', 'Romania', 2);
INSERT INTO USER_DETAILS (id, username, email, password, age, gender, country, user_id) VALUES (default, 'dragos', 'dragos@tremend.com', 'ccc', 19, 'male', 'Germania', 3);
INSERT INTO POST (id, title, content, localDateTime, author_id) VALUES (default, 'titlee', 'contentt', '2018-11-15T08:22:12', 1);
INSERT INTO POST (id, title, content, localDateTime, author_id) VALUES (default, 'title', 'content', '2017-11-15T08:22:12', 1);
INSERT INTO POST (id, title, content, localDateTime, author_id) VALUES (default, 'title', 'maria post', '2019-11-15T08:22:12', 2);
INSERT INTO POST_COMMENT (id, comment, localDateTime, post_id, user_id) VALUES (default, 'comment1', '2018-11-15T08:22:12', 1, 1);
INSERT INTO POST_COMMENT (id, comment, localDateTime, post_id, user_id) VALUES (default, 'comment2', '2019-11-15T08:22:12', 1, 1);
INSERT INTO POST_COMMENT (id, comment, localDateTime, post_id, user_id) VALUES (default, 'comment3', '2018-11-15T08:22:12', 3, 2);