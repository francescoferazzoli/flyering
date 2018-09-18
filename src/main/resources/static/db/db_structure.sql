--
-- Table structure for table `role`
--
DROP 
  TABLE IF EXISTS role;
CREATE SEQUENCE role_seq;
CREATE TABLE role (
  role_id int NOT NULL DEFAULT NEXTVAL ('role_seq'), 
  role varchar(255) DEFAULT NULL, 
  PRIMARY KEY (role_id)
);
ALTER SEQUENCE role_seq RESTART WITH 2;
--
-- Table structure for table `user`
--
DROP 
  TABLE IF EXISTS users;
CREATE SEQUENCE user_seq;
CREATE TABLE users (
  user_id int NOT NULL DEFAULT NEXTVAL ('user_seq'), 
  active int DEFAULT NULL, 
  email varchar(255) NOT NULL, 
  last_name varchar(255) NOT NULL, 
  name varchar(255) NOT NULL, 
  password varchar(255) NOT NULL, 
  PRIMARY KEY (user_id)
);
ALTER SEQUENCE user_seq RESTART WITH 4;
--
-- Table structure for table `user_role`
--
DROP 
  TABLE IF EXISTS user_role;
CREATE TABLE user_role (
  user_id int NOT NULL, 
  role_id int NOT NULL, 
  PRIMARY KEY (user_id, role_id), 
  CONSTRAINT FK859n2jvi8ivhui0rl0esws6o FOREIGN KEY (user_id) REFERENCES users (user_id), 
  CONSTRAINT FKa68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES role (role_id)
);
CREATE INDEX FKa68196081fvovjhkek5m97n3y ON user_role (role_id);
--
-- Table structure for table `italy_regions` (https://github.com/MatteoHenryChinaski/Comuni-Italiani-2018-Sql-Json-excel)
--
DROP 
  TABLE IF EXISTS italy_regions;
CREATE TABLE italy_regions (
  id_regione smallint NOT NULL, 
  regione varchar(50) DEFAULT NULL, 
  superficie double precision DEFAULT NULL, 
  num_residenti int DEFAULT NULL, 
  num_comuni int DEFAULT NULL, 
  num_provincie int DEFAULT NULL, 
  presidente varchar(45) DEFAULT NULL, 
  cod_istat varchar(2) DEFAULT NULL, 
  cod_fiscale varchar(11) DEFAULT NULL, 
  piva varchar(11) DEFAULT NULL, 
  pec varchar(100) DEFAULT NULL, 
  sito varchar(100) DEFAULT NULL, 
  sede varchar(255) DEFAULT NULL, 
  PRIMARY KEY (id_regione)
);
--
-- Table structure for table `italy_provincies` (https://github.com/MatteoHenryChinaski/Comuni-Italiani-2018-Sql-Json-excel)
--
DROP 
  TABLE IF EXISTS italy_provincies;
CREATE TABLE italy_provincies (
  sigla varchar(2) NOT NULL, 
  provincia varchar(255) DEFAULT NULL, 
  superficie double precision DEFAULT NULL, 
  residenti int DEFAULT NULL, 
  num_comuni int DEFAULT NULL, 
  id_regione smallint DEFAULT NULL, 
  PRIMARY KEY (sigla)
);
--
-- Table structure for table `italy_cities` (https://github.com/MatteoHenryChinaski/Comuni-Italiani-2018-Sql-Json-excel)
--
DROP 
  TABLE IF EXISTS italy_cities;
CREATE TABLE italy_cities (
  istat int NOT NULL, 
  comune varchar(255) DEFAULT NULL, 
  regione varchar(50) DEFAULT NULL, 
  provincia varchar(2) DEFAULT NULL, 
  prefisso varchar(7) DEFAULT NULL, 
  cod_fisco varchar(10) DEFAULT NULL, 
  superficie double precision DEFAULT NULL, 
  num_residenti int DEFAULT NULL, 
  PRIMARY KEY (istat)
);
--
-- Table structure for table `italy_cap` (https://github.com/MatteoHenryChinaski/Comuni-Italiani-2018-Sql-Json-excel)
--
DROP 
  TABLE IF EXISTS italy_cap;
CREATE TABLE italy_cap (
  istat int NOT NULL, 
  cap char(11) DEFAULT NULL, 
  PRIMARY KEY (istat)
);
