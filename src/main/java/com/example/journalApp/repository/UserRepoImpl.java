package com.example.journalApp.repository;

import com.example.journalApp.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//using this class gives us better control over querying the db, and lets us write
//difficult queries with ease
@Repository
public class UserRepoImpl {
    @Autowired
    MongoTemplate mongoTemplate;

    public List<Users> getUsersForSA(){
        Query query = new Query();
//        query.addCriteria(Criteria.where("username").is("megha"));
//        List<Users> users = mongoTemplate.find(query, Users.class); //example of ORM, we are providing a class but it will
//        automatically deserialize it into a table

//        query.addCriteria(Criteria.where("age").gte(18));
//        query to find users where the age is greater than or equal to 18. lte, lt, gt, etc are also valid
//        to add multiple cons also, we use mutliple lines.. like in the case if we execute both lines
//        then the new query will find users with username as megha and age>=18
//        query.addCriteria(Criteria.where("email").exists(true).ne(null).ne(""));
        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9+_.-]+@(.+)$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));

//        Criteria c1 = new Criteria();
        //this is how we can use or operator
//        query.addCriteria(c1.orOperator(
//                Criteria.where("name").is("Megha"),
//                Criteria.where("name").is("Sheetal")
//        ));
//
        List<Users> users = mongoTemplate.find(query, Users.class);
        return users;
    }
}
