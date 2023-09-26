package com.foro.api.controllers;

import com.foro.api.models.topic.DTO.GetTopics;
import com.foro.api.models.topic.DTO.TopicDataResponse;
import com.foro.api.models.topic.DTO.TopicRegistration;
import com.foro.api.models.topic.DTO.UpdateDataTopic;
import com.foro.api.models.topic.Topic;
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
     * @return Page<GetTopics>
     */

    @GetMapping
    public ResponseEntity<Page<GetTopics>> getAllTopic(@PageableDefault(size = 10) Pageable pages){
        return ResponseEntity.ok(topicRepository.findByActiveTrue(pages).map(GetTopics::new));
    }


    /**
     * method get by ID topics
     * @author David Figuerero
     * @param id
     * @return GetTopics
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<GetTopics> getByIdTopic(@PathVariable Long id){
        Topic topic = topicRepository.getReferenceById(id);
        GetTopics getTopics= new GetTopics(topic);
        return ResponseEntity.ok(getTopics);
    }

    /**
     * topic update method
     * @author David Figuerero
     * @param updateDataTopic
     * @return TopicDataResponse
     */
    @PutMapping
    @Transactional
    public ResponseEntity<TopicDataResponse> updateTopic(@RequestBody @Valid UpdateDataTopic updateDataTopic) {
        Topic topic = topicRepository.getReferenceById(updateDataTopic.id());
        topic.updateTopic(updateDataTopic);
        TopicDataResponse topicDataResponse = new TopicDataResponse(topic);
        return ResponseEntity.ok(topicDataResponse);
    }


    /**
     * method of logical deletion of topic,
     * modification of attribute active=false
     * @author David Figuerero
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping(path ="/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        topic.deactivateTopic();
        return ResponseEntity.noContent().build();
    }



}
