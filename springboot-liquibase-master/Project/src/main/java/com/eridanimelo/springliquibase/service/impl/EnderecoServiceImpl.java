package com.eridanimelo.springliquibase.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eridanimelo.springliquibase.config.UserNotFoundException;
import com.eridanimelo.springliquibase.model.Endereco;
import com.eridanimelo.springliquibase.repository.EnderecoRepository;
import com.eridanimelo.springliquibase.service.EnderecoService;

@Service
@Transactional(rollbackFor = Exception.class)
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository repository;

    public EnderecoServiceImpl(EnderecoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Endereco> getAllEnderecos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Endereco> findEnderecoByName(String email) {

        List<Endereco> e = repository.findEnderecoByName(email);

        if (Objects.isNull(e)) {
            throw new UserNotFoundException("Pessoa Sem endereco");
        }
        return e;
    }

    @Override
    public Endereco createEndereco(Endereco endereco) {
        try {
            return repository.save(endereco);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Endereco updateEndereco(Long id, Endereco updatedEndereco) {
        return repository.findById(id)
                .map(endereco -> {
                    endereco.setLogradouro(updatedEndereco.getLogradouro());
                    endereco.setBairro(updatedEndereco.getBairro());
                    endereco.setComplemento(updatedEndereco.getComplemento());
                    endereco.setUf(updatedEndereco.getUf());
                    endereco.setNumero(updatedEndereco.getNumero());
                    endereco.setCidade(updatedEndereco.getCidade());
                    endereco.setCep(updatedEndereco.getCep());
                    return repository.save(endereco);
                })
                .orElseThrow(() -> new RuntimeException("Endereco not found"));
    }

    @Override
    public void deleteEndereco(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao deletar o Endereco");
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Endereco findByCep(String cep) {
        return repository.findByCep(cep);
    }

}
