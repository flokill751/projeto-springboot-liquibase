package com.eridanimelo.springliquibase.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eridanimelo.springliquibase.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    
    List<Empresa> findEmpresaByNomeFantasia(String nomeFantasia); // <-- Este método é o que o ServiceImpl usa
}
