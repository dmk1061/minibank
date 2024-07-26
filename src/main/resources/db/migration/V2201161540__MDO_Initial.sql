CREATE  SEQUENCE mbsequence increment 1 start 20;

CREATE  EXTENSION  IF NOT EXISTS "pgcrypto";
CREATE  EXTENSION IF NOT EXISTS "uuid-ossp";


CREATE TABLE USERS (
    id  bigint PRIMARY KEY,
    name    varchar unique,
    password bytea,
    date_of_birth date,
    CONSTRAINT check_min_length check (length(password) >= 8),
    CONSTRAINT check_max_length check (length(password) <= 500)
);

CREATE TABLE ACCOUNT(
    id bigint PRIMARY KEY ,
    user_id bigint,
    initial_balance decimal,
    balance decimal,
    CONSTRAINT fk_user
        FOREIGN KEY(user_id)
            REFERENCES USERS(id)
);
CREATE TABLE EMAIL_DATA (
    id bigint PRIMARY KEY,
    user_id bigint,
    email varchar unique ,
    CONSTRAINT fk_user
        FOREIGN KEY(user_id)
        REFERENCES USERS(id)
);
CREATE TABLE PHONE_DATA (
    user_id bigint,
    id bigint PRIMARY KEY ,
    phone varchar unique ,
    CONSTRAINT fk_user
        FOREIGN KEY(user_id)
            REFERENCES USERS(id)
);
