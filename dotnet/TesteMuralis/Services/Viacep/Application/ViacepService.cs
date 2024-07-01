using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Json;
using System.Threading.Tasks;
using TesteMuralis.Exceptions;
using TesteMuralis.Services.Viacep.Adapter;
using TesteMuralis.Services.Viacep.Dto;

namespace TesteMuralis.Services.Viacep.Application
{
    public class ViacepService : ViacepGateway
    {
        public EnderecoViacep BuscarEnderecoPorCep(string cep)
        {
            var request = new HttpRequestMessage(HttpMethod.Get, $"https://viacep.com.br/ws/{cep}/json/");
            using var client = new HttpClient();
            var response = client.SendAsync(request).Result;
            if (response.IsSuccessStatusCode)
            {
                var content = response.Content.ReadAsStringAsync().Result;
                if (content.Contains("erro"))
                {
                    throw new BadRequestException("CEP não encontrado");
                }
                return JsonSerializer.Deserialize<EnderecoViacep>(content);
            }
            else
            {
                throw new Exception("Erro ao buscar endereço");
            }
        }
    }
}