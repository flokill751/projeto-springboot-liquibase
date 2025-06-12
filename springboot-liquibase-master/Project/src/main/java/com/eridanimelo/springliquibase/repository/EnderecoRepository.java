package com.eridanimelo.springliquibase.repository;

import com.eridanimelo.springliquibase.model.Endereco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    String FIND_BY_EMAIL_QUERY = """
            SELECT e FROM Endereco e
            INNER JOIN FETCH e.person p
            WHERE p.name = :name
            """;

    String FIND_BY_CEP_QUERY = """
            SELECT e FROM Endereco e
            INNER JOIN FETCH e.person p
            WHERE e.cep=:cep
            """;

    @Query(FIND_BY_EMAIL_QUERY)
    List<Endereco> findEnderecoByName(@Param("name") String name);

    @Query(FIND_BY_CEP_QUERY)
    Endereco findByCep(@Param("cep") String cep);

}
