package com.enzo.testemuralis.dto;

import com.enzo.testemuralis.models.Contato;

public record ContatoResponseDTO(
    String tipo,
    String texto
) {
    public ContatoResponseDTO(Contato contato) {
        this(contato.getTipo(), contato.getTexto());
    }
}