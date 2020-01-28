package api.domain;

import org.springframework.data.repository.CrudRepository;

public interface CarroRepository extends CrudRepository<Carro, Long>{
	public Iterable<Carro> findByTipo(String tipo);

}
