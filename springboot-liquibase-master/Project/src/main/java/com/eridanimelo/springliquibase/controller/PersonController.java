package com.eridanimelo.springliquibase.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eridanimelo.springliquibase.model.Person;
import com.eridanimelo.springliquibase.service.PersonService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/persons")
@Tag(name = "Person", description = "Endpoints to manage Persons")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @Operation(summary = "List all persons")
    @GetMapping
    public Page<Person> listPersons(
            @Parameter(description = "Paginação e ordenação", examples = {
                    @ExampleObject(name = "Default Pagination", value = "{\"page\": 0, \"size\": 1, \"sort\": \"name,asc\"}")
            }) @PageableDefault(size = 10, sort = "name,asc") Pageable pageable) {
        return service.getAllPersons(pageable);
    }

    @Operation(summary = "Search person by email")
    @GetMapping("/email")
    public ResponseEntity<Person> getPersonByEmail(@RequestParam String email) {
        return service.findPersonByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new person")
    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return service.createPerson(person);
    }

    @Operation(summary = "update an existing person")
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        try {
            return ResponseEntity.ok(service.updatePerson(id, person));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "delete a person by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        service.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

}
