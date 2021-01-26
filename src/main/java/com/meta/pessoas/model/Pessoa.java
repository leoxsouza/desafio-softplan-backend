package com.meta.pessoas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meta.pessoas.model.enumeration.TipoSexoEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "TB_PESSOA")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PESSOA")
    @SequenceGenerator(name = "SQ_PESSOA", sequenceName = "SQ_PESSOA")
    @Column(name = "ID_PESSOA")
    private Long id;

    @Column(name = "NOME", nullable = false)
    @NotNull(message = "Nome obrigatório!")
    @NotBlank(message = "Nome obrigatório!")
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "TP_SEXO")
    private TipoSexoEnum sexo;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "DT_NASCIMENTO", nullable = false)
    @NotNull
    private LocalDate dtNascimento;

    @Column(name = "DS_EMAIL")
    @Email
    private String email;

    @Size(max = 11)
    @NotNull
    @Column(name = "NU_CPF", nullable = false)
    private String cpf;

    @Column(name = "NATURALIDADE")
    private String naturalidade;

    @Column(name = "NACIONALIDADE")
    private String nacionalidade;

    @Column(name = "DH_CADASTRO")
    private LocalDateTime dtCadastro;

    @Column(name = "DH_ALTERACAO")
    private LocalDateTime dtAlteracao;

}
