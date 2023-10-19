package com.br.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteAtualizacaoRequest {
    private String nome;
    private String cep;
}
