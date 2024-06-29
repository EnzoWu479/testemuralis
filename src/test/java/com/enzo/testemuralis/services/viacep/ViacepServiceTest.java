package com.enzo.testemuralis.services.viacep;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.enzo.testemuralis.services.viacep.adapter.ViacepGateway;
import com.enzo.testemuralis.services.viacep.dtos.EnderecoViacep;

@SpringBootTest
public class ViacepServiceTest {
    @Autowired
    private ViacepGateway viacepService;

    @Test
    @DisplayName("Quando buscar por CEP, espero que encontre")
    void when_getCep_expect_find() {
        assertDoesNotThrow(() -> {
            EnderecoViacep endereco = viacepService.buscarEnderecoPorCep("08635020");
            assertEquals(endereco.logradouro(), "Rua das Adálias");
        });
    }

    @Test
    @DisplayName("Quando buscar por CEP, espero que de erro")
    void when_getCep_expect_error() {
        assertThrows(RuntimeException.class, () -> {
            viacepService.buscarEnderecoPorCep("0000000");
        });
    }

}
