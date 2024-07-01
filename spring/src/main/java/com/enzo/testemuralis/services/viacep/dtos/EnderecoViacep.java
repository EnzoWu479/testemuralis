package com.enzo.testemuralis.services.viacep.dtos;

public record EnderecoViacep(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf,
        String ibge,
        String gia,
        String ddd,
        String siafi) {
}
