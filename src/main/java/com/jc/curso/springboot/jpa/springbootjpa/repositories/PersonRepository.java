package com.jc.curso.springboot.jpa.springbootjpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jc.curso.springboot.jpa.springbootjpa.DTO.PersonDto;
import com.jc.curso.springboot.jpa.springbootjpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person,Long>{

    @Query("select count(p) from Person p")
    Long totalPerson();

    @Query("select min(p.id) from Person p")
    Long minId();

    @Query("select max(p.id) from Person p")
    Long maxId();

    List<Person> findAllByOrderByNameAscLastnameDesc();

    @Query("select p from Person p order by p.name, p.lastname desc")
    List<Person> getAllOrdered();

    List<Person> findByIdBetweenOrderByNameAsc(Long id1, Long id2);

    List<Person> findByNameBetweenOrderByNameDescLastnameDesc(String name1, String name2);
    
    @Query("select p from Person p where p.id between ?1 and ?2 order by p.name desc")
    List<Person> findAllBetweenId(Long id1, Long id2);

    @Query("select p from Person p where p.name between ?1 and ?2 order by p.name asc , p.lastname asc")
    List<Person> findAllBetweenName(String c1, String c2);

    @Query("select p.id, upper(p.name), lower(p.lastname), upper(p.programmingLanguaje) from Person p")
    List<Object[]> findAllPersonDataListCase();

    @Query("select upper(p.name ||' ' || p.lastname)  from Person p")
    List<String> findAllFullNameConcatUpper();

    @Query("select  lower(concat(p.name, ' ' , p.lastname))  from Person p")
    List<String> findAllFullNameConcatLower();

    //@Query("select concat(p.name, ' ' , p.lastname)  from Person p")
    @Query("select p.name ||' ' || p.lastname  from Person p")
    List<String> findAllFullNameConcat();

    @Query("select count(distinct(p.programmingLanguaje)) from Person p")
    Long findAllprogrammingLanguageDistinctCount();

    @Query("select distinct(p.programmingLanguaje) from Person p")
    List<String> findAllprogrammingLanguajeDistinct();

    @Query("select p.name from Person p")
    List<String> findAllNames();

    @Query("select distinct(p.name) from Person p")
    List<String> findAllNamesDistinct();

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
