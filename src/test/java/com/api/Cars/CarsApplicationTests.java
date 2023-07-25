package com.api.Cars;

import com.api.Cars.domain.Carro;
import com.api.Cars.domain.CarroDTO;
import com.api.Cars.domain.CarroService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CarsApplicationTests {

	@Autowired
	private CarroService service;
	@Test
	void testeAddCarro() {
		Carro ca = new Carro();
		ca.setNome("Ferrari");
		ca.setTipo("Esportivo");

		CarroDTO c = service.save(ca);

		assertNotNull(c);

		Long id = c.getId();
		assertNotNull(id);

		//Buscar Objeto
		Optional<CarroDTO> op = service.getCarrosById(id);
		assertTrue(op.isPresent());

		c = op.get();
		assertEquals("Ferrari", c.getNome());
		assertEquals("Esportivo", c.getTipo());

		//Deletar Objeto
		service.delete(id);

		//Verificar se deletou
		assertFalse(service.getCarrosById(id).isPresent());
	}

	void listaCarros() {

		List<CarroDTO> ca = service.getCarros();

	}

}
