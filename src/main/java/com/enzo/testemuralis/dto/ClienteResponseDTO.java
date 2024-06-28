package com.enzo.testemuralis.dto;

import com.enzo.testemuralis.models.Cliente;

public record ClienteResponseDTO(
    Long id,
    String nome,
    String dataCadastro,
    ContatoResponseDTO contato,
    EnderecoResponseDTO endereco
) {
    public ClienteResponseDTO(
        Cliente cliente
    ) {
        this(
            cliente.getId(),
            cliente.getNome(),
            cliente.getDataCadastro(),
            new ContatoResponseDTO(cliente.getContato()),
            new EnderecoResponseDTO(cliente.getEndereco())
        );
    }
} 
