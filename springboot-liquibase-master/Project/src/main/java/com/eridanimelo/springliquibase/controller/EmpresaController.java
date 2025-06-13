package com.eridanimelo.springliquibase.controller;

import com.eridanimelo.springliquibase.model.Empresa;
import com.eridanimelo.springliquibase.repository.EmpresaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
@Tag(name = "Empresa", description = "Endpoints para gerenciar empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Operation(summary = "Listar todas as empresas")
    @GetMapping
    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }

    @Operation(summary = "Buscar empresa por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscarEmpresaPorId(@PathVariable Long id) {
        return empresaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Criar nova empresa")
    @PostMapping
    public Empresa criarEmpresa(@RequestBody Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Operation(summary = "Atualizar empresa existente")
    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresaAtualizada) {
        return empresaRepository.findById(id)
                .map(empresa -> {
                    
                    empresa.setNome(empresaAtualizada.getNome());
                    empresa.setCnpj(empresaAtualizada.getCnpj());
                    empresa.setNomeFantasia(empresaAtualizada.getNomeFantasia());
                    empresa.setRazaoSocial(empresaAtualizada.getRazaoSocial());
                    empresa.setEmail(empresaAtualizada.getEmail());
                    
                    return ResponseEntity.ok(empresaRepository.save(empresa));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Excluir uma empresa por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable Long id) {
        return empresaRepository.findById(id)
                .map(empresa -> {
                    empresaRepository.delete(empresa);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
