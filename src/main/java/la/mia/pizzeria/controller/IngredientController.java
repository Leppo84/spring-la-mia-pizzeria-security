package la.mia.pizzeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import la.mia.pizzeria.model.Ingredient;
import la.mia.pizzeria.repository.IngredientRepository;
import la.mia.pizzeria.repository.OfferRepository;
import la.mia.pizzeria.repository.PizzaRepository;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	PizzaRepository PizzaRepository;

	@Autowired
	OfferRepository OfferRepository;
	
	@Autowired
	IngredientRepository IngredientRepository;

	@GetMapping()
	public String index(Model model) {
		List<Ingredient> ingredientList = IngredientRepository.findAll();
		model.addAttribute("elencoIngredienti", ingredientList);

		return "ingredients/index";
	}
	
	@GetMapping("/create")		//gestirà le richieste GET di tipo /ingredients/create
	public String create(Model model) {
		Ingredient ingredient=new Ingredient();	//non esiste ancora sul DB
		
		model.addAttribute("ingredient", ingredient);
	
		return "ingredients/create";
	}

	@PostMapping("/create")
	public String store(
		@ModelAttribute("ingredient") Ingredient formIngredient, 
		BindingResult bindingResult,
		Model model){
	
//		if (bindingResult.hasErrors())
//			return "ingredients/index";
		
		IngredientRepository.save(formIngredient);
		
		return "redirect:/ingredients";
		
	}
	
}
