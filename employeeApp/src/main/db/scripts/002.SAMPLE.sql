--liquibase formatted sql

--changeset author:2
insert into staff (emp_id, emp_fullname, emp_hash) values (1, 'Chris', 'adfhq235t5jkngadklsfioqen');
--rollback drop table staff;
