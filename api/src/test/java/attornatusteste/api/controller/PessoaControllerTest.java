package attornatusteste.api.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

import attornatusteste.api.dto.PessoaDTO;
import attornatusteste.api.model.Pessoa;
import attornatusteste.api.repository.PessoaRepository;
import attornatusteste.api.service.endereco.IEnderecoService;
import attornatusteste.api.service.pessoa.IPessoaService;
import attornatusteste.api.service.pessoa.impl.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@WebMvcTest
public class PessoaControllerTest {

    @Autowired
    private PessoaController pessoaController;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private IEnderecoService enderecoService;
    @MockBean
    private IPessoaService pessoaService;

    @MockBean
    private PessoaRepository repository;

    @BeforeEach
    public void setup(){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Carlos Eduardo");
        pessoa.setData_nascimento("12/04/2003");
        repository.save(pessoa);
        standaloneSetup(this.pessoaController);
    }

    @AfterEach
    public void down(){
        repository.deleteAll();
    }

    @Test
    @DisplayName("Salva pessoa com sucesso")
    public void deve_SalvarPessoa() throws Exception {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("Carlos Eduardo");
        pessoaDTO.setData_nascimento("12/04/2003");



        String pessoaDto = mapper.writeValueAsString(pessoaDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/pessoas/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(pessoaDto)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void deveRetornarOk_QuandoBuscarPessoa(){

        Mockito.when(this.pessoaService.findById(1L)).thenReturn(Optional.of(new Pessoa(1L,"Carlos Eduardo", "12/04/2003")));


        given()
                .accept(ContentType.JSON)
                .when()
                .get("api/pessoas/listar/{id}", 1L)
                .then()
                .statusCode(HttpStatus.OK.value());


    }



}
