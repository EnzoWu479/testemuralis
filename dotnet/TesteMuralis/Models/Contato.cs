using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using TesteMuralis.Dto;

namespace TesteMuralis.Models
{
    public class Contato
    {
        public int Id { get; set; }
        public string Tipo { get; set; }
        public string Texto { get; set; }

        public Contato() {}

        public Contato(ContatoRequestDTO contato) {
            Tipo = contato.Tipo;
            Texto = contato.Texto;
        }
    }
}