using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using TesteMuralis.Models;

namespace TesteMuralis.Dto
{
    public class ClienteResponseDTO
    {
        public int Id { get; set; }
        public string Nome { get; set; }
        public string DataCadastro { get; set; }
        public ContatoResponseDTO Contato { get; set; }
        public EnderecoResponseDTO Endereco { get; set; }

        public ClienteResponseDTO(Cliente cliente) {
            Id = cliente.Id;
            Nome = cliente.Nome;
            DataCadastro = cliente.DataCadastro;
            Contato = new ContatoResponseDTO(cliente.Contato);
            Endereco = new EnderecoResponseDTO(cliente.Endereco);
        }
    }
}