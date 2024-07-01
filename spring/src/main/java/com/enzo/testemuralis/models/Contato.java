package com.enzo.testemuralis.models;

import com.enzo.testemuralis.dto.ContatoRequestDTO;
import com.enzo.testemuralis.exceptions.BadRequestException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = Contato.TABLE_NAME)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Contato {
    public static final String TABLE_NAME = "contato";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String tipo;

    @Column(nullable = false, length = 100)
    private String texto;

    public Contato(ContatoRequestDTO contatoRequestDTO) {
        this.setTipo(contatoRequestDTO.tipo());
        this.setTexto(contatoRequestDTO.texto());
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setTipo(String tipo) {
        if (tipo == null || tipo.isBlank()) {
            throw new BadRequestException("Tipo não pode ser nulo ou vazio");
        }
        this.tipo = tipo;
    }
    public void setTexto(String texto) {
        if (texto == null || texto.isBlank()) {
            throw new BadRequestException("Texto não pode ser nulo ou vazio");
        }
        this.texto = texto;
    }

}
