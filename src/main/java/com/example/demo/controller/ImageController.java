/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Image;
import com.example.demo.model.Tag;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.TagRepository;
import com.example.demo.service.NextIdService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Owner
 */
@RestController
@RequestMapping("/img")
public class ImageController {
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    NextIdService nextIdService;
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Image> logFileUpload(@RequestBody Image image){
        System.out.println("Processing logFileUpload");
        System.out.println(image);
        String[] tags = image.getTagString().split(",");
        List<Tag> tagList = new ArrayList<>();
        for(String tagName : tags){
            Tag tag = tagRepository.findByTagName(tagName);
            if(tag == null){
                tag = new Tag();
                int nextTagId = nextIdService.getNextUserId("TAG");
                System.out.println("Create new tag Id :"+nextTagId);
                tag.setId(String.valueOf(nextTagId));
                tag.setTagName(tagName);
                tag.setCreatorId("1");
                tag = tagRepository.insert(tag);
                
            }
            tagList.add(tag);
        }
        image.setTags(tagList);
        image.setCreatorId("1");
        Date newDate = new Date();
        image.setCreateTimeStamp(newDate);
        image.setLastUpdatedTimeStamp(newDate);
        imageRepository.insert(image);
           
        return new ResponseEntity<Image>(image, HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Image>> findAllImages(){
        List<Image> images = imageRepository.findAll();
        return new ResponseEntity<List<Image>>(images,HttpStatus.OK);
    }
    
    @RequestMapping(path="/last5",method = RequestMethod.GET)
    public ResponseEntity<List<Image>> findLast5(){
        List<Image> images = imageRepository.findFirst5ByOrderByCreateTimeStampDesc();
        return new ResponseEntity<List<Image>>(images,HttpStatus.OK);
    }
    
}
