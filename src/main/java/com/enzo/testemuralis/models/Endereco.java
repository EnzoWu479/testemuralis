package com.enzo.testemuralis.models;

import com.enzo.testemuralis.dto.EnderecoRequestDTO;

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
        this.cep = enderecoRequestDTO.cep();
        this.logradouro = enderecoRequestDTO.logradouro();
        this.cidade = enderecoRequestDTO.cidade();
        this.numero = enderecoRequestDTO.numero();
        this.complemento = enderecoRequestDTO.complemento();
    }
}
