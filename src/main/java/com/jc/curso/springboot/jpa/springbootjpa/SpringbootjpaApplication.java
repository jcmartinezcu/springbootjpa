package com.jc.curso.springboot.jpa.springbootjpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

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

		delete2();
		
	}

	@Transactional
	public void delete2(){

		repository.findAll().forEach(System.out::println);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id a eliminar:");
		Long id = scanner.nextLong();
		repository.deleteById(id);

		repository.findAll().forEach(System.out::println);

		scanner.close();

	}

	@Transactional
	public void delete(){

		repository.findAll().forEach(System.out::println);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id a eliminar:");
		Long id = scanner.nextLong();
		repository.deleteById(id);

		repository.findAll().forEach(System.out::println);

		scanner.close();

	}

	@Transactional
	public void update(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona:");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);

		optionalPerson.ifPresent(person -> {
			System.out.println(person);
			System.out.println("Ingrese el lenguaje de programación:");
			String programmingLanguage = scanner.next();
			person.setProgrammingLanguaje(programmingLanguage);
			Person personDb= repository.save(person);
			System.out.println(personDb);

		});

		scanner.close();
	}

	@Transactional
	public void create(){


		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el nombre:");
		String name = scanner.next();
		System.out.println("Ingrese el apellido:");
		String lastName = scanner.next();
		System.out.println("Ingrese el lenguaje de programacion:");
		String programmingLanguage = scanner.next();
		scanner.close();

		Person person = new Person(null, name, lastName, programmingLanguage);

		Person personNew = repository.save(person);
		System.out.println(personNew);

		repository.findById(personNew.getId()).ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void findOne(){

		// Person person = null;
		// Optional<Person> optionalPerson = repository.findById(2L);
		// if (optionalPerson.isPresent()) {
		// 	person = optionalPerson.get();
		// }
		// System.out.println(person);
		repository.findByNameContaining("se").ifPresent(System.out::println);

	}

	@Transactional(readOnly = true)
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
