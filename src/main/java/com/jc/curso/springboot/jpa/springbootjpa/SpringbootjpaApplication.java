package com.jc.curso.springboot.jpa.springbootjpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jc.curso.springboot.jpa.springbootjpa.entities.Person;
import com.jc.curso.springboot.jpa.springbootjpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootjpaApplication implements CommandLineRunner{

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootjpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		findOne();
		
	}

	public void findOne(){

		// Person person = null;
		// Optional<Person> optionalPerson = repository.findById(2L);
		// if (optionalPerson.isPresent()) {
		// 	person = optionalPerson.get();
		// }
		// System.out.println(person);
		repository.findByNameContaining("se").ifPresent(System.out::println);

	}

	public void list(){

		//List<Person> persons = (List<Person>) repository.findAll();
		//List<Person> persons = (List<Person>) repository.buscarByProgrammingLanguaje("Java","Andres");
		List<Person> persons = (List<Person>) repository.findByProgrammingLanguajeAndName("Java","Andres");
		

		persons.stream().forEach(person -> System.out.println(person));

		List<Object[]> personsValues = repository.obtenerPersonData();

		personsValues.stream()
			.forEach(person ->{
				System.out.println(person[0] + " es experto en " + person[1]);
		});

	}

}
