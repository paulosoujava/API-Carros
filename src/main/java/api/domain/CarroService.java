package api.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import api.domain.Carro;
import api.domain.dto.CarroDTO;

@Service
public class CarroService {

	@Autowired
	CarroRepository rep;
	
	public List<CarroDTO> getCarroByBD(){
		return rep.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
	}
	
	

	public Optional<CarroDTO> getCarroByID(Long id) {
		return rep.findById(id).map(CarroDTO::create);
	}
	

	public List<CarroDTO> getCarroByTipo(String tipo) {
		return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
	}

	public CarroDTO save(Carro c) {
		Assert.isNull(c.getId(), "Ops o id é por nossa conta! ;)");
		return CarroDTO.create(rep.save(c));
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
	public CarroDTO update(Carro c, Long id) {
		Assert.notNull(id, "Não foi possível atualizar o registro");
		Optional<Carro>  optional = getCarroById(id);
		if( optional.isPresent()) {
			Carro db = optional.get();
			db.setNome(c.getNome());
			db.setTipo(c.getTipo());
			return CarroDTO.create(rep.save(db));	
		}
		
		return null;
	}

	private Optional<Carro> getCarroById(Long id) {
		return rep.findById(id);
	}



	
}
