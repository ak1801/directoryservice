CREATE DATABASE DIRECTORY;

USE DIRECTORY;

CREATE TABLE BIRD(
    id INT NOT NULL auto_increment, 
    name VARCHAR(50) NOT NULL,
    family VARCHAR(50) NOT NULL,
    continents VARCHAR(200) NOT NULL,
    added_on DATE NOT NULL,
    visible BOOL NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
);
