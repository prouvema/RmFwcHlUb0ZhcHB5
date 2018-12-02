drop table if exists oauth_access_token;
drop table if exists oauth_refresh_token;
create table oauth_access_token (token_id VARCHAR(256) NOT NULL, token BLOB NOT NULL, authentication_id VARCHAR(256) PRIMARY KEY NOT NULL, user_name VARCHAR(256) NOT NULL, client_id VARCHAR(256) NOT NULL, authentication blob NOT NULL, refresh_token VARCHAR(256) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create table oauth_refresh_token (token_id varchar(255) NOT NULL, token blob NOT NULL, authentication blob NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into profile (id, create_time, update_time, create_user, update_user, version_number, firstname, lastname, mobile_number, phone_number) values (1, NOW(), NOW(), 'no context', 'no context', 1, 'admin', 'admin', '0611223344', '0455667788');
insert into user (email, password, create_time, update_time, create_user, update_user, version_number, account_non_expired, account_non_locked, credentials_non_expired, enabled, profile_id) values ('admin@localhost', '$argon2i$v=19$m=65536,t=1,p=1$aJvXrXPpqJ6xSFA/dqkeJQ$FQhTHMeGTC+H4ty+3P8javkrqEuLJTcIGvzJHNvRUvk', NOW(), NOW(), 'no context', 'no context', 1, true, true, true, true, 1);
