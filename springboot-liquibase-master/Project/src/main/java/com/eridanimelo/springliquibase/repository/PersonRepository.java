package com.eridanimelo.springliquibase.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eridanimelo.springliquibase.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(String email);

}
