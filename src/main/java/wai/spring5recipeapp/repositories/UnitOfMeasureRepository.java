package wai.spring5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import wai.spring5recipeapp.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
