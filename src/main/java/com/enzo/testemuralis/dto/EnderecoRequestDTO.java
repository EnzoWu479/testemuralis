package com.enzo.testemuralis.dto;

public record EnderecoRequestDTO(
    Long id,
    String cep,
    String logradouro,
    String cidade,
    String numero,
    String complemento
) {
    
}
