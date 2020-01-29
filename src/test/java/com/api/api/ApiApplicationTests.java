package com.api.api;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static junit.framework.TestCase.*;

import java.util.List;
import java.util.Optional;

import api.domain.Carro;
import api.domain.CarroService;
import api.domain.dto.CarroDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
class ApiApplicationTests {


    @Autowired
    private CarroService service;

    @Test
    public void testSave() {

        Carro carro = new Carro();
        carro.setNome("Porshe");
        carro.setTipo("esportivos");

        CarroDTO c = service.save(carro);

        assertNotNull(c);

        Long id = c.getId();
        assertNotNull(id);

        // Buscar o objeto
        Optional<CarroDTO> op = service.getCarroByID(id);
        assertTrue(op.isPresent());

        c = op.get();
        assertEquals("Porshe",c.getNome());
        assertEquals("esportivos",c.getTipo());

        // Deletar o objeto
        service.delete(id);

        // Verificar se deletou
        assertFalse(service.getCarroByID(id).isPresent());
    }

    @Test
    public void testLista() {

        List<CarroDTO> carros = service.getCarroByBD();

        assertEquals(30, carros.size());
    }

    @Test
    public void testListaPorTipo() {

        assertEquals(10, service.getCarroByTipo("classicos").size());
        assertEquals(10, service.getCarroByTipo("esportivos").size());
        assertEquals(10, service.getCarroByTipo("luxo").size());

        assertEquals(0, service.getCarroByTipo("x").size());
    }

    @Test
    public void testGet() {

        Optional<CarroDTO> op = service.getCarroByID(11L);

        assertTrue(op.isPresent());

        CarroDTO c = op.get();

        assertEquals("Ferrari FF", c.getNome());
    }
}
