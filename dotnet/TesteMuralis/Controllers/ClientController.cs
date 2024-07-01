using Microsoft.AspNetCore.Http.HttpResults;
using Microsoft.AspNetCore.Mvc;
using TesteMuralis.Context;
using TesteMuralis.Dto;
using TesteMuralis.Exceptions;
using TesteMuralis.Models;
using TesteMuralis.Services.Viacep;
using TesteMuralis.Services.Viacep.Adapter;

namespace TesteMuralis.Controllers
{
    [ApiController]
    [Route("/clientes")]
    public class ClientController
    {
        readonly ClienteService _clientService;
        readonly ViacepGateway _viacepService;

        public ClientController(ClienteService clienteService, ViacepGateway viacepGateway)
        {
            _clientService = clienteService;
            _viacepService = viacepGateway;
        }

        [HttpGet]
        public IActionResult GetAll(string? name)
        {
            var clients = new List<Cliente>();
            if (name == null) clients = _clientService.Get();
            else clients = _clientService.Get(name);
            return new OkObjectResult(clients.ConvertAll(client => new ClienteResponseDTO(client)));
        }

        [HttpGet("{id}")]
        public IActionResult Get(int id)
        {
            var client = _clientService.Get(id);

            return new OkObjectResult(new ClienteResponseDTO(client));
        }

        [HttpPost]
        public IActionResult Create([FromBody] ClienteRequestDTO clienteRequest)
        {
            var cliente = new Cliente(clienteRequest);
            cliente.DataCadastro = DateTime.Now.ToString("yyyy-MM-dd");
            var address = _viacepService.BuscarEnderecoPorCep(cliente.Endereco.Cep);

            if (address == null) return new BadRequestResult();

            cliente.Endereco.Cidade = address.localidade;
            cliente.Endereco.Complemento = address.complemento;
            cliente.Endereco.Logradouro = address.logradouro;
            var created = _clientService.Create(cliente);
            return new OkObjectResult(new ClienteResponseDTO(created));
        }

        [HttpPut("{id}")]
        public IActionResult Update(int id, [FromBody] ClienteRequestDTO clienteRequest)
        {
            var cliente = new Cliente(clienteRequest);
            var address = _viacepService.BuscarEnderecoPorCep(cliente.Endereco.Cep);
            if (address == null) return new BadRequestResult();
            cliente.Endereco.Cidade = address.localidade;
            cliente.Endereco.Complemento = address.complemento;
            cliente.Endereco.Logradouro = address.logradouro;
            _clientService.Put(id, cliente);
            return new OkObjectResult(new ClienteResponseDTO(cliente));
        }

        [HttpDelete("{id}")]
        public IActionResult Delete(int id)
        {
            _clientService.Delete(id);
            return new OkResult();
        }
    }
}