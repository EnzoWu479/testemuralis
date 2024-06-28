package com.enzo.testemuralis.dto;

public record ClienteRequestDTO(
    String nome,
    ContatoRequestDTO contato,
    EnderecoRequestDTO endereco
) {
    
}
