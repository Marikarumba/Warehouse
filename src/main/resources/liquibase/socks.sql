--liquibase formatted sql

--changeset burseva:1
CREATE TABLE socks(
                      id Serial,
                      color Text,
                      cotton_part INTEGER,
                      quantity INTEGER
)