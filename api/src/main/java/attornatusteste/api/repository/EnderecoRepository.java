package attornatusteste.api.repository;

import attornatusteste.api.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findByCep(String cep);

    Optional<Endereco> findById(Long id);
}
