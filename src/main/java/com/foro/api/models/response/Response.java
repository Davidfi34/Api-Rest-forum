package com.foro.api.models.response;

import com.foro.api.models.response.DTO.UpdateDataResponse;
import com.foro.api.models.topic.DTO.UpdateDataTopic;
import com.foro.api.models.topic.Topic;
import com.foro.api.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "responses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_resp")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_topic_resp")
    private Topic topic;

    private LocalDateTime data;
    private Boolean active;

    public void updateResponse(UpdateDataResponse request) {
        if (request.message() != null) this.message = request.message();
        this.data = LocalDateTime.now();
    }

    public void deactivateResponse() {
        this.active = false;
    }
}
