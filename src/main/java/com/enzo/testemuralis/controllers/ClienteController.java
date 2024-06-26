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
import org.springframework.web.bind.annotation.RestController;

import com.enzo.testemuralis.models.Cliente;
import com.enzo.testemuralis.models.Endereco;
import com.enzo.testemuralis.models.dto.ClienteRequestDTO;
import com.enzo.testemuralis.models.dto.ErrorDTO;
import com.enzo.testemuralis.providers.ViaCepProvider.dtos.EnderecoViacep;
import com.enzo.testemuralis.providers.ViaCepProvider.impl.ViacepProvider;
import com.enzo.testemuralis.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ViacepProvider viacepProvider;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.ok().body(clientes);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Cliente cliente = clienteService.findById(id);
            return ResponseEntity.ok().body(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ClienteRequestDTO object) {
        Cliente client = new Cliente(object);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        client.setDataCadastro(dtf.format(now));
        Endereco endereco = client.getEndereco();

        try {
            EnderecoViacep enderecoViacep = viacepProvider.buscarEnderecoPorCep(endereco.getCep());
            System.out.println(enderecoViacep.getLocalidade());

            endereco.setLogradouro(enderecoViacep.getLogradouro());
            endereco.setComplemento(enderecoViacep.getComplemento());
            endereco.setCidade(enderecoViacep.getLocalidade());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorDTO("CEP inválido"));
        }

        Cliente cliente = clienteService.save(client);
        return ResponseEntity.ok().body(cliente);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ClienteRequestDTO object) {
        Cliente client = new Cliente(object);
        Endereco endereco = client.getEndereco();

        try {
            EnderecoViacep enderecoViacep = viacepProvider.buscarEnderecoPorCep(endereco.getCep());

            endereco.setLogradouro(enderecoViacep.getLogradouro());
            endereco.setComplemento(enderecoViacep.getComplemento());
            endereco.setCidade(enderecoViacep.getLocalidade());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorDTO("CEP inválido"));
        }

        try {

            Cliente cliente = clienteService.update(id, client);
            return ResponseEntity.ok().body(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.ok().build();
    }
}
