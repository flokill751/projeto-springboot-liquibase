package com.eridanimelo.springliquibase.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.eridanimelo.springliquibase.model.Empresa;

public interface EmpresaService {

    Page<Empresa> getAllEmpresas(Pageable pageable);

    List<Empresa> findEmpresaByNome(String nomeFantasia);

    Empresa createEmpresa(Empresa empresa);

    Empresa updateEmpresa(Long id, Empresa updatedEmpresa);

    void deleteEmpresa(Long id);
}
