package attornatusteste.api.dto;

import attornatusteste.api.model.Endereco;
import attornatusteste.api.model.Pessoa;
import attornatusteste.api.repository.PessoaRepository;
import attornatusteste.api.service.endereco.IEnderecoService;
import attornatusteste.api.service.endereco.impl.EnderecoService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {

    private String nome;

    private String data_nascimento;

    @JsonIgnore
    private String cep;

    public PessoaDTO(Pessoa pessoa) {
        this.nome = pessoa.getNome();
        this.data_nascimento = pessoa.getData_nascimento();

    }

    public static Page<PessoaDTO> converterToDto(Page<Pessoa> pessoas) {
        return pessoas.map(PessoaDTO::new);
    }


    public Pessoa converterToEntity(IEnderecoService enderecoService) {
        List<Endereco> enderecos = enderecoService.findByCep(cep);
        return new Pessoa(nome, data_nascimento, enderecos);
    }
}
