package com.api.Cars.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository rep;
    public List<Carro> getCarros() {
        return rep.findAll();
    }

    public Carro save(Carro carro) {
        return rep.save(carro);
    }
}
