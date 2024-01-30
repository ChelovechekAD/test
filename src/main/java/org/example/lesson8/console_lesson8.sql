CREATE DATABASE IF NOT EXISTS lesson8; /* IF NOT EXISTS - important fragment.
                                          Because, of that database is present, sql scripts finished with error.
                                          */


/* Alternative:

   drop database if exists lesson8;
   create database lesson8;

   //recreating database every run

   */

USE lesson8;

/* If you selected the database, you don't need to use database name in structures like this */

CREATE TABLE /*lesson8.*/ houses
(
    id         int primary key auto_increment,
    size       DECIMAL,
    color      VARCHAR(50),
    room_count INT
);

CREATE TABLE /*lesson8.DOORS*/ doors /* Don't create anythings using uppercase letters. It can cause problems. */
(
    id   int primary key auto_increment,
    size DECIMAL(32, 2),
    type varchar(50)
);