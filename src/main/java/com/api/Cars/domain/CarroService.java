package com.api.Cars.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository rep;
    public List<CarroDTO> getCarros() {
        //Recupera a Lista de carros, e criar carrosDTO, convertendo a lista para DTO
        return rep.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public  Optional<CarroDTO> getCarrosById(Long id) {
        //new cria o objeto no contrutor padrao
        //return rep.findById(id).map(CarroDTO::new);
        //create usa o ModelMapper para crear o DTO
        return rep.findById(id).map(CarroDTO::create);
    }
    public List<CarroDTO> getCarrosByTipo(String tipo) {
        return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO save(Carro carro) {
        return CarroDTO.create(rep.save(carro));
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
        rep.deleteById(id);

    }
}
