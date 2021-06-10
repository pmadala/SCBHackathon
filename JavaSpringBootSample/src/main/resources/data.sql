
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS account_balance;
DROP TABLE IF EXISTS authority;
DROP TABLE IF EXISTS deposit_log;
DROP TABLE IF EXISTS model;
DROP TABLE IF EXISTS report;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS user_authority;

CREATE TABLE user (
  id VARCHAR(250) PRIMARY KEY, 
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  date_created NUMERIC,
  date_modified NUMERIC
);
create table user_authority(
 id NUMERIC PRIMARY KEY,
 user_id NUMERIC ,
 authority_id NUMERIC
);
CREATE TABLE authority (
   id NUMERIC PRIMARY KEY,
   name VARCHAR(250) NOT NULL
);
CREATE TABLE report (
  id VARCHAR(250) PRIMARY KEY, 
  start NUMERIC NOT NULL, 
  end  NUMERIC NOT NULL, 
  new_accounts  NUMERIC, 
  min_balance_accounts  NUMERIC, 
  max_deposit_in_account_count  NUMERIC,
  date_created NUMERIC,
  date_modified NUMERIC
);
CREATE TABLE deposit_log (
  id VARCHAR(250) PRIMARY KEY,
  deposit VARCHAR(250) NOT NULL,
  account_id VARCHAR(250) NOT NULL,
  date_created NUMERIC,
  date_modified NUMERIC
);
CREATE TABLE account (
	id VARCHAR(250) PRIMARY KEY,
	name VARCHAR(250),
	type VARCHAR(250),
	country VARCHAR(250),
	currency VARCHAR(250),
	customer VARCHAR(250),
	fingerprint VARCHAR(250),
	last4 VARCHAR(250),
	status VARCHAR(250),
	date_created NUMERIC,
	date_modified NUMERIC
);
CREATE TABLE account_balance (
  id VARCHAR(250) PRIMARY KEY,
  balance VARCHAR(250) NOT NULL,
  last4 VARCHAR(250) NOT NULL,
  account VARCHAR(250) NOT NULL,
  date_created NUMERIC,
  date_modified NUMERIC
);




INSERT INTO account VALUES ('id1', 'name1', 'type1', 'country1', 'currency1', 'customer1', 'fingerprint1', 'last41', 'status1', 1622912277860, 1622912277860);
INSERT INTO account VALUES ('id2', 'name2', 'type2', 'country2', 'currency2', 'customer2', 'fingerprint2', 'last42', 'status2', 1622912277860, 1622912277860);
INSERT INTO account VALUES ('id3', 'name3', 'type3', 'country3', 'currency3', 'customer3', 'fingerprint3', 'last43', 'status3', 1622912277860, 1622912277860);
INSERT INTO account VALUES ('id4', 'name4', 'type4', 'country4', 'currency4', 'customer4', 'fingerprint4', 'last44', 'status4', 1622912277860, 1622912277860);

INSERT INTO authority VALUES (1, 'ROLE_ADMIN');
INSERT INTO authority VALUES (2, 'ROLE_USER');

INSERT INTO user VALUES (1, 'user1', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 1622912256325, 1622912256325);
INSERT INTO user VALUES (2, 'user2', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 1622912277860, 1622912277860);

INSERT INTO user_authority VALUES(1, 1, 1);
INSERT INTO user_authority VALUES(2, 2, 2);
