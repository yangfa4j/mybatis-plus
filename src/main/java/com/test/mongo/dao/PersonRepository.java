package com.test.mongo.dao;

import com.test.mongo.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Date 2021/2/23
 * @Author yangfa
 * @Description
 */
@Repository
public interface PersonRepository extends MongoRepository<Person, String> {

}
