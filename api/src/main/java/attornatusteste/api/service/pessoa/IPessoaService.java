package attornatusteste.api.service.pessoa;

import attornatusteste.api.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IPessoaService extends Serializable {

    Pessoa save(Pessoa pessoa);

    Pessoa getId(Long pessoa_id);

    Pessoa update(Long pessoa_id);

    Page<Pessoa> findAll(Pageable paginacao);

    Optional<Pessoa> findById(Long pessoa_id);

    void delete(Pessoa pessoa);


    Page<Pessoa> findByCep(String cep, Pageable paginacao);
}
