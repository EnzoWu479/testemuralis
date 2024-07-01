using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using TesteMuralis.Dto;
using TesteMuralis.Exceptions;

namespace TesteMuralis.Models
{
    public class Cliente
    {
        public int Id { get; set; }
        private string _nome;
        public string Nome
        {
            get => _nome;
            set
            {
                if (string.IsNullOrEmpty(value))
                {
                    throw new BadRequestException("Nome é obrigatório");
                }
                _nome = value;
            }
        }
        public string DataCadastro { get; set; }
        public Contato Contato { get; set; }
        public Endereco Endereco { get; set; }

        public Cliente() { }

        public Cliente(ClienteRequestDTO cliente)
        {
            Nome = cliente.Nome;
            Contato = new Contato(cliente.Contato);
            Endereco = new Endereco(cliente.Endereco);
        }
    }
}