CREATE TABLE time_slots
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    start_time  DATETIME NOT NULL,
    end_time    DATETIME NOT NULL,
    is_reserved BOOLEAN DEFAULT FALSE,
    price       INT      NOT NULL
);

CREATE TABLE reservations
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    start_time DATETIME NOT NULL,
    end_time   DATETIME NOT NULL,
    price      INT      NOT NULL
);

CREATE TABLE time_slots_versioning
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    start_time  DATETIME NOT NULL,
    end_time    DATETIME NOT NULL,
    is_reserved BOOLEAN DEFAULT FALSE,
    price       INT      NOT NULL,
    version     INT      NOT NULL
);
