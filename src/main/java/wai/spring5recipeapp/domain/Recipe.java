package wai.spring5recipeapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    //EnumType.ORDINAL is the default => persisted {EASY, MODERATE, HARD} as {1, 2, 3}
    //EnumType.STRING => persisted as {EASY, MODERATE, HARD}
    //Disadvantage with EnumType.ORDINAL is changing the enum, e.g. to  {VERY EASY, EASY, MODERATE, HARD}
    // => affected previous data e.g. HARD change from 3 to 4
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @Lob
    private Byte[] image;

    //Make the recipe owner the one to one relationship => Recipe is parent and notes is child
    //=> in case of delete or update of a recipe(parent), notes(child) will also be deleted
    //Oppositely, if cascade is not defined here, deleting a recipe, will not delete
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    //mappedby = "recipe"
    //=> each ingredient in the set will be mapped by property "recipe" in class Recipe
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    //If there is no config => results in two join table: recipe-categories and category-recipes
    //@JoinTable can customize the join table
    //joinColumns: id column of this class
    //inverseJoinColumns: id column of related class
    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public Recipe() {
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
