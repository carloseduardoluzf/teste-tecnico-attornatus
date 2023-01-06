package attornatusteste.api.controller;

import attornatusteste.api.dto.EnderecoDTO;
import attornatusteste.api.dto.PessoaDTO;
import attornatusteste.api.model.Endereco;
import attornatusteste.api.model.Pessoa;
import attornatusteste.api.service.endereco.IEnderecoService;
import attornatusteste.api.service.pessoa.IPessoaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
@RestController
@RequestMapping("/api/pessoas/endereco")
public class EnderecoController {
    @Autowired
    private IPessoaService pessoaService;
    @Autowired
    private IEnderecoService enderecoService;


    @Transactional
    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrarEndereco(@RequestBody @Valid EnderecoDTO dto, UriComponentsBuilder uriBuilder){
        Endereco endereco = dto.converterToEntity(pessoaService);
        enderecoService.save(endereco);

        URI uri = uriBuilder.path("/endereco/{id}").buildAndExpand(endereco.getId()).toUri();
        return ResponseEntity.created(uri).body(new EnderecoDTO(endereco));
    }

    @GetMapping("/listar")
    public Page<EnderecoDTO> listarEnderecos( @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable paginacao) {

        Page<Endereco> endereco = enderecoService.findAll(paginacao);
        return EnderecoDTO.converterToDto(endereco);


    }

}
