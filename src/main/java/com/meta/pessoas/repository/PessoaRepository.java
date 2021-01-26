package com.meta.pessoas.repository;

import com.meta.pessoas.model.Pessoa;
import com.meta.pessoas.service.dto.PessoaListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("SELECT CASE WHEN count(p.id) > 0 THEN true ELSE false END " +
            "FROM Pessoa p WHERE p.cpf = :cpf AND (:id IS NULL OR p.id != :id)")
    Boolean existsByCpf(@Param("cpf") String cpf, @Param("id") Long id);

    @Query("SELECT new com.meta.pessoas.service.dto.PessoaListDTO(p.id, p.nome, p.cpf) FROM Pessoa p")
    Page<PessoaListDTO> listar(Pageable pageable);
}
