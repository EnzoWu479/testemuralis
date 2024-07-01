package com.enzo.testemuralis.dto;

public record EnderecoRequestDTO(
    String cep,
    String logradouro,
    String cidade,
    String numero,
    String complemento
) {
    
}
