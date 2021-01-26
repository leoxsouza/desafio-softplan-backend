package com.meta.pessoas.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UrlGitDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String urlFrontend;
    private String urlBackend;
}
