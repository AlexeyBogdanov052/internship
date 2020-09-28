--liquibase formatted sql

--changeset author:1
create table staff (
    emp_id int primary key,
    emp_fullname varchar(255),
    emp_hash varchar(255)
);
--rollback drop table staff;