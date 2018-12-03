drop table if exists oauth_access_token;
drop table if exists oauth_refresh_token;
create table oauth_access_token (token_id VARCHAR(256) NOT NULL, token BLOB NOT NULL, authentication_id VARCHAR(256) PRIMARY KEY NOT NULL, user_name VARCHAR(256) NOT NULL, client_id VARCHAR(256) NOT NULL, authentication blob NOT NULL, refresh_token VARCHAR(256) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create table oauth_refresh_token (token_id varchar(255) NOT NULL, token blob NOT NULL, authentication blob NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into application_role (id, create_time, update_time, create_user, update_user, version_number, name) values (1, NOW(), NOW(), 'no context', 'no context', 1, 'ADMIN');

insert into user (id, email, password, create_time, update_time, create_user, update_user, version_number, account_non_expired, account_non_locked, credentials_non_expired, enabled) values (1, 'admin@localhost', '$argon2i$v=19$m=65536,t=1,p=1$aJvXrXPpqJ6xSFA/dqkeJQ$FQhTHMeGTC+H4ty+3P8javkrqEuLJTcIGvzJHNvRUvk', NOW(), NOW(), 'no context', 'no context', 1, true, true, true, true);
insert into profile (id, create_time, update_time, create_user, update_user, version_number, user_id, firstname, lastname, mobile_number, phone_number) values (1, NOW(), NOW(), 'no context', 'no context', 1, 1, 'admin', 'admin', '0611223344', '0455667788');
insert into user_application_role_join (user_id, application_role_id) values (1, 1);

insert into user (id, email, password, create_time, update_time, create_user, update_user, version_number, account_non_expired, account_non_locked, credentials_non_expired, enabled) values (2, 'mben@localhost', '$argon2i$v=19$m=65536,t=1,p=1$aJvXrXPpqJ6xSFA/dqkeJQ$FQhTHMeGTC+H4ty+3P8javkrqEuLJTcIGvzJHNvRUvk', NOW(), NOW(), 'no context', 'no context', 1, true, true, true, true);
insert into profile (id, create_time, update_time, create_user, update_user, version_number, user_id, firstname, lastname, mobile_number, phone_number) values (2, NOW(), NOW(), 'no context', 'no context', 1, 2, 'Moussouf', 'Benhawouid', '0611223344', '0455667788');