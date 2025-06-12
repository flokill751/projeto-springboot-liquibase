package com.eridanimelo.springliquibase.controller;

import java.util.List;

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

import org.springframework.web.bind.annotation.RestController;

import com.eridanimelo.springliquibase.model.Endereco;
import com.eridanimelo.springliquibase.service.EnderecoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/enderecos")
@Tag(name = "Endereco", description = "Endpoints to manage endereco")
public class EnderecoController {

    private final EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @Operation(summary = "List all enderecos")
    @GetMapping
    public Page<Endereco> listEnderecos(
            @Parameter(description = "Paginação e ordenação", examples = {
                    @ExampleObject(name = "Default Pagination", value = "{\"page\": 0, \"size\": 1, \"sort\": \"uf,asc\"}")
            }) @PageableDefault(size = 10, sort = "uf,asc") Pageable pageable) {
        return service.getAllEnderecos(pageable);
    }

    @Operation(summary = "Search endereco by name")
    @GetMapping("/find-by-name/{name}")
    public List<Endereco> getEnderecoByName(@PathVariable("name") String name) {
        return service.findEnderecoByName(name);

    }

    @Operation(summary = "Search endereco by cep")
    @GetMapping("/find-by-cep/{cep}")
    public Endereco getEnderecoByCep(@PathVariable("cep") String cep) {
        return service.findByCep(cep);

    }

    @Operation(summary = "Create a new endereco")
    @PostMapping
    public Endereco createEndereco(@RequestBody Endereco endereco) {
        return service.createEndereco(endereco);
    }

    @Operation(summary = "update an existing endereco")
    @PutMapping("/{id}")
    public ResponseEntity<Endereco> updateEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
        try {
            return ResponseEntity.ok(service.updateEndereco(id, endereco));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "delete a endereco by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Long id) {
        service.deleteEndereco(id);
        return ResponseEntity.noContent().build();
    }

}
