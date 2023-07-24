package com.api.Cars.api;

import com.api.Cars.domain.Carro;
import com.api.Cars.domain.CarroDTO;
import com.api.Cars.domain.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
    @Autowired
    private CarroService service;
    @GetMapping()
    public ResponseEntity getCarros(){
        return ResponseEntity.ok(service.getCarros());
    }

    @GetMapping("/{id}")
    public ResponseEntity getCarros(@PathVariable("id") Long id){
        Optional<Carro> ca = service.getCarrosById(id);
        return ca
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity getCarros(@PathVariable("tipo") String tipo){
        //return ResponseEntity.ok(service.getCarrosByTipo(tipo));
        List<CarroDTO> ca = service.getCarrosByTipo(tipo);
        return ca.isEmpty() ?
                ResponseEntity.noContent().build():
                ResponseEntity.ok(ca);
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
