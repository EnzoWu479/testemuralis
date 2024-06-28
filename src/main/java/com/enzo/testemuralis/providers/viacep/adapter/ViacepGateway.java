package com.enzo.testemuralis.providers.viacep.adapter;

import com.enzo.testemuralis.providers.viacep.dtos.EnderecoViacep;

public interface ViacepGateway {
    EnderecoViacep buscarEnderecoPorCep(String cep);
} 
