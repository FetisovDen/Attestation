--liquibase formatted sql


--changeset dfetisov:1
--precondition-sql-check expectedResult:0 SELECT count(*) FROM pg_tables WHERE tablename='socks'
--onFail=MARK_RAN
CREATE TABLE socks
(
    id          BIGINT PRIMARY KEY generated always as identity,
    color       TEXT    NOT NULL,
    cotton_part INTEGER check ( cotton_part>=0 ) check ( cotton_part <=100 ),
    quantity    INTEGER NOT NULL check ( quantity>0 )
);




