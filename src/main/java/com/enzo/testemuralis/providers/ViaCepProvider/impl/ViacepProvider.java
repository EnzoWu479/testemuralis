package com.enzo.testemuralis.providers.ViaCepProvider.impl;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.enzo.testemuralis.providers.ViaCepProvider.abs.IViacepProvider;
import com.enzo.testemuralis.providers.ViaCepProvider.dtos.EnderecoViacep;
import com.google.gson.Gson;

@Service
public class ViacepProvider implements IViacepProvider {

    @Override
    public EnderecoViacep buscarEnderecoPorCep(String cep) throws ClientProtocolException, IOException {
        EnderecoViacep endereco = null;

        HttpGet request = new HttpGet("https://viacep.com.br/ws/" + cep + "/json/");

        try(
            CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
            CloseableHttpResponse response = httpClient.execute(request);
        ) {
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String result = EntityUtils.toString(entity);

                Gson gson = new Gson();

                endereco = gson.fromJson(result, EnderecoViacep.class);
            }
        }
        return endereco;
    }

}
