package com.eridanimelo.springliquibase.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.eridanimelo.springliquibase.model.Endereco;

public interface EnderecoService {

    Page<Endereco> getAllEnderecos(Pageable pageable);

    List<Endereco> findEnderecoByName(String email);

    Endereco createEndereco(Endereco Endereco);

    Endereco updateEndereco(Long id, Endereco updatedEndereco);

    void deleteEndereco(Long id);

    Endereco findByCep(String cep);
}
