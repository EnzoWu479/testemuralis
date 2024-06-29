package com.enzo.testemuralis.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.enzo.testemuralis.models.Cliente;
import com.enzo.testemuralis.models.Contato;
import com.enzo.testemuralis.models.Endereco;

@SpringBootTest
@ActiveProfiles("test")
public class ClienteServiceTest {
    @Autowired
    private ClienteService clienteService;

    @Test
    @DisplayName("Quando salvar, espero que crie um novo cliente e encontre pelo id")
    void when_post_expect_create() {
        Cliente cliente = new Cliente(1l, "Cliente Teste", "2021-10-10",
                new Contato(
                        null, "Contato Teste", "Telefone Teste"),
                new Endereco(
                        null, "00000000", "Logradouro teste", "Cidade Teste", "12345678", "Complemento Teste"));
        clienteService.save(cliente);

        assertTrue(clienteService.findById(1l).isPresent());
    }

    @Test
    @DisplayName("Quando buscar por id, espero que não encontre")
    void when_getId_expect_notFound() {
        assertFalse(clienteService.findById(2l).isPresent());
    }

    @Test
    @DisplayName("Quando buscar todos, espero que não encontre nenhum")
    void when_getAll_expect_findEmpty() {
        assertTrue(clienteService.findAll().isEmpty());
    }

    @Test
    @DisplayName("Quando buscar todos, espero que encontre todos")
    void when_getAll_expect_findAll() {
        Cliente cliente = new Cliente(1l, "Cliente Teste", "2021-10-10",
                new Contato(
                        null, "Contato Teste", "Telefone Teste"),
                new Endereco(
                        null, "00000000", "Logradouro teste", "Cidade Teste", "12345678", "Complemento Teste"));
        clienteService.save(cliente);

        assertFalse(clienteService.findAll().isEmpty());
    }

    @Test
    @DisplayName("Quando atualizar, espero que atualize")
    void when_put_expect_update() {
        Cliente cliente = new Cliente(1l, "Cliente Teste", "2021-10-10",
                new Contato(
                        null, "Contato Teste", "Telefone Teste"),
                new Endereco(
                        null, "00000000", "Logradouro teste", "Cidade Teste", "12345678", "Complemento Teste"));
        clienteService.save(cliente);

        assertEquals(clienteService.findById(1l).get().getNome(), "Cliente Teste");

        cliente.setNome("Cliente Teste 2");

        clienteService.update(1l, cliente);

        assertEquals(clienteService.findById(1l).get().getNome(), "Cliente Teste 2");
    }

    @Test
    @DisplayName("Quando deletar, espero que delete")
    void when_delete_expect_delete() {
        Cliente cliente = new Cliente(1l, "Cliente Teste", "2021-10-10",
                new Contato(
                        null, "Contato Teste", "Telefone Teste"),
                new Endereco(
                        null, "00000000", "Logradouro teste", "Cidade Teste", "12345678", "Complemento Teste"));
        clienteService.save(cliente);

        assertTrue(clienteService.findById(1l).isPresent());

        clienteService.delete(1l);

        assertFalse(clienteService.findById(1l).isPresent());
    }
}
