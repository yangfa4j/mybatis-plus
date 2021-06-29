package com.test.mongo.controller;

import com.test.mongo.entity.Person;
import com.test.mongo.service.MongoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mongo")
public class BaseController {

    @Autowired
    private MongoDbService mongoDbService;

    @PostMapping("/save")
    public String saveObj(@RequestBody Person person) {
        return mongoDbService.saveObj(person);
    }

    @GetMapping("/findAll")
    public List<Person> findAll() {
        return mongoDbService.findAll();
    }

    @GetMapping("/findLike")
    public List<Person> findLike() {
        return mongoDbService.findLike();
    }

}