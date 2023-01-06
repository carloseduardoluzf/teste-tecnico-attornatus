package attornatusteste.api.service.pessoa.impl;

import attornatusteste.api.model.Pessoa;
import attornatusteste.api.repository.PessoaRepository;
import attornatusteste.api.service.pessoa.IPessoaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService implements IPessoaService {

    @Autowired
    private PessoaRepository repository;


    @Override
    @Transactional
    public Pessoa save(Pessoa pessoa) {
        return repository.save(pessoa);
    }
    @Override
    public Pessoa getId(Long pessoa_id){
        return repository.getOne(pessoa_id);
    }

    @Override
    @Transactional
    public Pessoa update(Long pessoa_id){
        Pessoa pessoa = getId(pessoa_id);
        pessoa.setNome(pessoa.getNome());
        pessoa.setData_nascimento(pessoa.getData_nascimento());

        return pessoa;
    }

    @Override
    public Page<Pessoa> findAll(Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    @Override
    public Optional<Pessoa> findById(Long id) {
        return repository.findById(id);
    }
    @Override
    @Transactional
    public void delete(Pessoa pessoa) {
        repository.delete(pessoa);
    }

    @Override
    public Page<Pessoa> findByCep(String cep, Pageable paginacao) {
        return repository.findByEnderecoCep(cep, paginacao);
    }

}
