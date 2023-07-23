package com.api.Cars.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository rep;
    public List<Carro> getCarros() {
        return rep.findAll();
    }

    public  Optional<Carro> getCarrosById(Long id) {
        Optional<Carro> op = rep.findById(id);
        return op;

    }

    public Carro save(Carro carro) {
        return rep.save(carro);
    }

    public Carro update(Carro carro, Long id) {
        Assert.notNull(id, "Nao foi possivel att");

        Optional<Carro> op = rep.findById(id);
        if(op.isPresent()){
            Carro db = op.get();
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            return rep.save(db);
        } else
            throw new RuntimeException("Nao foi possivel autlaizar");
    }

    public void delete(Long id) {
        Optional<Carro> op = rep.findById(id);
        if(op.isPresent()){
        rep.deleteById(id);
        }
    }
}
