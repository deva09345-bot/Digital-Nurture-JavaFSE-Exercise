USE ormlearn;

CREATE TABLE IF NOT EXISTS department (
    dp_id   INT NOT NULL AUTO_INCREMENT,
    dp_name VARCHAR(30),
    PRIMARY KEY (dp_id)
);

CREATE TABLE IF NOT EXISTS skill (
    sk_id   INT NOT NULL AUTO_INCREMENT,
    sk_name VARCHAR(30),
    PRIMARY KEY (sk_id)
);

CREATE TABLE IF NOT EXISTS employee (
    em_id            INT NOT NULL AUTO_INCREMENT,
    em_name          VARCHAR(30),
    em_salary        DOUBLE,
    em_permanent     TINYINT(1),
    em_date_of_birth DATE,
    em_dp_id         INT,
    PRIMARY KEY (em_id),
    FOREIGN KEY (em_dp_id) REFERENCES department(dp_id)
);

CREATE TABLE IF NOT EXISTS employee_skill (
    es_em_id INT NOT NULL,
    es_sk_id INT NOT NULL,
    PRIMARY KEY (es_em_id, es_sk_id),
    FOREIGN KEY (es_em_id) REFERENCES employee(em_id),
    FOREIGN KEY (es_sk_id) REFERENCES skill(sk_id)
);

INSERT INTO department (dp_name) VALUES ('Technology');
INSERT INTO department (dp_name) VALUES ('Finance');
INSERT INTO department (dp_name) VALUES ('HR');

INSERT INTO skill (sk_name) VALUES ('Java');
INSERT INTO skill (sk_name) VALUES ('Spring Boot');
INSERT INTO skill (sk_name) VALUES ('Hibernate');
INSERT INTO skill (sk_name) VALUES ('MySQL');
INSERT INTO skill (sk_name) VALUES ('Angular');

INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES ('Deva',    75000, 1, '1998-04-15', 1);
INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES ('Ravi',    60000, 1, '1995-08-20', 2);
INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES ('Priya',   55000, 0, '1997-01-10', 3);
INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES ('Kumar',   80000, 1, '1990-06-25', 1);
INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES ('Sneha',   50000, 0, '1999-11-30', 2);

INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (1, 1);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (1, 2);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (1, 3);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (2, 4);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (3, 5);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (4, 1);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (4, 2);

-- Quiz schema
CREATE TABLE IF NOT EXISTS user (
    us_id   INT NOT NULL AUTO_INCREMENT,
    us_name VARCHAR(50),
    PRIMARY KEY (us_id)
);

CREATE TABLE IF NOT EXISTS question (
    qu_id    INT NOT NULL AUTO_INCREMENT,
    qu_text  VARCHAR(255),
    qu_score DOUBLE,
    PRIMARY KEY (qu_id)
);

CREATE TABLE IF NOT EXISTS options (
    op_id      INT NOT NULL AUTO_INCREMENT,
    op_text    VARCHAR(100),
    op_correct TINYINT(1),
    op_qu_id   INT,
    PRIMARY KEY (op_id),
    FOREIGN KEY (op_qu_id) REFERENCES question(qu_id)
);

CREATE TABLE IF NOT EXISTS attempt (
    at_id    INT NOT NULL AUTO_INCREMENT,
    at_date  DATE,
    at_us_id INT,
    PRIMARY KEY (at_id),
    FOREIGN KEY (at_us_id) REFERENCES user(us_id)
);

CREATE TABLE IF NOT EXISTS attempt_question (
    aq_id    INT NOT NULL AUTO_INCREMENT,
    aq_at_id INT,
    aq_qu_id INT,
    PRIMARY KEY (aq_id),
    FOREIGN KEY (aq_at_id) REFERENCES attempt(at_id),
    FOREIGN KEY (aq_qu_id) REFERENCES question(qu_id)
);

CREATE TABLE IF NOT EXISTS attempt_option (
    ao_id      INT NOT NULL AUTO_INCREMENT,
    ao_selected TINYINT(1),
    ao_aq_id   INT,
    ao_op_id   INT,
    PRIMARY KEY (ao_id),
    FOREIGN KEY (ao_aq_id) REFERENCES attempt_question(aq_id),
    FOREIGN KEY (ao_op_id) REFERENCES options(op_id)
);

