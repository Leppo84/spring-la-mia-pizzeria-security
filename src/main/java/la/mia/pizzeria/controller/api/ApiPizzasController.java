package la.mia.pizzeria.controller.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import la.mia.pizzeria.model.Pizza;
import la.mia.pizzeria.repository.IngredientRepository;
import la.mia.pizzeria.repository.PizzaRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiPizzasController {

	@Autowired
	PizzaRepository PizzaRepository;
	
	@Autowired
	IngredientRepository IngredientRepository;
	
	@GetMapping()
	public List<Pizza> index() {
		return PizzaRepository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Pizza> show(@PathVariable("id") Integer id) {
		Optional<Pizza> res=PizzaRepository.findById(id);
		if (res.isPresent()) 
			return new ResponseEntity<Pizza>(res.get(), HttpStatus.OK);
	    else
	    	return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("{id}")
	public void delete(
			@PathVariable("id") Integer id) {
		PizzaRepository.deleteById(id);
	}

	
	@PostMapping()
	public Pizza create(@RequestBody Pizza pizza) {
		return PizzaRepository.save(pizza);
	}
	
	@PutMapping("{id}")
	public Pizza update(@RequestBody Pizza pizza,
			@PathVariable("id") Integer id) {
		Pizza p=PizzaRepository.getReferenceById(id);
		p.setName(pizza.getName());
		
		return PizzaRepository.save(p);
	}
	
	
}
