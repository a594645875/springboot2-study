package com.czc.springboot.demo.redis;

import com.czc.springboot.demo.model.Address;
import com.czc.springboot.demo.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis模板封装类：
 * redisTemplate.opsForValue();//操作字符串
 * redisTemplate.opsForHash();//操作 hash
 * redisTemplate.opsForList();//操作 list
 * redisTemplate.opsForSet();//操作 set
 * redisTemplate.opsForZSet();//操作有序 set
 *
 * 两种注入方式：
 * // 注入RedisTemplate，更通用
 * //@Autowired
 * private RedisTemplate<String, String> template;
 *
 * // 如：注入RedisTemplate的子类ListOperations，只能操作List
 * //@Resource
 * private ListOperations<String, String> listOps;
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConfigTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String,Object> valueOperations;

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, Object> hashOperations;

    @Resource(name = "redisTemplate")
    private ListOperations<String, Object> listOperations;

    @Resource(name = "redisTemplate")
    private SetOperations<String, Object> setOperations;

    @Resource(name = "redisTemplate")
    private ZSetOperations<String, Object> zSetOperations;

    @Test
    public void testValueObj() throws Exception{
        Person person = new Person("boke","byrant");
        person.setAddress(new Address("南京","中国"));
        //ValueOperations<String,Object> operations = redisTemplate.opsForValue();
        valueOperations.set("player:1",person,20, TimeUnit.SECONDS); //10秒之后数据消失

        Person getBack = (Person)valueOperations.get("player:1");
        System.out.println(getBack);
    }

    @Test
    public void testSetOperation() throws Exception{
        Person person = new Person("boke","byrant");
        Person person2 = new Person("curry","stephen");
        setOperations.add("playerset",person,person2);
        Set<Object> result = setOperations.members("playerset");
        System.out.println(result);
    }

    @Test
    public void HashOperations() throws Exception{
        Person person = new Person("boke","byrant");
        hashOperations.put("hash:player","firstname",person.getFirstname());
        hashOperations.put("hash:player","lastname",person.getLastname());
        hashOperations.put("hash:player","address",person.getAddress());
        System.out.println(hashOperations.get("hash:player","firstname"));
    }

    @Test
    public void  ListOperations() throws Exception{

        listOperations.leftPush("list:player",new Person("boke","byrant"));
        listOperations.leftPush("list:player",new Person("Jordan","Mikel"));
        listOperations.leftPush("list:player",new Person("curry","stephen"));

        System.out.println(listOperations.leftPop("list:player"));
    }

    /**
     * 将对象一次性hash入库
     */
    @Resource(name="redisTemplate")
    private HashOperations<String, String, Object> jacksonHashOperations;
    private HashMapper<Object, String, Object> jackson2HashMapper = new Jackson2HashMapper(false);
    @Test
    public void testHashPutAll(){

        Person person = new Person("boke","byrant");
        person.setId("1");
        person.setAddress(new Address("南京","中国"));
        //将对象以hash的形式放入数据库
        Map<String,Object> mappedHash = jackson2HashMapper.toHash(person);
        jacksonHashOperations.putAll("player" + person.getId(), mappedHash);

        //将对象从数据库取出来
        Map<String,Object> loadedHash = jacksonHashOperations.entries("player" + person.getId());
        Object map = jackson2HashMapper.fromHash(loadedHash);
        Person getback = new ObjectMapper().convertValue(map,Person.class);
        Assert.assertEquals(person.getFirstname(),getback.getFirstname());
    }
}