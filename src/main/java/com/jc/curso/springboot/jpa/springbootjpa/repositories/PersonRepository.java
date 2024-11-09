package com.jc.curso.springboot.jpa.springbootjpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jc.curso.springboot.jpa.springbootjpa.DTO.PersonDto;
import com.jc.curso.springboot.jpa.springbootjpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person,Long>{

    @Query("select new com.jc.curso.springboot.jpa.springbootjpa.DTO.PersonDto(p.name, p.lastname) from Person p")
    List<PersonDto> findAllPersonDto();

    @Query("select new Person(p.name, p.lastname) from Person p")
    List<Person> findAllObjectPersonPersonalized();

    @Query("select p.name from Person p where p.id=?1")
    String getNameById(Long id);

    @Query("select p.id from Person p where p.id=?1")
    Long getIdById(Long id);

    @Query("select concat(p.name, ' ' , p.lastname) as fullname from Person p where p.id=?1")
    String getFullNameById(Long id);

    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneName(String name);

    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    Optional<Person> findByNameContaining(String name);

    List<Person> findByProgrammingLanguaje(String programmingLanguaje);

    @Query("select p from Person p where p.programmingLanguaje=?1 and p.name=?2")
    List<Person> buscarByProgrammingLanguaje(String programmingLanguaje, String name);

    List<Person> findByProgrammingLanguajeAndName(String programmingLanguaje, String name);

    @Query("select p.id, p.name, p.lastname, p.programmingLanguaje from Person p where p.id=?1")
    Object obtenerPersonDataById(Long id);

    @Query("select p, p.programmingLanguaje from Person p")
    List<Object[]> findAllMixPerson();

    @Query("select p.id, p.name, p.lastname, p.programmingLanguaje from Person p")
    List<Object[]> obtenerPersonDataList();

    @Query("select p.name, p.programmingLanguaje from Person p")
    List<Object[]> obtenerPersonData();
   
    @Query("select p.name, p.programmingLanguaje from Person p where p.name=?1")
    List<Object[]> obtenerPersonData(String name);   
   
    @Query("select p.name, p.programmingLanguaje from Person p where p.programmingLanguaje=?1 and p.name=?2")
    List<Object[]> obtenerPersonData(String programmingLanguaje, String name);

    @Query("select p.name, p.programmingLanguaje from Person p where p.programmingLanguaje=?1")
    List<Object[]> obtenerPersonDataByProgrammingLanguaje(String programmingLanguaje);
}
