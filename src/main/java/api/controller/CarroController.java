package api.controller;

import api.domain.Carro;
import api.domain.dto.CarroDTO;
import api.domain.CarroService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/carro/v1")
public class CarroController {

	@Autowired
	CarroService cs;

	@GetMapping
	public ResponseEntity getFromBD() {
		return ResponseEntity.ok(cs.getCarroByBD());
	}

	@GetMapping("/{id}")
	public ResponseEntity getCarroById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(cs.getCarroByID(id));

	}

	@GetMapping("/tipo/{tipo}")
	public ResponseEntity getCarroByTipo(@PathVariable("tipo") String tipo) {
		List<CarroDTO> carros = cs.getCarroByTipo(tipo);
		return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);
	}

	@PostMapping
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity post(@RequestBody Carro c) {
		CarroDTO carro = cs.save(c);
		return ResponseEntity.created(getUri(carro.getId())).build();

	}

	@PutMapping("/update/{id}")
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Carro c) {
		CarroDTO carro = cs.update(c, id);
		return carro != null ? ResponseEntity.ok(carro) : ResponseEntity.noContent().build();

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		cs.delete(id);
		return ResponseEntity.ok().build();

	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

}
