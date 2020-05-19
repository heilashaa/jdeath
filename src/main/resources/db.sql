CREATE DATABASE IF NOT EXISTS jdeath DEFAULT CHARACTER SET = 'utf8' DEFAULT COLLATE 'utf8_general_ci';

CREATE TABLE user (
                        userid int NOT NULL AUTO_INCREMENT,
                        name varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                        sureName varchar(45) DEFAULT NULL,
                        PRIMARY KEY (userid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE account (
                           accountid int NOT NULL AUTO_INCREMENT,
                           account int DEFAULT NULL,
                           userid int DEFAULT NULL,
                           PRIMARY KEY (accountid),
                           KEY account_FK (userid),
                           CONSTRAINT account_FK FOREIGN KEY (userid) REFERENCES user (userid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO jdeath.user (name , sureName) VALUES
('Agatha', 'MacDonald'),
('Allan', 'Butler'),
('Bruce', 'Robertson'),
('Carl', 'Murphy'),
('Deborah', 'Waite'),
('Elvin', 'Thomas'),
('Joanna', 'Kingsman'),
('Louis', 'Sheldon'),
('Norman', 'Young'),
('William', 'Jacobson');

INSERT INTO jdeath.account (account , userid) VALUES
('110', '1'),
('120', '2'),
('130', '3'),
('140', '4'),
('150', '6'),
('160', '6'),
('170', '7'),
('180', '8'),
('190', '9'),
('200', '10');
