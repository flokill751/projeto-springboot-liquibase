package com.eridanimelo.springliquibase.controller;

import java.util.List;

import com.eridanimelo.springliquibase.model.Telefone;
import com.eridanimelo.springliquibase.repository.TelefoneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/telefones")
@Tag(name = "Telefone", description = "Endpoints to manage telefones")
public class TelefoneController {

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Operation(summary = "List all telefones")
    @GetMapping
    public List<Telefone> listarTelefones() {
        return telefoneRepository.findAll();
    }

    @Operation(summary = "Find telefone by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Telefone> buscarTelefonePorId(@PathVariable Long id) {
        return telefoneRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new telefone")
    @PostMapping
    public Telefone criarTelefone(@RequestBody Telefone telefone) {
        return telefoneRepository.save(telefone);
    }

    @Operation(summary = "Update telefone by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Telefone> atualizarTelefone(@PathVariable Long id, @RequestBody Telefone telefoneAtualizado) {
        return telefoneRepository.findById(id)
                .map(telefone -> {
                    telefone.setNumeroT(telefoneAtualizado.getNumeroT());
                    telefone.setPerson(telefoneAtualizado.getPerson());
                    return ResponseEntity.ok(telefoneRepository.save(telefone));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete telefone by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTelefone(@PathVariable Long id) {
        return telefoneRepository.findById(id)
                .map(telefone -> {
                    telefoneRepository.delete(telefone);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
