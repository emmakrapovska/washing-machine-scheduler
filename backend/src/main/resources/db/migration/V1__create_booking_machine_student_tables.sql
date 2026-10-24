CREATE TABLE machines (
                          id           BIGSERIAL PRIMARY KEY,
                          machine_type VARCHAR(20) NOT NULL CHECK (machine_type IN ('PERENJE', 'SUSHENJE')),
                          location     VARCHAR(255),
                          status       VARCHAR(20) NOT NULL CHECK (status IN ('ISPRAVNA', 'NEISPRAVNA')),
                          created_at   TIMESTAMP NOT NULL,
                          updated_at   TIMESTAMP NOT NULL
);

CREATE TABLE students (
                          id          BIGSERIAL PRIMARY KEY,
                          name        VARCHAR(255),
                          room_number VARCHAR(50),
                          email       VARCHAR(255) NOT NULL UNIQUE,
                          created_at  TIMESTAMP NOT NULL,
                          updated_at  TIMESTAMP NOT NULL
);

CREATE TABLE bookings (
                          id          BIGSERIAL PRIMARY KEY,
                          machine_id  BIGINT NOT NULL REFERENCES machines (id),
                          student_id  BIGINT NOT NULL REFERENCES students (id),
                          status      VARCHAR(30) NOT NULL CHECK (
                              status IN ('CREATED', 'APPROVED', 'FINISHED_SUCCESSFULLY', 'CANCELED', 'FAILED')
                              ),
                          start_time  TIMESTAMP NOT NULL,
                          end_time    TIMESTAMP NOT NULL,
                          created_at  TIMESTAMP NOT NULL,
                          updated_at  TIMESTAMP NOT NULL
);

CREATE INDEX idx_bookings_machine_start ON bookings (machine_id, start_time);
CREATE INDEX idx_bookings_student ON bookings (student_id);
CREATE INDEX idx_bookings_status_start ON bookings (status, start_time);