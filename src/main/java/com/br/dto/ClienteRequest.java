package com.br.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteRequest {

    private String nome;
    private String cep;
}
