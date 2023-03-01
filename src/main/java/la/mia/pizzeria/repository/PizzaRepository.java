package la.mia.pizzeria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import la.mia.pizzeria.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
	
	public List<Pizza> findByNameLike(String input);	
//	public List<Pizza> findByName(String input);	

}
