--liquibase formatted sql

--changeset author:1
create table staff (
    id int primary key,
    emp_surname varchar(255),
    emp_name varchar(255),
    emp_surname varchar(255),
    emp_patronymic varchar(255),
    emp_hash varchar(255)
);
--rollback drop table staff;
