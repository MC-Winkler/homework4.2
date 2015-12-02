CREATE DATABASE IF NOT EXISTS mvc;

USE mvc;

CREATE TABLE checkedout
(
	id        INT		PRIMARY KEY 	AUTO_INCREMENT,
  title     varchar(70),
	firstname	varchar(30),
  lastname	varchar(30),
  email     varchar(50),
  duedate		date
);