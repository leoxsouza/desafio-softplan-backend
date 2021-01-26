package com.meta.pessoas.service;

import com.meta.pessoas.model.Pessoa;
import com.meta.pessoas.repository.PessoaRepository;
import com.meta.pessoas.service.dto.PessoaListDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
@AllArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public Pessoa cadastrar(Pessoa pessoa) throws Exception {
        processamentoSalvar(pessoa);
        return pessoaRepository.save(pessoa);
    }

    private void processamentoSalvar(Pessoa pessoa) throws Exception {
        if (pessoa.getId() == null) {
            pessoa.setDtCadastro(LocalDateTime.now());
        } else {
            pessoa.setDtAlteracao(LocalDateTime.now());
        }

        if (pessoaRepository.existsByCpf(pessoa.getCpf(), pessoa.getId())) {
            throw new Exception("CPF Já cadastrado!");
        }
    }

    public Page<PessoaListDTO> listar(Pageable pageable) {
        return pessoaRepository.listar(pageable);
    }

    public void deletar(Long id) {
        pessoaRepository.deleteById(id);
    }

    public Pessoa findById(Long id) {
        return pessoaRepository.findById(id).orElse(null);
    }
}
