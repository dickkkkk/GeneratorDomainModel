CREATE TABLE IF NOT EXISTS indicators(

    player_id    BIGSERIAL NOT NULL,
    hp           INTEGER,
    mana         INTEGER,
    power        INTEGER,
    dexterity    INTEGER,
    intelligence INTEGER,

    FOREIGN KEY (player_id) references players(player_id) ON DELETE CASCADE
);
