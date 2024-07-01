using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using TesteMuralis.Models;

namespace TesteMuralis.Dto
{
    public class EnderecoResponseDTO
    {
        public string Cep { get; set; }
        public string Logradouro { get; set; }
        public string Cidade { get; set; }
        public string Numero { get; set; }
        public string Complemento { get; set; }

        public EnderecoResponseDTO(Endereco endereco)
        {
            Cep = endereco.Cep;
            Logradouro = endereco.Logradouro;
            Cidade = endereco.Cidade;
            Numero = endereco.Numero;
            Complemento = endereco.Complemento;
        }
    }
}