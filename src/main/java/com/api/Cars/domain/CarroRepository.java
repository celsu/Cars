package com.api.Cars.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {

}
