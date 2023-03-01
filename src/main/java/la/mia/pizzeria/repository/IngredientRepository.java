package la.mia.pizzeria.repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import la.mia.pizzeria.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}


