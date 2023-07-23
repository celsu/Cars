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
    public String postCarro(@RequestBody Carro carro){
        Carro c = service.save(carro);
        return "carro add com sucesso: "+c.getNome()+" - "+c.getTipo();
    }

    @PutMapping("/{id}")
    public String putCarro(@PathVariable("id") Long id, @RequestBody Carro carro){
        Carro c = service.update(carro, id);
        return "carro atualizado com sucesso: "+c.getId()+" - "+c.getNome();
    }

    @DeleteMapping("/{id}")
    public String deleteCarro(@PathVariable("id") Long id){
         service.delete(id);
        return "carro deletado com sucesso";
    }
}
