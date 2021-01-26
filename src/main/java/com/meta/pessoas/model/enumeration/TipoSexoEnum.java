package com.meta.pessoas.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoSexoEnum {

    M("Masculino"),
    F("Feminino");

    String descricao;
}
