package com.jc.curso.springboot.jpa.springbootjpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jc.curso.springboot.jpa.springbootjpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person,Long>{

    List<Person> findByProgrammingLanguaje(String programmingLanguaje);

    @Query("select p from Person p where p.programmingLanguaje=?1 and p.name=?2")
    List<Person> buscarByProgrammingLanguaje(String programmingLanguaje, String name);

    List<Person> findByProgrammingLanguajeAndName(String programmingLanguaje, String name);

    @Query("select p.name, p.programmingLanguaje from Person p")
    List<Object[]> obtenerPersonData();
   
    @Query("select p.name, p.programmingLanguaje from Person p where p.name=?1")
    List<Object[]> obtenerPersonData(String name);   
   
    @Query("select p.name, p.programmingLanguaje from Person p where p.programmingLanguaje=?1 and p.name=?2")
    List<Object[]> obtenerPersonData(String programmingLanguaje, String name);

    @Query("select p.name, p.programmingLanguaje from Person p where p.programmingLanguaje=?1")
    List<Object[]> obtenerPersonDataByProgrammingLanguaje(String programmingLanguaje);
}
