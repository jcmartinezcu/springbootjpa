package com.jc.curso.springboot.jpa.springbootjpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;
    
    @Column(name = "programming_language")
    private String programmingLanguaje;

    
    public Person() { }

    public Person(Long id, String name, String lastname, String programmingLanguaje) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.programmingLanguaje = programmingLanguaje;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getProgrammingLanguaje() {
        return programmingLanguaje;
    }
    public void setProgrammingLanguaje(String programmingLanguaje) {
        this.programmingLanguaje = programmingLanguaje;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", name=" + name + ", lastname=" + lastname + ", programmingLanguaje="
                + programmingLanguaje + "]";
    }

    
}
