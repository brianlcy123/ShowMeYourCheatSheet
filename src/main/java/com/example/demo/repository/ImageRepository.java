/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Image;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author Owner
 */
@RepositoryRestResource
public interface ImageRepository extends MongoRepository<Image, String>{
    Image insert(Image image);
    List<Image> findFirst5ByOrderByCreateTimeStampDesc();
}
