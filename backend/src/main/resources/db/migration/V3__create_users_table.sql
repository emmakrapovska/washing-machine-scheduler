CREATE TABLE users (
                       id          BIGSERIAL PRIMARY KEY,
                       username    VARCHAR(255) NOT NULL UNIQUE,
                       email       VARCHAR(255) NOT NULL UNIQUE,
                       password    VARCHAR(255) NOT NULL,
                       role        VARCHAR(20) NOT NULL CHECK (role IN ('STUDENT', 'ADMIN')),
                       student_id  BIGINT UNIQUE REFERENCES students (id),
                       created_at  TIMESTAMP NOT NULL,
                       updated_at  TIMESTAMP NOT NULL
);

CREATE INDEX idx_users_username ON users (username);