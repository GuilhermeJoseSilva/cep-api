package com.br.repository;

import com.br.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    Optional<Cliente> findByCep(String cep);


}
