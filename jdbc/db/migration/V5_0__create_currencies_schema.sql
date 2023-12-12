CREATE TABLE IF NOT EXISTS currencies (

    id        BIGSERIAL NOT NULL,
    player_id BIGSERIAL NOT NULL,
    name      VARCHAR(100),
    count     INTEGER,

    FOREIGN KEY (player_id) references players(player_id) ON DELETE CASCADE
);