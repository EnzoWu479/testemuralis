using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using TesteMuralis.Models;

namespace TesteMuralis.Dto
{
    public class ContatoResponseDTO
    {
        public string Tipo { get; set; }
        public string Texto { get; set; }

        public ContatoResponseDTO(Contato contato)
        {
            Tipo = contato.Tipo;
            Texto = contato.Texto;
        }
    }
}