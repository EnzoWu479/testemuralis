using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
// ring nome,
//     ContatoRequestDTO contato,
//     EnderecoRequestDTO endereco
namespace TesteMuralis.Dto
{
    public class ClienteRequestDTO
    {
        public string? Nome { get; set; }
        public ContatoRequestDTO? Contato { get; set; }
        public EnderecoRequestDTO? Endereco { get; set; }
    }
}