package GupyTest.demo.models.entities.repositories;

import GupyTest.demo.models.entities.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Integer> {
}
