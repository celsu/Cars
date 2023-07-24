package com.api.Cars.domain;

import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CarroDTO {

    private Long id;
    private String nome;
    private String tipo;

   /* public CarroDTO(Carro c){
        this.id = c.getId();
        this.nome = c.getNome();
        this.tipo = c.getTipo();
    }*/

    public static CarroDTO create(Carro c){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(c, CarroDTO.class);
    }

}
