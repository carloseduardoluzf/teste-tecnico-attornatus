package attornatusteste.api.controller;

import attornatusteste.api.dto.PessoaDTO;
import attornatusteste.api.model.Pessoa;
import attornatusteste.api.repository.PessoaRepository;
import attornatusteste.api.service.endereco.IEnderecoService;
import attornatusteste.api.service.pessoa.IPessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("api/pessoas")
public class PessoaController {
    @Autowired
    private IEnderecoService enderecoService;
    @Autowired
    private IPessoaService pessoaService;

    @Autowired
    private PessoaRepository repository;



    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrarPessoa(@RequestBody @Valid PessoaDTO pessoaDTO, UriComponentsBuilder uriBuilder){
        Pessoa pessoa = pessoaDTO.converterToEntity(enderecoService);
        pessoaService.save(pessoa);

        URI uri = uriBuilder.path("/api/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(new PessoaDTO(pessoa));
    }

    @GetMapping("/listar")
    public Page<PessoaDTO> listarPessoas(@RequestParam(required = false) String cep,@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable paginacao){
        if (cep == null) {
            Page<Pessoa> pessoas = pessoaService.findAll(paginacao);
            return PessoaDTO.converterToDto(pessoas);
        }

        else {
            Page<Pessoa> pessoas = pessoaService.findByCep(cep, paginacao);
            return PessoaDTO.converterToDto(pessoas);
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Object> listarPessoaEndereco(@PathVariable("id")  Long id){
        Optional<Pessoa> pessoaOptional = pessoaService.findById(id);
        if (!pessoaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pessoaOptional.get());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> removerPessoa(@PathVariable("id") Long id){
        Optional<Pessoa> pessoaOptional = pessoaService.findById(id);
        if (!pessoaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada, não foi possível remover!");
        }
        pessoaService.delete(pessoaOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa foi removida com sucesso!");
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarDadosPessoa(@PathVariable Long id,@RequestBody @Valid PessoaDTO dto){
        Optional<Pessoa> pessoaOptional = pessoaService.findById(id);
        if (!pessoaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada, não foi possível atualizar os dados!");
        }
        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(dto, pessoa);
        pessoa.setId(pessoaOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.save(pessoa));
    }

}
