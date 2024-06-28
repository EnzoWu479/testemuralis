package com.enzo.testemuralis.providers.viacep.application;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.enzo.testemuralis.providers.viacep.adapter.ViacepGateway;
import com.enzo.testemuralis.providers.viacep.dtos.EnderecoViacep;
import com.google.gson.Gson;

@Service
public class ViacepService implements ViacepGateway {
    class ViacepError {
        boolean erro;
    }

    @Override
    public EnderecoViacep buscarEnderecoPorCep(String cep) {
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

                ViacepError viacepError = gson.fromJson(result, ViacepError.class);

                if (viacepError.erro) {
                    throw new RuntimeException("CEP inválido");
                }

                endereco = gson.fromJson(result, EnderecoViacep.class);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return endereco;
    }

}
