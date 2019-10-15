package com.czc.springboot.demo.dao;

import com.czc.springboot.demo.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, String> {
 // 继承CrudRepository，获取基本的CRUD操作
}