package com.jc.curso.springboot.jpa.springbootjpa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.jc.curso.springboot.jpa.springbootjpa.DTO.PersonDto;
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

		update();
		
	}

	@Transactional(readOnly = true)
	public void whereIn(){
		System.out.println("================= Consulta wher in =================");
		List<Person> persons = repository.getPersonByIds(Arrays.asList(1L,2L,5L,7L));
		persons.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void subQueries(){

		System.out.println("================= Consultas por el nombre mas corto y su largo =================");
		List<Object[]> registers = repository.getShorterName();
		registers.forEach(reg ->{
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("name=" + name + ", length="+length);
		});

		System.out.println("================= Consultas para obtener el ultimo registro de persona=================");
		Optional<Person> optionaPerson = repository.getLastRegistration();
		optionaPerson.ifPresent(System.out::println);

	}

	@Transactional(readOnly = true)
	public void queriesFunctionAggregation(){

			
		System.out.println("================= Consultas con el total de los registros de la tabla persona =================");
		Long count = repository.getTotalPerson();
		System.out.println(count);

		System.out.println("================= Consultas con el valor minimo del id =================");
		Long min = repository.getMinId();
		System.out.println(min);

		System.out.println("================= Consultas con el valor maximo del id =================");
		Long max = repository.getMaxId();
		System.out.println(max);

		System.out.println("================= Consultas por el nombre y su largo =================");
		List<Object[]> regs = repository.getPersonNameLength();
		regs.forEach(reg ->{
			String name = (String) reg[0];
			Integer length =(Integer) reg[1];
			System.out.println("name="+name+" , length="+length);

		});

		System.out.println("================= Consultas con el nombre mas corto =================");
		Integer minLengthName = repository.getMinLengthName();
		System.out.println(minLengthName);

		System.out.println("================= Consultas con el nombre mas largo =================");
		Integer maxLengthName = repository.getMaxLengthName();
		System.out.println(maxLengthName);

		System.out.println("================= Consultas resumen de funciones de agregacion min, max, sum, avg, count =================");
		Object[] resumeReg = (Object[]) repository.getResumeAggregationFunction();
		System.out.println("min="+ resumeReg[0] +", max="+resumeReg[1]+
		", sum="+ resumeReg[2]+", avg="+ resumeReg[3]+", count="+resumeReg[4]);

	}

	@Transactional(readOnly = true)
	public void personalizedQueriesBetween(){

		System.out.println("================= Consultas por rangos id =================");
		List<Person> persons = repository.findByIdBetweenOrderByNameAsc(2L,5L);
		persons.forEach(System.out::println);

		System.out.println("================= Consultas por rangos name =================");
		persons = repository.findByNameBetweenOrderByNameDescLastnameDesc("J","Q");
		persons.forEach(System.out::println);

		System.out.println("================= Consultas getAll() =================");
		persons = repository.findAllByOrderByNameAscLastnameDesc();
		persons.forEach(System.out::println);

	}

	@Transactional(readOnly = true)
	public void peronalizedQueriesConcatUpperAndLowerCase(){

		System.out.println("(================= Consulta nombre y apellidos de personas =================");
		List<String> names = repository.findAllFullNameConcat();
		names.forEach(System.out::println);

		System.out.println("(================= Consulta nombre y apellidos mayusculas =================  ");
		names = repository.findAllFullNameConcatUpper();
		names.forEach(System.out::println);

		System.out.println("(================= Consulta nombre y apellidos minusculas =================  ");
		names = repository.findAllFullNameConcatLower();
		names.forEach(System.out::println);

		System.out.println("(================= Consulta personalizada upper y lower case =================  ");
		List<Object[]> regs = repository.findAllPersonDataListCase();
		regs.forEach(reg -> System.out.println("id=" + reg[0] + ", nombre=" + reg[1] + ", apellido=" + reg[2] + ", lenguaje=" + reg[3]));
		
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesDistinct(){

		System.out.println("================= consultas con nombres de personas ================= ");
		List<String> names = repository.findAllNames();
		names.forEach(System.out:: println);

		System.out.println("================= Consulta con nombres unicos de personas ================= ");
		names = repository.findAllNamesDistinct();
		names.forEach(System.out::println);

		System.out.println(" (\"================= Consulta de lenguajes de programación unicos");
		List<String> languages = repository.findAllprogrammingLanguajeDistinct();
		languages.forEach(System.out::println);

		System.out.println("================= Consulta total de lenguajes de programación unicos");
		Long totalLanguage = repository.findAllprogrammingLanguageDistinctCount();
		System.out.println("Total de lenguajes de programación: " + totalLanguage);
	}

	@Transactional(readOnly = true)
	public void personalizedQueries2(){

		System.out.println("================= Consulta por objeto persona y lenguaje de programacion =================");
		List<Object[]> personRegs = repository.findAllMixPerson();

		personRegs.forEach(reg->{
			System.out.println("programmingLanguage=" +  reg[1] + ", person=" + reg[0]);
		});

		System.out.println("Consulta que puebla y devuelve ogjeto entity de una instancia personalizada");
		List<Person> persons = repository.findAllObjectPersonPersonalized();
		persons.forEach(System.out::println);

		System.out.println("consulta que puebla y devuelve objeto dto de una clase personalizada");
		List<PersonDto> personDto = repository.findAllPersonDto();
		personDto.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizedQueries(){

		Scanner scanner = new Scanner(System.in);

		System.out.println("================= Consulta solo el nombre por el id =================");
		System.out.println("Ingrese el id:");
		Long id = scanner.nextLong();
		scanner.close();

		System.out.println("===== mostrando solo el nombre =====");

		String name = repository.getNameById(id);
		System.out.println(name);

		System.out.println("===== mostrando solo el id =====");
		Long idDb = repository.getIdById(id);
		System.out.println(idDb);

		System.out.println("===== mostrando nombre completo con concat =====");

		String fullName = repository.getFullNameById(id);
		System.out.println(fullName);

		System.out.println("===== consulta por campos personalizados por el id =====");
		Object[] personReg = (Object[]) repository.obtenerPersonDataById(id);
		System.out.println("id=" + personReg[0] + ", nombre=" + personReg[1] + ", apellido=" + personReg[2] + ", lenguaje=" + personReg[3]);

		System.out.println("===== consulta por campos personalizados lista =====");
		List<Object[]> regs = repository.obtenerPersonDataList();
		regs.forEach(reg -> System.out.println("id=" + reg[0] + ", nombre=" + reg[1] + ", apellido=" + reg[2] + ", lenguaje=" + reg[3]));
		
	}

	@Transactional
	public void delete2(){

		repository.findAll().forEach(System.out::println);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id a eliminar:");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);
		optionalPerson.ifPresentOrElse(
			repository::delete, 
			() -> System.out.println("Lo sentimos no existe la persona con ese id!"));
		

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
