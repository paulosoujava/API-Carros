package api.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import api.domain.Carro;

@Service
public class CarroService {

	@Autowired
	CarroRepository rep;
	
	public Iterable<Carro> getCarroByBD(){
		return  rep.findAll();
	}
	
	

	public Optional<Carro> getCarroByID(Long id) {
		return rep.findById(id);
	}
	

	public Iterable<Carro> getCarroByTipo(String tipo) {
		return rep.findByTipo(tipo);
	}

	public Carro save(Carro c) {
		return rep.save(c);
	}
	public boolean delete(Long id) {
		Assert.notNull(id, "Não foi possível atualizar o registro");
		Optional<Carro>  optional = getCarroById(id);
		if( optional.isPresent()) {
			rep.deleteById(id);
			return true;
		}
		return false;
	}
	public Carro update(Carro c, Long id) {
		Assert.notNull(id, "Não foi possível atualizar o registro");
		Optional<Carro>  optional = getCarroById(id);
		if( optional.isPresent()) {
			Carro db = optional.get();
			db.setNome(c.getNome());
			db.setTipo(c.getTipo());
			return rep.save(db);	
		}
		
		return c;
	}

	private Optional<Carro> getCarroById(Long id) {
		return rep.findById(id);
	}



	public List<Carro> getCarros(){
		List<Carro> carros = new ArrayList<>();
		carros.add( new Carro( 1L, "Fucao", "classico"));
		carros.add( new Carro( 2L, "Passat", "classico"));
		carros.add( new Carro( 3L, "Monza", "classico"));
		carros.add( new Carro( 4L, "Luna", "classico"));
		return  carros;
	}

	
}
