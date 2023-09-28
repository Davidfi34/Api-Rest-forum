package com.foro.api.models.response.DTO;

import com.foro.api.models.response.Response;

import java.time.LocalDateTime;

public record DataResponse(Long id, String message, Long id_user_resp, Long id_topic_resp, LocalDateTime data) {

    public DataResponse(Response response){
        this(response.getId(),response.getMessage(),
                response.getUser().getId(),response.getTopic().getId(),response.getData());
    }
}
