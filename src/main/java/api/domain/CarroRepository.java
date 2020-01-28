package api.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CarroRepository extends JpaRepository<Carro, Long>{
	public List<Carro> findByTipo(String tipo);

}
