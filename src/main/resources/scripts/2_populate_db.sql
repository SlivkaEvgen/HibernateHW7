USE homework;

INSERT INTO companies(id, name, city)
VALUES (1, 'GoIT', 'Kiyv'),
       (2, 'StarsIT', 'Kyiv'),
       (3, 'UpIT', 'Lvov');

INSERT INTO customers(id, name, city, budget)
VALUES (1, 'RichMan1', 'Kyiv', 100000),
       (2, 'RichMan2', 'ZP', 200000),
       (3, 'RichMan3', 'Lvov', 300000);

INSERT INTO developers(id, name, age, gender, email, salary, company_id)
VALUES (1, 'Вася', 35, 'Male', 'abc@com.ua', 1000, 3),
       (2, 'Коля', 25, 'Male', 'abc1@com.ua', 2000, 2),
       (3, 'Костя', 26, 'Male', 'abc2@com.ua', 3000, 1),
       (4, 'Витя', 27, 'Male', 'abc3@com.ua', 1000, 1),
       (5, 'Юра', 28, 'Male', 'abc4@com.ua', 2000, 1),
       (6, 'Ира', 29, 'Female', 'abc5@com.ua', 3000, 2),
       (7, 'Дима', 30, 'Male', 'abc6@com.ua', 3000, 3),
       (8, 'Катя', 32, 'Female', 'abc7@com.ua', 2000, 2),
       (9, 'Антон', 33, 'Male', 'abc8@com.ua', 1000, 3),
       (10, 'Лена', 36, 'Female', 'abc9@com.ua', 3000, 3),
       (11, 'Маша', 40, 'Female', 'abc10@com.ua', 2000, 2),
       (12, 'Миша', 42, 'Male', 'abc11@com.ua', 1000, 2);

INSERT INTO skills(id, activity, level)
VALUES (1, 'Java', 'Junior'),
       (2, 'Java', 'Middle'),
       (3, 'Java', 'Senior'),
       (4, 'JS', 'Junior'),
       (5, 'JS', 'Middle'),
       (6, 'JS', 'Senior'),
       (7, 'C+', 'Junior'),
       (8, 'C+', 'Middle'),
       (9, 'C+', 'Senior'),
       (10, 'C#', 'Junior'),
       (11, 'C#', 'Middle'),
       (12, 'C#', 'Senior');

INSERT INTO developers_skills(developer_id, skill_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6),
       (7, 7),
       (8, 8),
       (9, 9),
       (10, 10),
       (11, 11),
       (12, 12),
       (1, 5),
       (2, 8),
       (3, 12);

INSERT INTO projects(id, name, cost, company_id, customer_id)
VALUES (1, 'Bot', 300000, 1, 1),
       (2, 'Car', 350000, 2, 2),
       (3, 'School', 400000, 3, 3);

INSERT INTO developers_projects(developer_id, project_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 1),
       (5, 2),
       (6, 3),
       (7, 3),
       (8, 2),
       (9, 1),
       (10, 3),
       (11, 2),
       (12, 1);
