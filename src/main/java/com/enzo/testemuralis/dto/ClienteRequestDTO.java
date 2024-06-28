package com.enzo.testemuralis.dto;

public record ClienteRequestDTO(
    Long id,
    String nome,
    String dataCadastro,
    ContatoRequestDTO contato,
    EnderecoRequestDTO endereco
) {
    
}
