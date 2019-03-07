package com.terry.mongodbdemo;

import com.terry.mongodbdemo.dto.Subject;
import com.terry.mongodbdemo.dto.User;
import com.terry.mongodbdemo.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbdemoApplicationTests {


    @Autowired
    IUserService userService;

    @Test
    public void testInsert() {

        User user = new User();
        user.setUserName("jujuju");
        user.setAge(18);
        user.setRelationships(new String[]{"father", "mother", "sunzei"});

        Subject subject1 = new Subject().setName("math").setScore(80);

        Subject subject2 = new Subject().setName("english").setScore(80);

        user.setSubjects(Arrays.asList(subject1, subject2));

        userService.addUser(user);

        List<User> users = userService.getAllUser();
        users.forEach((u) -> {
            System.out.println(u);
        });

    }

    @Test
    public void testQuery() {


        System.out.println("------------------ all ----------------");
        System.out.println(userService.getAllUser());


        String name = "terry";
        List<User> list = userService.getUserByName(name);


        list.forEach(user -> {
            System.out.println("------------" + user.getUserName() + "-----------");
            System.out.println(user);
        });


    }

    @Test
    public void testUpdate() {
        List<User> users = userService.getUserByName("ryunn");
        users.forEach(u -> {
            Subject subject1 = new Subject().setName("math").setScore(100);
            Subject subject2 = new Subject().setName("english").setScore(100);
            u.setAge(17).setSubjects(Arrays.asList(subject1, subject2));
            userService.updateUser(u);

        });

    }

    @Test
    public void testDelete() {
        userService.deleteByUserName("jujuju");
        System.out.println(userService.getAllUser());

    }

}
