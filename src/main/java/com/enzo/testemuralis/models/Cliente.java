package com.enzo.testemuralis.models;

import com.enzo.testemuralis.dto.ClienteRequestDTO;

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
        this.nome = clienteRequestDTO.nome();
        this.dataCadastro = clienteRequestDTO.dataCadastro();
        this.contato = new Contato(clienteRequestDTO.contato());
        this.endereco = new Endereco(clienteRequestDTO.endereco());
    }
}
