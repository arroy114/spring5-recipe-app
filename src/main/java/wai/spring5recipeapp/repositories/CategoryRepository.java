package wai.spring5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import wai.spring5recipeapp.domain.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    //Spring data JPS provides the implementation of the query method, we just provide the method name
    // name of the method should be findBy+propertyOfTheClass,
    // Here we find by description which is a property in Category
    Optional<Category> findByDescription(String description);


}
