package com.test.mongo.Test;

import com.test.mongo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

/**
 * @Date 2021/2/23
 * @Author yangfa
 * @Description
 */
public class Test {

    @Autowired
    private MongoTemplate template;

    public static void main(String[] args) {
        Test test = new Test();
        test.findAll();
    }

    public void findAll() {
        List<Person> all = template.findAll(Person.class);
        System.out.println("all = " + all);
    }
}
