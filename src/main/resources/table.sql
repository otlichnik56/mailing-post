CREATE TABLE mailings
(
    id                  SERIAL PRIMARY KEY UNIQUE,
    type                TEXT,
    recipient_index     INTEGER REFERENCES posts(index),
    recipient_address   TEXT,
    recipient_name      TEXT
);


CREATE TABLE posts
(
    index       SERIAL PRIMARY KEY UNIQUE,
    name        TEXT,
    address     TEXT
);


CREATE TABLE tracks
(
    id          SERIAL PRIMARY KEY UNIQUE,
    mailing_id  INTEGER REFERENCES mailings(id),
    post_index  INTEGER REFERENCES posts(index),
    date_time   TIMESTAMP,
    status      TEXT
);