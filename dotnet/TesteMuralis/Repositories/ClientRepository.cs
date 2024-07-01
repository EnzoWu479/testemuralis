using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using TesteMuralis.Context;
using TesteMuralis.Exceptions;
using TesteMuralis.Models;

namespace TesteMuralis.Repositories
{
    public class ClientRepository
    {
        readonly ClientContext _context;
        public ClientRepository(ClientContext context)
        {
            _context = context;
        }

        public List<Cliente> Get()
        {
            return _context.Clients.Include(
                x => x.Contato
            ).Include(
                x => x.Endereco
            ).ToList() ?? new List<Cliente>();
        }

        public Cliente Get(int id)
        {
            var client = _context.Clients.Include(
                x => x.Contato
            ).Include(
                x => x.Endereco
            ).FirstOrDefault(x => x.Id == id);
            if (client == null) throw new BadRequestException("Cliente não encontrado");
            return client;
        }
        public List<Cliente> Get(string name)
        {
            return _context.Clients.Include(
                x => x.Contato
            ).Include(
                x => x.Endereco
            ).Where(x => x.Nome.Contains(name)).ToList();
        }

        public Cliente Create(Cliente cliente)
        {
            _context.Clients.Add(cliente);
            _context.SaveChanges();
            return cliente;
        }

        public Cliente Put(Cliente cliente)
        {
            var oldClient = Get(cliente.Id);

            oldClient.Nome = cliente.Nome;
            oldClient.Contato.Texto = cliente.Contato.Texto;
            oldClient.Contato.Tipo = cliente.Contato.Tipo;
            oldClient.Endereco.Cep = cliente.Endereco.Cep;
            oldClient.Endereco.Cidade = cliente.Endereco.Cidade;
            oldClient.Endereco.Complemento = cliente.Endereco.Complemento;
            oldClient.Endereco.Logradouro = cliente.Endereco.Logradouro;
            oldClient.Endereco.Numero = cliente.Endereco.Numero;
            
            _context.Clients.Update(oldClient);
            _context.SaveChanges();
            return oldClient;
        }

        public void Delete(int id)
        {
            var cliente = _context.Clients.FirstOrDefault(x => x.Id == id);
            if (cliente == null) throw new BadRequestException("Cliente não encontrado");
            _context.Clients.Remove(cliente);
            _context.SaveChanges();
        }
    }
}