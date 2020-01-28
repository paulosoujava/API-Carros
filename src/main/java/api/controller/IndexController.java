package api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
	
	
	@GetMapping
	public String get() {
		return "@GetMapping";
	}
	@GetMapping("/login")
	public String getLogin(@RequestParam("login") String login, @RequestParam("senha") String senha) {
		return "login: "+login +" - Senha:  "+senha;
	}
	@GetMapping("/loginWithVariable/{login}/senha/{senha}")
	public String getLoginWithVariable(@PathVariable("login") String login, @PathVariable("senha") String senha) {
		return "login: "+login +" - Senha:  "+senha;
	}
	
	@PostMapping("/loginWithPost")
	public String getLoginWithPostVariable(@RequestParam("login") String login, @RequestParam("senha") String senha) {
		return "login: "+login +" - Senha:  "+senha;
	}
	
	@GetMapping("/carro/{id}")
	public String getCarroById(@PathVariable("id") String id) {
		return "Carro id: "+id;
	}
	@GetMapping("/carros/{tipo}")
	public String getCarroByTipo(@PathVariable("tipo") String tipo) {
		return "Carro tipo: "+tipo;
	}

	@PostMapping
	public String post() {
		return "@PostMapping";
	}
	@PutMapping
	public String put() {
		return "@PutMapping";
	}
	@DeleteMapping
	public String delete() {
		return "@DeleteMapping";
	}

}
