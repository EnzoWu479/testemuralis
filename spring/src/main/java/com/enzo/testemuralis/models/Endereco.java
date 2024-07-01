package com.enzo.testemuralis.models;

import java.util.Objects;

import com.enzo.testemuralis.dto.EnderecoRequestDTO;
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
import lombok.Setter;

@Entity
@Table(name = Endereco.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Endereco {
    public static final String TABLE_NAME = "endereco";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 8)
    private String cep;

    @Column(nullable = false, length = 100)
    private String logradouro;

    @Column(nullable = false, length = 100)
    private String cidade;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = true, length = 100)
    private String complemento;

    public Endereco(EnderecoRequestDTO enderecoRequestDTO) {
        setCep(enderecoRequestDTO.cep());
        setLogradouro(enderecoRequestDTO.logradouro());
        setCidade(enderecoRequestDTO.cidade());
        setNumero(enderecoRequestDTO.numero());
        setComplemento(enderecoRequestDTO.complemento());
    }

    public void setCep(String cep) {
        if (cep.isBlank()) {
            throw new BadRequestException("CEP não pode ser vazio");
        }
        
        cep = cep.replaceAll("[^0-9]", "");
        if (cep.length() != 8) {
            throw new BadRequestException("CEP deve conter 8 caracteres");
        }
        this.cep = cep;
    }
    public void setNumero(String numero) {
        if (numero.isBlank()) {
            throw new BadRequestException("Número não pode ser vazio");
        }
        if (!numero.matches("[0-9]+")) {
            throw new BadRequestException("Número deve conter apenas números");
        }
        this.numero = numero;
    }
}
