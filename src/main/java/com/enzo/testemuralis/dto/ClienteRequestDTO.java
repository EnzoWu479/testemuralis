package com.enzo.testemuralis.dto;

public record ClienteRequestDTO(
    Long id,
    String nome,
    ContatoRequestDTO contato,
    EnderecoRequestDTO endereco
) {
    
}
