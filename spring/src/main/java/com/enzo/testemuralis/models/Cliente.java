package com.enzo.testemuralis.models;

import java.util.Objects;

import com.enzo.testemuralis.dto.ClienteRequestDTO;
import com.enzo.testemuralis.exceptions.BadRequestException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Cliente.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    public static final String TABLE_NAME = "cliente";  

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, name = "usr_nome")
    private String nome;

    private String dataCadastro;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contato_id", referencedColumnName = "id", nullable = false)
    private Contato contato;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id", nullable = false)
    private Endereco endereco;

    public Cliente(ClienteRequestDTO clienteRequestDTO) {
        setNome(clienteRequestDTO.nome());
        setContato(new Contato(clienteRequestDTO.contato()));
        setEndereco(new Endereco(clienteRequestDTO.endereco()));
    }

    public void setNome(String nome) {
        if (nome.isBlank()) {
            throw new BadRequestException("Nome não pode ser vazio");
        }
        this.nome = nome;
    }

    public void setDataCadastro(String dataCadastro) {
        if (!dataCadastro.isBlank()) {
            if (!dataCadastro.matches("\\d{4}-\\d{2}-\\d{2}")) {
                throw new BadRequestException("Data de cadastro inválida (formato esperado: yyyy-MM-dd)");
            }
            this.dataCadastro = dataCadastro;
        }
    }
}
