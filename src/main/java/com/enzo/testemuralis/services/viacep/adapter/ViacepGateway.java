package com.enzo.testemuralis.services.viacep.adapter;

import com.enzo.testemuralis.services.viacep.dtos.EnderecoViacep;

public interface ViacepGateway {
    EnderecoViacep buscarEnderecoPorCep(String cep);
} 
