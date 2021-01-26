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
@RestController
@RequestMapping(value = "/source")
public class SourceResource {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getGitHubUrl() {
        return new ResponseEntity<>("leoxsouza", HttpStatus.OK);
    }

}
