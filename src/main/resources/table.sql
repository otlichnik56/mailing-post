CREATE TABLE mailing
(
    id                  SERIAL PRIMARY KEY UNIQUE,
    type                TEXT,
    recipient_index     INTEGER,
    recipient_address   TEXT,
    recipient_name      TEXT
);

CREATE TABLE post
(
    index       SERIAL PRIMARY KEY UNIQUE,
    name        TEXT,
    address     TEXT
);

CREATE TABLE track
(
    id          SERIAL PRIMARY KEY UNIQUE,
    mailing_id  INTEGER,
    post_index  INTEGER,
    date_time   TIMESTAMP,
    status      TEXT
);