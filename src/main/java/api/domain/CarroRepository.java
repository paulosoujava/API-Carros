package api.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CarroRepository extends CrudRepository<Carro, Long>{
	public List<Carro> findByTipo(String tipo);

}
