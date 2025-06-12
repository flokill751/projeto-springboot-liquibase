package com.eridanimelo.springliquibase.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eridanimelo.springliquibase.model.Person;
import com.eridanimelo.springliquibase.repository.PersonRepository;
import com.eridanimelo.springliquibase.service.PersonService;

@Service
@Transactional(rollbackFor = Exception.class)
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Person> getAllPersons(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Person> findPersonByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Person createPerson(Person person) {
        try {
            return repository.save(person);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Person updatePerson(Long id, Person updatedPerson) {
        return repository.findById(id)
                .map(person -> {
                    person.setName(updatedPerson.getName());
                    person.setEmail(updatedPerson.getEmail());
                    person.setPhone(updatedPerson.getPhone());
                    return repository.save(person);
                })
                .orElseThrow(() -> new RuntimeException("Person not found"));
    }

    @Override
    public void deletePerson(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw e;
        }
    }

}
