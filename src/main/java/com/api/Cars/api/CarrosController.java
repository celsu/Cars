package com.api.Cars.api;

import com.api.Cars.domain.Carro;
import com.api.Cars.domain.CarroDTO;
import com.api.Cars.domain.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        Optional<CarroDTO> ca = service.getCarrosById(id);
        return ca.map(ResponseEntity::ok)
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
    public ResponseEntity postCarro(@RequestBody Carro carro){
        try {
            CarroDTO c = service.save(carro);
            URI location = getUri(c.getId());
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public String putCarro(@PathVariable("id") Long id, @RequestBody Carro carro){
        Carro c = service.update(carro, id);
        return "carro atualizado com sucesso: "+c.getId()+" - "+c.getNome();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCarro(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
        //Na classe ExceptionConfig tem mapeado a excessao caso nao ache o ID (EmptyResultDataAccessException)
    }
}
