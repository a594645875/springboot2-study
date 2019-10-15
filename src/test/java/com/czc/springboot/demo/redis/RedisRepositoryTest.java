package com.czc.springboot.demo.redis;

import com.czc.springboot.demo.dao.PersonRepository;
import com.czc.springboot.demo.model.Address;
import com.czc.springboot.demo.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    public void test(){

        Person rand = new Person("zimug", "汉神");
        rand.setAddress(new Address("杭州", "中国"));
        personRepository.save(rand);
        Optional<Person> op = personRepository.findById(rand.getId());
        Person p2 = op.get();
        personRepository.count();
        personRepository.delete(rand);

    }

}