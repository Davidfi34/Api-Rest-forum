package com.foro.api.controllers;

import com.foro.api.models.topic.DTO.GetTopics;
import com.foro.api.models.topic.DTO.TopicDataResponse;
import com.foro.api.models.topic.DTO.TopicRegistration;
import com.foro.api.repository.TopicRepository;
import com.foro.api.services.SaveTopicRepository;
import jakarta.transaction.Transactional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private SaveTopicRepository saveTopicRepository;
    @Autowired
    private TopicRepository topicRepository;


    /**
     *  topic registration method
     *  @author David Figuerero
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

    /**
     * method to obtain all the topics
     * @author David Figuerero
     * @param pages
     * @return Page<topic>
     */

    @GetMapping
    public ResponseEntity<Page<GetTopics>> getAllTopic(@PageableDefault(size = 10) Pageable pages){
        return ResponseEntity.ok(topicRepository.findAll(pages).map(GetTopics::new));
    }



}
