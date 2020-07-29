package com.khrapkov.firstSpringBootApp.repositories;

import com.khrapkov.firstSpringBootApp.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

}
