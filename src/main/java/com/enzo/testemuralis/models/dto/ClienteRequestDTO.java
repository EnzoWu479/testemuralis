package com.enzo.testemuralis.models.dto;

public record ClienteRequestDTO(
    Long id,
    String nome,
    String dataCadastro,
    ContatoRequestDTO contato,
    EnderecoRequestDTO endereco
) {
    
}
