CREATE TABLE IF NOT EXISTS players(

    id        BIGSERIAL NOT NULL,
    player_id BIGSERIAL NOT NULL PRIMARY KEY,
    name      varchar(100)

);