CREATE TABLE players
(
    id   INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(100)
);
CREATE TABLE matches
(
    id      INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    player1 INTEGER,
    player2 INTEGER,
    winner  INTEGER
);
INSERT INTO players (name)
VALUES ('Artem'),
       ('Vasya'),
       ('Matvey')