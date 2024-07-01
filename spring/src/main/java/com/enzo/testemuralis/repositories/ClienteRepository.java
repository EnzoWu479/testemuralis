package com.enzo.testemuralis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enzo.testemuralis.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    List<Cliente> findByNome(String nome);
}
