package com.foro.api.repository;

import com.foro.api.models.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

   // Page<Topic> findAllTopic(Pageable pageable);//ONLY ACTIVE : TRUE
}
