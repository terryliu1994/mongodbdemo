package com.terry.mongodbdemo.service.impl;

import com.terry.mongodbdemo.dto.User;
import com.terry.mongodbdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<User> getAllUser() {
        List<User> users = mongoTemplate.findAll(User.class);
        return users;
    }

    @Override
    public List<User> getUserByName(String username) {
        if (StringUtils.isEmpty(username)) {
            throw new RuntimeException("username 不能为空");
        }
        Criteria criteria = new Criteria();
        criteria.and("userName").is(username);

        Query query = new Query(criteria);

        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }

    @Override
    public User addUser(User user) {
        user = mongoTemplate.save(user);
        return user;
    }

    @Override
    public void deleteByUserName(String username) {
        Criteria criteria = new Criteria();
        criteria.and("userName").is(username);
        Query query = new Query(criteria);
        mongoTemplate.remove(query, User.class);

    }

    @Override
    public void updateUser(User user) {
        Criteria whereClause = new Criteria();
        whereClause.and("userName").is(user.getUserName());
        Query query = new Query(whereClause);

        Update update = new Update();
        update.set("userName", user.getUserName());
        update.set("age", user.getAge());
        update.set("relationships", user.getRelationships());
        update.set("subjects", user.getSubjects());

        mongoTemplate.updateMulti(query, update, User.class);

    }

    @Override
    public void transactionUpdate(User user) {
       /* MongoCollection<Document> userCollection = mongoClient.getDatabase("test").getCollection("user_collection");

        try(ClientSession session = mongoClient.startSession()){

            session.startTransaction();

            Map<String,Object> userMap = new HashMap<>();
            userMap.put("userName",user.getUserName());
            userMap.put("age",user.getAge());
            userMap.put("relationships",user.getRelationships());

            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("$set",userMap);

            Bson data = new BasicDBObject();
            ((BasicDBObject) data).putAll(dataMap);

            userCollection.updateOne(session,
                    Filters.eq("userName", user.getUserName()),
                    data);

        *//*userCollection.updateMany(session,
                Filters.eq("userName",user.getUserName()),
                Updates.set("age",user.getAge()));*//*

            System.out.println(1/0);

            session.commitTransaction();
        }catch (Exception e){
            e.printStackTrace();
        }*/

    }
}