-- Quiz sample data
INSERT INTO user (us_name) VALUES ('John');
INSERT INTO user (us_name) VALUES ('Mary');

INSERT INTO question (qu_text, qu_score) VALUES ('What is the extension of the hyper text markup language file?', 1.0);
INSERT INTO question (qu_text, qu_score) VALUES ('What is the maximum level of heading tag can be used in a HTML page?', 1.0);
INSERT INTO question (qu_text, qu_score) VALUES ('The HTML document itself begins with <html> and ends </html>. State True or False', 1.0);
INSERT INTO question (qu_text, qu_score) VALUES ('Choose the right option to store text value in a variable', 0.5);

INSERT INTO options (op_text, op_correct, op_qu_id) VALUES ('.xhtm', 0, 1);
INSERT INTO options (op_text, op_correct, op_qu_id) VALUES ('.ht',   0, 1);
INSERT INTO options (op_text, op_correct, op_qu_id) VALUES ('.html', 1, 1);
INSERT INTO options (op_text, op_correct, op_qu_id) VALUES ('.htmx', 0, 1);

INSERT INTO options (op_text, op_correct, op_qu_id) VALUES ('5', 0, 2);
INSERT INTO options (op_text, op_correct, op_qu_id) VALUES ('3', 0, 2);
INSERT INTO options (op_text, op_correct, op_qu_id) VALUES ('4', 0, 2);
INSERT INTO options (op_text, op_correct, op_qu_id) VALUES ('6', 1, 2);

INSERT INTO options (op_text, op_correct, op_qu_id) VALUES ('false', 0, 3);
INSERT INTO options (op_text, op_correct, op_qu_id) VALUES ('true',  1, 3);

INSERT INTO options (op_text, op_correct, op_qu_id) VALUES ('''John''', 1, 4);
INSERT INTO options (op_text, op_correct, op_qu_id) VALUES ('John',    0, 4);
INSERT INTO options (op_text, op_correct, op_qu_id) VALUES ('"John"',  0, 4);
INSERT INTO options (op_text, op_correct, op_qu_id) VALUES ('/John/',  0, 4);

INSERT INTO attempt (at_date, at_us_id) VALUES ('2019-10-01', 1);

INSERT INTO attempt_question (aq_at_id, aq_qu_id) VALUES (1, 1);
INSERT INTO attempt_question (aq_at_id, aq_qu_id) VALUES (1, 2);
INSERT INTO attempt_question (aq_at_id, aq_qu_id) VALUES (1, 3);
INSERT INTO attempt_question (aq_at_id, aq_qu_id) VALUES (1, 4);

INSERT INTO attempt_option (ao_selected, ao_aq_id, ao_op_id) VALUES (0, 1, 1);
INSERT INTO attempt_option (ao_selected, ao_aq_id, ao_op_id) VALUES (0, 1, 2);
INSERT INTO attempt_option (ao_selected, ao_aq_id, ao_op_id) VALUES (1, 1, 3);
INSERT INTO attempt_option (ao_selected, ao_aq_id, ao_op_id) VALUES (0, 1, 4);

INSERT INTO attempt_option (ao_selected, ao_aq_id, ao_op_id) VALUES (0, 2, 5);
INSERT INTO attempt_option (ao_selected, ao_aq_id, ao_op_id) VALUES (1, 2, 6);
INSERT INTO attempt_option (ao_selected, ao_aq_id, ao_op_id) VALUES (0, 2, 7);
INSERT INTO attempt_option (ao_selected, ao_aq_id, ao_op_id) VALUES (0, 2, 8);

INSERT INTO attempt_option (ao_selected, ao_aq_id, ao_op_id) VALUES (0, 3, 9);
INSERT INTO attempt_option (ao_selected, ao_aq_id, ao_op_id) VALUES (1, 3, 10);

INSERT INTO attempt_option (ao_selected, ao_aq_id, ao_op_id) VALUES (1, 4, 11);
INSERT INTO attempt_option (ao_selected, ao_aq_id, ao_op_id) VALUES (0, 4, 12);
INSERT INTO attempt_option (ao_selected, ao_aq_id, ao_op_id) VALUES (0, 4, 13);
INSERT INTO attempt_option (ao_selected, ao_aq_id, ao_op_id) VALUES (0, 4, 14);
