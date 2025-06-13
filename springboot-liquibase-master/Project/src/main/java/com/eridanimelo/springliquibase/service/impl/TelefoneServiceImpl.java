package com.eridanimelo.springliquibase.service.impl;

import com.eridanimelo.springliquibase.config.UserNotFoundException;
import com.eridanimelo.springliquibase.model.Telefone;
import com.eridanimelo.springliquibase.repository.TelefoneRepository;
import com.eridanimelo.springliquibase.service.TelefoneService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(rollbackFor = Exception.class)
public class TelefoneServiceImpl implements TelefoneService {

    private final TelefoneRepository repository;

    public TelefoneServiceImpl(TelefoneRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Telefone> getAllTelefones(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Telefone> findTelefoneByNumero(String numero) {
        List<Telefone> telefones = repository.findTelefoneByNumeroT(numero);
        if (Objects.isNull(telefones) || telefones.isEmpty()) {
            throw new UserNotFoundException("Telefone não encontrado.");
        }
        return telefones;
    }

    @Override
    public Telefone createTelefone(Telefone telefone) {
        return repository.save(telefone);
    }

    @Override
    public Telefone updateTelefone(Long id, Telefone telefoneAtualizado) {
        return repository.findById(id)
                .map(telefone -> {
                    telefone.setNumeroT(telefoneAtualizado.getNumeroT());
                    telefone.setPerson(telefoneAtualizado.getPerson());
                    return repository.save(telefone);
                })
                .orElseThrow(() -> new RuntimeException("Telefone não encontrado"));
    }

    @Override
    public void deleteTelefone(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o telefone");
        }
    }
}
