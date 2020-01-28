package api.controller;

import api.domain.Carro;
import api.domain.CarroService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carro/v1")
public class CarroController {

	@Autowired
	CarroService cs;

	@GetMapping
	public ResponseEntity<Iterable<Carro>> getFromBD() {
		return ResponseEntity.ok(cs.getCarroByBD());
	}

	@GetMapping("/carro/{id}")
	public ResponseEntity getCarroById(@PathVariable("id") Long id) {
		Optional<Carro> optional = cs.getCarroByID(id);
		return optional.map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
		// return (optional.isPresent()) ? ResponseEntity.ok(optional.get()) :
		// ResponseEntity.noContent().build();

	}

	@GetMapping("/carro/tipo/{tipo}")
	public ResponseEntity getCarroByTipo(@PathVariable("tipo") String tipo) {
		List<Carro> carros = cs.getCarroByTipo(tipo);
		return carros.isEmpty() ?   ResponseEntity.noContent().build() : ResponseEntity.ok(carros);
	}

	@PostMapping
	public String post(@RequestBody Carro c) {
		cs.save(c);
		return "Carro Salvo";
	}

	@PutMapping("/update/{id}")
	public String put(@PathVariable("id") Long id, @RequestBody Carro c) {
		cs.update(c, id);
		return "Carro atualizado";
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		cs.delete(id);
		return "Carro deletado";
	}

	@GetMapping("/inMemory")
	public List<Carro> getInMemory() {
		return cs.getCarros();
	}

}
