package com.czc.springboot.demo.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("people")
public class Person implements Serializable {

  private static final long serialVersionUID = -8985545025228238754L;

  String id;
  String firstname;
  String lastname;
  Address address;

  public Person(String firstname, String lastname) {
    this.firstname = firstname;
    this.lastname = lastname;
  }
}