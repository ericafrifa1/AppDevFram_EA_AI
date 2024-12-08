INSERT INTO users (username, password, role, unlocked) VALUES
                                                           ('admin', '$2a$10$abcdEFGHijklMNOPqrstUVWXyz1234567890abcdEFGHijklMNOPqr', 'ADMIN', true),
                                                           ('user1', '$2a$10$abcdEFGHijklMNOPqrstUVWXyz1234567890abcdEFGHijklMNOPqr', 'USER', true);

INSERT INTO households (eircode, number_of_occupants, max_number_of_occupants, owner_occupied) VALUES
                                                                                                   ('A12BCD', 2, 5, true),
                                                                                                   ('E34FGH', 0, 3, false);

INSERT INTO pets (name, animal_type, breed, age, household_id) VALUES
                                                                   ('Buddy', 'Dog', 'Golden Retriever', 3, 1),
                                                                   ('Whiskers', 'Cat', 'Siamese', 2, 1);
