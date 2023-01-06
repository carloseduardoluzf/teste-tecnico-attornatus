package attornatusteste.api.service.endereco;

import attornatusteste.api.model.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IEnderecoService extends Serializable {

    Optional<Endereco> findById(Long id);


    List<Endereco> findByCep(String cep);

    Endereco save(Endereco endereco);

    Page<Endereco> findAll(Pageable paginacao);
}
