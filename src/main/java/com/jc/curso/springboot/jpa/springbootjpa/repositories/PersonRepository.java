package com.jc.curso.springboot.jpa.springbootjpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jc.curso.springboot.jpa.springbootjpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person,Long>{

}
