--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS role;
CREATE SEQUENCE role_seq;

CREATE TABLE role (
  role_id int NOT NULL DEFAULT NEXTVAL ('role_seq'),
  role varchar(255) DEFAULT NULL,
  PRIMARY KEY (role_id)
)  ;

ALTER SEQUENCE role_seq RESTART WITH 2;


--
-- Table structure for table `user`
--
DROP TABLE IF EXISTS users;
CREATE SEQUENCE user_seq;

CREATE TABLE users (
  user_id int NOT NULL DEFAULT NEXTVAL ('user_seq'),
  active int DEFAULT NULL,
  email varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY (user_id)
)  ;

ALTER SEQUENCE user_seq RESTART WITH 4;


--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role (
  user_id int NOT NULL,
  role_id int NOT NULL,
  PRIMARY KEY (user_id,role_id)
 ,
  CONSTRAINT FK859n2jvi8ivhui0rl0esws6o FOREIGN KEY (user_id) REFERENCES users (user_id),
  CONSTRAINT FKa68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES role (role_id)
) ;

CREATE INDEX FKa68196081fvovjhkek5m97n3y ON user_role (role_id);
