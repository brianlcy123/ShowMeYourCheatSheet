/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Tag;
import com.example.demo.model.User;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author Owner
 */
@RepositoryRestResource
public interface TagRepository extends MongoRepository<Tag, String>{
    Tag insert(Tag tag);
    
    Tag findByTagName(String tagName);
    
}
