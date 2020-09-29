package wai.spring5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import wai.spring5recipeapp.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
