using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using TesteMuralis.Services.Viacep.Dto;

namespace TesteMuralis.Services.Viacep.Adapter
{
    public interface ViacepGateway
    {
        EnderecoViacep BuscarEnderecoPorCep(String cep);
    }
}