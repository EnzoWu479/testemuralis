package com.enzo.testemuralis.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enzo.testemuralis.models.Cliente;
import com.enzo.testemuralis.models.Contato;
import com.enzo.testemuralis.models.Endereco;
import com.enzo.testemuralis.repositories.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
    public List<Cliente> findByNome(String nome) {
        return clienteRepository.findByNome(nome);
    }

    public Optional<Cliente> findById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente;
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

        clienteExtraido.setContato(
                new Contato(
                        clienteExtraido.getContato().getId(),
                        cliente.getContato().getTipo(),
                        cliente.getContato().getTexto()));

        clienteExtraido.setEndereco(
                new Endereco(
                        clienteExtraido.getEndereco().getId(),
                        cliente.getEndereco().getCep(),
                        cliente.getEndereco().getLogradouro(),
                        cliente.getEndereco().getCidade(),
                        cliente.getEndereco().getNumero(),
                        cliente.getEndereco().getComplemento()));

        clienteRepository.save(clienteExtraido);

        return clienteExtraido;
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}
