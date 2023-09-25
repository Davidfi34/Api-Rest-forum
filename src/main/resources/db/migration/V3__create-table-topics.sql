CREATE TABLE topics(
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    message VARCHAR(200) NOT NULL,
    data DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL,
    id_user BIGINT NOT NULL,
    id_course BIGINT NOT NULL,

    PRIMARY KEY(id),

        CONSTRAINT fk_users_id_user FOREIGN KEY (id_user) REFERENCES users(id),
        CONSTRAINT fk_courses_id_course FOREIGN KEY (id_course) REFERENCES courses(id)



);