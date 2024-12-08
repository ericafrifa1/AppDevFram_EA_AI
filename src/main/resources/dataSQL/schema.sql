CREATE TABLE pets (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      animal_type VARCHAR(255) NOT NULL,
                      breed VARCHAR(255) NOT NULL,
                      age INT NOT NULL,
                      household_id BIGINT,
                      FOREIGN KEY (household_id) REFERENCES households (id) ON DELETE CASCADE
);

CREATE TABLE households (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            eircode VARCHAR(255) NOT NULL,
                            number_of_occupants INT NOT NULL,
                            max_number_of_occupants INT NOT NULL,
                            owner_occupied BOOLEAN NOT NULL
);

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL,
                       unlocked BOOLEAN NOT NULL
);
