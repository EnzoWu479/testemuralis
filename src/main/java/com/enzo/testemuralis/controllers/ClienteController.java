package com.enzo.testemuralis.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enzo.testemuralis.dto.ClienteRequestDTO;
import com.enzo.testemuralis.dto.ClienteResponseDTO;
import com.enzo.testemuralis.dto.Response;
import com.enzo.testemuralis.models.Cliente;
import com.enzo.testemuralis.models.Endereco;
import com.enzo.testemuralis.providers.viacep.adapter.ViacepGateway;
import com.enzo.testemuralis.providers.viacep.dtos.EnderecoViacep;
import com.enzo.testemuralis.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ViacepGateway viacepService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<Response<ClienteResponseDTO[]>> getAll() {
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.ok().body(Response.ok(
                clientes.stream().map(ClienteResponseDTO::new).toArray(ClienteResponseDTO[]::new)));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Response<ClienteResponseDTO>> getById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.findById(id);
        if (!cliente.isPresent()) {
            throw new RuntimeException("Cliente não encontrado");
        }
        return ResponseEntity.ok().body(Response.ok(new ClienteResponseDTO(cliente.get())));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<Response<ClienteResponseDTO>> create(@RequestBody ClienteRequestDTO object) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        Cliente client = new Cliente(object);
        client.setDataCadastro(dtf.format(now));
        Endereco endereco = client.getEndereco();
        EnderecoViacep enderecoViacep = viacepService.buscarEnderecoPorCep(endereco.getCep());

        endereco.setLogradouro(enderecoViacep.logradouro());
        endereco.setComplemento(enderecoViacep.complemento());
        endereco.setCidade(enderecoViacep.localidade());
        Cliente cliente = clienteService.save(client);
        return ResponseEntity.ok().body(
                Response.ok(new ClienteResponseDTO(cliente)));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<Response<ClienteResponseDTO>> update(@PathVariable Long id,
            @RequestBody ClienteRequestDTO object) {
        Cliente client = new Cliente(object);
        Endereco endereco = client.getEndereco();
        EnderecoViacep enderecoViacep = viacepService.buscarEnderecoPorCep(endereco.getCep());

        endereco.setLogradouro(enderecoViacep.logradouro());
        endereco.setComplemento(enderecoViacep.complemento());
        endereco.setCidade(enderecoViacep.localidade());

        Cliente cliente = clienteService.update(id, client);
        return ResponseEntity.ok().body(
                Response.ok(new ClienteResponseDTO(cliente)));

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<ClienteResponseDTO>> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.ok().build();
    }
}
