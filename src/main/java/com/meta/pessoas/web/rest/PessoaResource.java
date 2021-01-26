package com.meta.pessoas.web.rest;

import com.meta.pessoas.model.Pessoa;
import com.meta.pessoas.service.PessoaService;
import com.meta.pessoas.service.dto.PessoaListDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequestMapping(value = "/api/pessoas")
@RequiredArgsConstructor
public class PessoaResource {

    private final PessoaService pessoaService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pessoa> cadastrar(@RequestBody @Valid Pessoa pessoa) throws Exception {
        log.info("Request para cadastrar ou editar uma Pessoa: {}", pessoa.toString());
        return new ResponseEntity<>(pessoaService.cadastrar(pessoa), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PessoaListDTO>> listar(Pageable pageable) {
        log.info("Request para listar as pessoas por pagina {}", pageable.getPageNumber());
        return new ResponseEntity<>(pessoaService.listar(pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
        log.info("Request para buscar uma pessoa pelo id: {}", id);
        return new ResponseEntity<>(pessoaService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/remover/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        log.info("Request para deletar uma pessoa: {}", id);
        pessoaService.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
