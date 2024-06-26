package com.enzo.testemuralis.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enzo.testemuralis.models.Cliente;
import com.enzo.testemuralis.repositories.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
    public Cliente findById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado");
        }
        return cliente.get();
    }

    @Transactional
    public Cliente save(Cliente cliente) {
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return clienteSalvo;
    }

    @Transactional
    public Cliente update(Long id, Cliente cliente) {
        Optional<Cliente> clienteSalvo = clienteRepository.findById(id);

        if (clienteSalvo.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado");
        }
        Cliente clienteExtraido = clienteSalvo.get();

        clienteExtraido.setNome(cliente.getNome());
        
        clienteExtraido.getContato().setTexto(cliente.getContato().getTexto());
        clienteExtraido.getContato().setTipo(cliente.getContato().getTipo());

        clienteExtraido.getEndereco().setCep(cliente.getEndereco().getCep());
        clienteExtraido.getEndereco().setCidade(cliente.getEndereco().getCidade());
        clienteExtraido.getEndereco().setComplemento(cliente.getEndereco().getComplemento());
        clienteExtraido.getEndereco().setLogradouro(cliente.getEndereco().getLogradouro());
        clienteExtraido.getEndereco().setNumero(cliente.getEndereco().getNumero());

        return clienteExtraido;
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}
