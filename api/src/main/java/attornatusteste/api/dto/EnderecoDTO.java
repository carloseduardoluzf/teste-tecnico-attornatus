package attornatusteste.api.dto;

import attornatusteste.api.model.Endereco;
import attornatusteste.api.model.Pessoa;
import attornatusteste.api.service.pessoa.IPessoaService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.swing.text.StyledEditorKit;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    private Boolean principal;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private Long pessoa_id;

    public EnderecoDTO(Endereco endereco) {
        this.principal = endereco.getPrincipal();
        this.logradouro = endereco.getLogradouro();
        this.cep = endereco.getCep();
        this.numero = endereco.getNumero();
        this.cidade = endereco.getCidade();
        this.pessoa_id = endereco.getPessoa().getId();
    }


    public Endereco converterToEntity(IPessoaService pessoaService) {
        Pessoa pessoas = pessoaService.getId(pessoa_id);
        return new Endereco(principal, logradouro, cep, numero, cidade, pessoas);
    }

    public static Page<EnderecoDTO> converterToDto(Page<Endereco> enderecos) {
        return enderecos.map(EnderecoDTO::new);
    }
}
