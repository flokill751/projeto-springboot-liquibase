package com.eridanimelo.springliquibase.service;

import com.eridanimelo.springliquibase.model.Telefone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TelefoneService {

    Page<Telefone> getAllTelefones(Pageable pageable);

    List<Telefone> findTelefoneByNumero(String numero);

    Telefone createTelefone(Telefone telefone);

    Telefone updateTelefone(Long id, Telefone telefoneAtualizado);

    void deleteTelefone(Long id);
}
