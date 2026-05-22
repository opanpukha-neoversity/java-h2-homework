INSERT INTO worker (name, birthday, level, salary) VALUES
('Ivan Petrenko', DATE '1990-03-12', 'Middle', 3500),
('Olena Shevchenko', DATE '1985-07-23', 'Senior', 7000),
('Andrii Kovalenko', DATE '1998-11-02', 'Junior', 1200),
('Maria Bondarenko', DATE '1992-01-16', 'Middle', 4000),
('Petro Melnyk', DATE '1979-05-30', 'Senior', 8500),
('Dmytro Tkachenko', DATE '2001-09-10', 'Trainee', 700),
('Sofia Kravchenko', DATE '1996-12-05', 'Junior', 1500),
('Maksym Moroz', DATE '1988-04-18', 'Senior', 9000),
('Anna Lysenko', DATE '1994-06-25', 'Middle', 4200),
('Bohdan Savchuk', DATE '2003-02-14', 'Trainee', 600);

INSERT INTO client (name) VALUES
('Tech Corp'),
('Soft Solutions'),
('Future Systems'),
('Data Planet'),
('Green Energy');

INSERT INTO project (client_id, name, start_date, finish_date) VALUES
(1, 'CRM Upgrade', DATE '2020-01-01', DATE '2020-07-01'),
(1, 'Mobile App', DATE '2021-02-15', DATE '2021-08-15'),
(1, 'Data Migration', DATE '2022-03-01', DATE '2022-06-01'),
(2, 'Online Store', DATE '2020-05-01', DATE '2021-05-01'),
(2, 'Payment Gateway', DATE '2021-06-01', DATE '2021-12-01'),
(3, 'Analytics Platform', DATE '2019-01-01', DATE '2021-01-01'),
(3, 'Internal Portal', DATE '2022-01-01', DATE '2022-04-01'),
(4, 'BI Reports', DATE '2021-03-01', DATE '2022-09-01'),
(4, 'Warehouse System', DATE '2022-10-01', DATE '2023-04-01'),
(5, 'Solar Monitoring', DATE '2020-07-01', DATE '2021-01-01');

INSERT INTO project_worker (project_id, worker_id) VALUES
(1, 1), (1, 2), (1, 3),
(2, 2), (2, 4), (2, 7),
(3, 1), (3, 6),
(4, 4), (4, 5), (4, 8),
(5, 3), (5, 9),
(6, 2), (6, 5), (6, 8), (6, 9),
(7, 6), (7, 10),
(8, 1), (8, 4), (8, 8),
(9, 5), (9, 7), (9, 9),
(10, 2), (10, 10);
