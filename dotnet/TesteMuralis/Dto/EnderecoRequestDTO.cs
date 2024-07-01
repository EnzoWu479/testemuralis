using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
// String cep,
//     String logradouro,
//     String cidade,
//     String numero,
//     String complemento
namespace TesteMuralis.Dto
{
    public class EnderecoRequestDTO
    {
        public String? Cep { get; set; }
        public String? Numero { get; set; }
    }
}