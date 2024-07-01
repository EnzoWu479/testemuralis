package com.enzo.testemuralis.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enzo.testemuralis.dto.ClienteRequestDTO;
import com.enzo.testemuralis.dto.ClienteResponseDTO;
import com.enzo.testemuralis.models.Cliente;
import com.enzo.testemuralis.models.Endereco;
import com.enzo.testemuralis.services.ClienteService;
import com.enzo.testemuralis.services.viacep.adapter.ViacepGateway;
import com.enzo.testemuralis.services.viacep.dtos.EnderecoViacep;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ViacepGateway viacepService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<ClienteResponseDTO[]> getAll(@RequestParam(required = false) String name){
        List<Cliente> clientes;
        if (name != null && !name.isEmpty()) {
            clientes = clienteService.findByNome(name);
        } else {
            clientes = clienteService.findAll();
        } 
        return ResponseEntity.ok().body( 
                clientes.stream().map(ClienteResponseDTO::new).toArray(ClienteResponseDTO[]::new));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getById(@PathVariable Long id) {
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok().body(new ClienteResponseDTO(cliente));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> create(@RequestBody ClienteRequestDTO object) {
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
        return ResponseEntity.ok().body(new ClienteResponseDTO(cliente));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> update(@PathVariable Long id,
            @RequestBody ClienteRequestDTO object) {
        Cliente client = new Cliente(object);
        Endereco endereco = client.getEndereco();
        EnderecoViacep enderecoViacep = viacepService.buscarEnderecoPorCep(endereco.getCep());

        endereco.setLogradouro(enderecoViacep.logradouro());
        endereco.setComplemento(enderecoViacep.complemento());
        endereco.setCidade(enderecoViacep.localidade());

        Cliente cliente = clienteService.update(id, client);
        return ResponseEntity.ok().body(new ClienteResponseDTO(cliente));

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.ok().build();
    }
}
