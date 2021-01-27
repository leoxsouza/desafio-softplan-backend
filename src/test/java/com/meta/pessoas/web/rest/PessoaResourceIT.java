package com.meta.pessoas.web.rest;

import com.meta.pessoas.model.Pessoa;
import com.meta.pessoas.service.PessoaService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@SpringBootTest
public class PessoaResourceIT {

    @Autowired
    private PessoaService pessoaService;

    private MockMvc mockMvc;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private static final String API = "/api/pessoas";

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        PessoaResource resource = new PessoaResource(pessoaService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(resource)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                .setMessageConverters(jacksonMessageConverter).build();
    }

    public Pessoa getDefaultPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("TESTE");
        pessoa.setCpf("11111111111");
        pessoa.setDtNascimento(LocalDate.now());
        return pessoa;
    }

    public Pessoa postPessoa(Pessoa pessoa) throws Exception {
        return jacksonMessageConverter.getObjectMapper()
                .readValue(mockMvc.perform(post(API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonMessageConverter.getObjectMapper().writeValueAsBytes(pessoa))).andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), Pessoa.class);
    }

    public Pessoa getDTO(Long id) throws Exception {
        return jacksonMessageConverter.getObjectMapper().readValue(mockMvc.perform(get(API  + "/" + id)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Pessoa.class);
    }

    @Test
    public void pessoaTest() throws Exception {
        Pessoa pessoa = postPessoa(getDefaultPessoa());
        assertNotNull(pessoa.getId());

        pessoa = getDTO(pessoa.getId());

        assertNotNull(pessoa);
        assertNotNull(pessoa.getId());
        assertNotNull(pessoa.getNome());
        assertNotNull(pessoa.getCpf());

        pessoa.setNome("EDITADO");
        pessoa = postPessoa(pessoa);

        assertEquals(pessoa.getNome(), "EDITADO");


        //Teste Listar Pessoas
        mockMvc.perform(get(API)).andExpect(status().isOk())
                .andExpect(jsonPath("$.content", Matchers.hasSize(1)));

        //TEST remover pessoa
        mockMvc.perform(get(API  + "/remover/" + pessoa.getId())).andExpect(status().isOk());

        mockMvc.perform(get(API)).andExpect(status().isOk())
                .andExpect(jsonPath("$.content", Matchers.hasSize(0)));
    }

}
