package com.foro.api.services;

import com.foro.api.infra.errors.IntegrityValidation;
import com.foro.api.models.response.DTO.DataResponse;
import com.foro.api.models.response.DTO.ResponseRegistration;
import com.foro.api.models.response.Response;
import com.foro.api.models.topic.Topic;
import com.foro.api.models.user.User;
import com.foro.api.repository.ResponseRepository;
import com.foro.api.repository.TopicRepository;
import com.foro.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SaveResponseRepository {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private ResponseRepository responseRepository;


    public DataResponse saveResponse(ResponseRegistration responseRegistration){

        if (!userRepository.findById(responseRegistration.id_user_resp()).isPresent() ){
            throw new IntegrityValidation("user not found");
        }

        if (responseRegistration.id_topic_resp() != null && !topicRepository.existsById(responseRegistration.id_topic_resp()) ){
            throw new IntegrityValidation("topic not found");
        }

        User user = userRepository.findById(responseRegistration.id_user_resp()).get();
        Topic topic = topicRepository.findById(responseRegistration.id_topic_resp()).get();

        Response response = new Response(null,responseRegistration.message(),user,topic, LocalDateTime.now(),true);

        responseRepository.save(response);

        return new DataResponse(response);

    }


}
