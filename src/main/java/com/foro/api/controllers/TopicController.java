package com.foro.api.controllers;

import com.foro.api.models.topic.DTO.TopicDataResponse;
import com.foro.api.models.topic.DTO.TopicRegistration;
import com.foro.api.services.SaveTopicRepository;
import jakarta.transaction.Transactional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private SaveTopicRepository saveTopicRepository;


    /**
     *  topic registration method
     *  @author David Figuerero
     *
     * @param topicRegistration
     * @param uriComponentsBuilder
     * @return TopiDataResponse
     */

    @PostMapping
    @Transactional
    public ResponseEntity<TopicDataResponse> topicRegister (@RequestBody @Valid TopicRegistration topicRegistration,
                                                           UriComponentsBuilder uriComponentsBuilder){
        TopicDataResponse topic = saveTopicRepository.saveTopic(topicRegistration);

        URI url = uriComponentsBuilder.path("/topic/{id}").buildAndExpand(topic.id()).toUri();
        return ResponseEntity.created(url).body(topic);
    }




}
