CREATE TABLE responses(
    id BIGINT NOT NULL AUTO_INCREMENT,
    message VARCHAR(200) NOT NULL,
    id_user_resp BIGINT NOT NULL,
    id_topic_resp BIGINT NOT NULL,
    data DATETIME NOT NULL,

    PRIMARY KEY(id),

        CONSTRAINT fk_users_id_user_resp FOREIGN KEY (id_user_resp) REFERENCES users(id),
        CONSTRAINT fk_topics_id_topic_resp FOREIGN KEY (id_topic_resp) REFERENCES topics(id)
);