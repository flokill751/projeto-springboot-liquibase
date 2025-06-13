package com.eridanimelo.springliquibase.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eridanimelo.springliquibase.config.UserNotFoundException;
import com.eridanimelo.springliquibase.model.Empresa;
import com.eridanimelo.springliquibase.repository.EmpresaRepository;
import com.eridanimelo.springliquibase.service.EmpresaService;

@Service
@Transactional(rollbackFor = Exception.class)
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository repository;

    public EmpresaServiceImpl(EmpresaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Empresa> getAllEmpresas(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Empresa> findEmpresaByNome(String nomeFantasia) {
        List<Empresa> empresas = repository.findEmpresaByNomeFantasia(nomeFantasia);
        if (Objects.isNull(empresas) || empresas.isEmpty()) {
            throw new UserNotFoundException("Empresa não encontrada");
        }
        return empresas;
    }

    @Override
    public Empresa createEmpresa(Empresa empresa) {
        try {
            return repository.save(empresa);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Empresa updateEmpresa(Long id, Empresa updatedEmpresa) {
        return repository.findById(id)
                .map(empresa -> {
                    empresa.setNomeFantasia(updatedEmpresa.getNomeFantasia());
                    empresa.setCnpj(updatedEmpresa.getCnpj());
                    empresa.setRazaoSocial(updatedEmpresa.getRazaoSocial());
                    return repository.save(empresa);
                })
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
    }

    @Override
    public void deleteEmpresa(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar empresa");
        }
    }
}
