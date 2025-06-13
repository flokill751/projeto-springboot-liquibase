package com.eridanimelo.springliquibase.repository;

import com.eridanimelo.springliquibase.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
    List<Telefone> findTelefoneByNumeroT(String numeroT);
}
