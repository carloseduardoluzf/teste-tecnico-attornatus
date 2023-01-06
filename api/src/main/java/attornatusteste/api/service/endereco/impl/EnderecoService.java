package attornatusteste.api.service.endereco.impl;

import attornatusteste.api.model.Endereco;
import attornatusteste.api.repository.EnderecoRepository;
import attornatusteste.api.service.endereco.IEnderecoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService implements IEnderecoService {

    @Autowired
    private EnderecoRepository repository;


    @Override
    public Optional<Endereco> findById(Long id) {
        return repository.findById(id);
    }
    @Override
    public List<Endereco> findByCep(String cep){
        return repository.findByCep(cep);
    }
    @Override
    @Transactional
    public Endereco save(Endereco endereco) {
        return repository.save(endereco);
    }

    @Override
    public Page<Endereco> findAll(Pageable paginacao){
        return repository.findAll(paginacao);
    }




}
