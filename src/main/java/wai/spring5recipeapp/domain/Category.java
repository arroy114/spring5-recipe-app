package wai.spring5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "recipes")
@ToString(exclude = "recipes")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    //if no config => generate "category_recipes" join table
    //mappedBy = "categories": recipes is mapped by categories, no join table will be created
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes = new HashSet<>();

}
