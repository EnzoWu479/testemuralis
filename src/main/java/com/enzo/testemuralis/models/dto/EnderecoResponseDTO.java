package com.enzo.testemuralis.models.dto;

import com.enzo.testemuralis.models.Endereco;

public record EnderecoResponseDTO(
        String cep,
        String logradouro,
        String cidade,
        String numero,
        String complemento) {
    public EnderecoResponseDTO(Endereco endereco) {
        this(
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getCidade(),
                endereco.getNumero(),
                endereco.getComplemento());
    }
}