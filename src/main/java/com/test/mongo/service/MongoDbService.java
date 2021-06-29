package com.test.mongo.service;

import com.test.mongo.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MongoDbService {

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("yangfa");

        System.out.println(strings.get(0));

        System.out.println(strings.subList(1, strings.size()));
    }
    private static final Logger logger = LoggerFactory.getLogger(MongoDbService.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存对象
     *
     * @param person
     * @return
     */
    public String saveObj(Person person) {
        logger.info("--------------------->[MongoDB save start]");
        mongoTemplate.save(person);
        return "添加成功";
    }


    /**
     * 查询所有
     *
     * @return
     */
    public List<Person> findAll() {
        logger.info("--------------------->[MongoDB find start]");
        return mongoTemplate.findAll(Person.class, "yangfa");
    }

    /**
     * 模糊搜索
     *
     * @return
     */
    public List<Person> findLike() {
        logger.info("--------------------->[MongoDB find start]");
        return mongoTemplate.find(new Query(Criteria.where("name").is("wangwu")), Person.class, "yangfa");
    }

}