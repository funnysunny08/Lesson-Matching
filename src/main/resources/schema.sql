CREATE TABLE tutor
(
    tutor_id   bigint PRIMARY KEY auto_increment,
    name VARCHAR(100) NOT NULL,
    university VARCHAR(100) NOT NULL,
    major     VARCHAR(100) NOT NULL,
    gender  VARCHAR(20) NOT NULL,
    age     int NOT NULL,
    created_at   datetime(6) NOT NULL
);

CREATE TABLE student
(
    student_id   bigint PRIMARY KEY auto_increment,
    name VARCHAR(100) NOT NULL,
    gender  VARCHAR(20) NOT NULL,
    age     int NOT NULL,
    residential_area VARCHAR(100) NOT NULL,
    created_at   datetime(6) NOT NULL
);

CREATE TABLE lecture
(
    lecture_id   bigint PRIMARY KEY auto_increment,
    tutor_id   bigint,
    subject VARCHAR(100) NOT NULL,
    region  VARCHAR(100) NOT NULL,
    price   int NOT NULL,
    number_of_week  int NOT NULL,
    created_at   datetime(6) NOT NULL,
    CONSTRAINT fk_lecture_to_tutor FOREIGN KEY (tutor_id) REFERENCES tutor (tutor_id) ON DELETE CASCADE
);

CREATE TABLE matching_lecture
(
    matching_lecture_id   bigint PRIMARY KEY auto_increment,
    lecture_id   bigint,
    student_id   bigint,
    status VARCHAR(100) NOT NULL,
    created_at   datetime(6) NOT NULL,
    CONSTRAINT fk_matching_lecture_to_lecture FOREIGN KEY (lecture_id) REFERENCES lecture (lecture_id) ON DELETE CASCADE,
    CONSTRAINT fk_matching_lecture_to_student FOREIGN KEY (student_id) REFERENCES student (student_id) ON DELETE CASCADE
);