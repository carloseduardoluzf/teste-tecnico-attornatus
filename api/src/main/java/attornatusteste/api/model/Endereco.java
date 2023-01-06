package attornatusteste.api.model;

import attornatusteste.api.dto.EnderecoDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "tbendereco")
@Entity(name = "Endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "endereco_id", nullable = false)
    private Long id;

    private Boolean principal = false;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    @JsonBackReference
    private Pessoa pessoa;


    public Endereco(Boolean principal ,String logradouro, String cep, String numero, String cidade, Pessoa pessoa) {
        this.principal = principal;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.pessoa = pessoa;

    }


}