package com.enzo.testemuralis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enzo.testemuralis.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}
