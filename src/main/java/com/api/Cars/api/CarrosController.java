package com.api.Cars.api;

import com.api.Cars.domain.Carro;
import com.api.Cars.domain.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
    @Autowired
    private CarroService service;
    @GetMapping()
    public List<Carro>getCarros(){
        return service.getCarros();
    }
    @PostMapping
    public String postCarros(@RequestBody Carro carro){
        Carro c = service.save(carro);
        return "carro com sucesso: "+c.getNome()+" - "+c.getTipo();
    }
}
