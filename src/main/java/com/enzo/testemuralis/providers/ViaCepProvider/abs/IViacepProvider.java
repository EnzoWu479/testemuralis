package com.enzo.testemuralis.providers.ViaCepProvider.abs;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.enzo.testemuralis.providers.ViaCepProvider.dtos.EnderecoViacep;

public interface IViacepProvider {
    EnderecoViacep buscarEnderecoPorCep(String cep) throws ClientProtocolException, IOException;
} 
