package com.enzo.testemuralis.providers.viacep.adapter;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.enzo.testemuralis.providers.viacep.dtos.EnderecoViacep;

public interface ViacepGateway {
    EnderecoViacep buscarEnderecoPorCep(String cep) throws ClientProtocolException, IOException;
} 
