using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using TesteMuralis.Context;
using TesteMuralis.Models;
using TesteMuralis.Repositories;

namespace TesteMuralis.Services.Viacep
{
    public class ClienteService
    {
        readonly ClientRepository _repository;
        public ClienteService(ClientRepository repository)
        {
            _repository = repository;
        }

        public List<Cliente> Get()
        {
            return _repository.Get();
        }

        public Cliente Get(int id)
        {
            return _repository.Get(id);
        }

        public List<Cliente> Get(string name)
        {
            return _repository.Get(name);
        }

        public Cliente Create(Cliente cliente)
        {
            _repository.Create(cliente);
            return cliente;
        }

        public Cliente Put(int id, Cliente cliente)
        {
            cliente.Id = id;
            _repository.Put(cliente);
            return cliente;
        }

        public void Delete(int id)
        {
            _repository.Delete(id);
        }
    }
}