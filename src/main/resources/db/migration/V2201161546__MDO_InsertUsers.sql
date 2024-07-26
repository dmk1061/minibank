INSERT INTO USERS(id, name, password, date_of_birth)
values (1, 'IVAN IVANOV', pgp_sym_encrypt('12345678', '${secret-key}'), '01.05.1993');
INSERT INTO USERS(id, name, password, date_of_birth)
values (2, 'PETR PETROV', pgp_sym_encrypt('12345678', '${secret-key}'), '01.05.1995');
INSERT INTO USERS(id, name, password, date_of_birth)
values (3, 'SIDOR SIDOROV', pgp_sym_encrypt('12345678', '${secret-key}'), '01.05.1999');

