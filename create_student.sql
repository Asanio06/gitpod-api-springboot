CREATE TABLE IF NOT EXISTS student(  
    id BIGSERIAL NOT NULL primary key,
    firstname varchar(255),
    lastname varchar(255),
    email varchar(255),
    birthdate DATE
);
