using TesteMuralis.Dto;
using TesteMuralis.Exceptions;

namespace TesteMuralis.Models
{
    public class Endereco
    {
        public int Id { get; set; }
        private string _cep;
        public string Cep { get => _cep; set {
            if (string.IsNullOrEmpty(value))
            {
                throw new Exception("CEP é obrigatório");
            }
            // check if has only numbers using regex
            if (!System.Text.RegularExpressions.Regex.IsMatch(value, @"^\d+$"))
            {
                throw new Exception("CEP deve conter apenas números");
            }
            if (value.Length != 8)
            {
                throw new Exception("CEP deve conter 8 dígitos");
            }
            _cep = value;
        } }
        public string Logradouro { get; set; }
        public string Cidade { get; set; }

        private string _numero;
        public string Numero { get => _numero; set {
            if (string.IsNullOrEmpty(value))
            {
                throw new BadRequestException("Número é obrigatório");
            }
            if (!System.Text.RegularExpressions.Regex.IsMatch(value, @"^\d+$"))
            {
                throw new BadRequestException("Número deve conter apenas números");
            }
            _numero = value;
        } }
        public string Complemento { get; set; }

        public Endereco() {}

        public Endereco(EnderecoRequestDTO endereco) {
            Cep = endereco.Cep;
            Numero = endereco.Numero;
        }
    }
}