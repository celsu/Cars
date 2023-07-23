package com.api.Cars.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    //tipo é um atributo da classe carro o Spring consegue fazer esse FIND
    List<Carro> findByTipo(String tipo);
}
