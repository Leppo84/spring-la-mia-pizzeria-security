package la.mia.pizzeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import la.mia.pizzeria.model.Ingredient;
import la.mia.pizzeria.model.Pizza;
import la.mia.pizzeria.repository.IngredientRepository;
import la.mia.pizzeria.repository.PizzaRepository;

@Controller
@RequestMapping("/")
public class PizzasController {

	@Autowired
	PizzaRepository PizzaRepository;
	
	@Autowired
	IngredientRepository IngredientRepository;
	
//	@GetMapping
//	public String index(Model model) {
//		List<Pizza> elencoPizze=PizzaRepository.findAll();
//		model.addAttribute("elencoPizze",elencoPizze);
//		return "index";
//	}
	
	@GetMapping
	public String index(@RequestParam(name = "input", required = false) String input, Model model) {
		List<Pizza> elencoPizze;	
		if (input != null && !input.isEmpty()) {
			elencoPizze = PizzaRepository.findByNameLike("%" + input + "%");
		} else {
			elencoPizze = PizzaRepository.findAll();
		}
		model.addAttribute("elencoPizze", elencoPizze);
		return "index";
	}
	
	@GetMapping("/create")		//gestirà le richieste GET di tipo /create
	public String create(Model model) {
		Pizza pizza=new Pizza();	//non esiste ancora sul DB
		pizza.setPhoto("https://upload.wikimedia.org/wikipedia/commons/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg");
		model.addAttribute("pizza", pizza);
		
		return "/create";
	}
	
	@PostMapping("/create")  	//gestirà le richieste di tipo POST di tipo /create
	public String store(
		@Valid @ModelAttribute("pizza") Pizza formPizza, 
		BindingResult bindingResult,
		Model model){
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("error", "Qualcosa è andato storto :(");
			return "/create";
			}
		
		PizzaRepository.save(formPizza);
		
		return "redirect:/"; //genera un altro get
		
	}

	@GetMapping("/{id}")
	public String singlePizza(@PathVariable("id") Integer id, Model model) {
		Pizza detailPizza = PizzaRepository.getReferenceById(id);
		
//		System.out.println(detailPizza.getOffers().get(1).getOfferBeginDate());

		model.addAttribute("pizza", detailPizza);

		return "show";

	}
	
	@GetMapping("/edit/{id}")		//richieste GET del tipo /edit/xx
	public String edit(@PathVariable("id") Integer id, Model model) {		
		Pizza pizza=PizzaRepository.getReferenceById(id);  //lo recupero dal DB

		List<Ingredient> elencoIngredienti=IngredientRepository.findAll();
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("elencoIngredienti", elencoIngredienti);
		model.addAttribute("edit", true);
		return "/edit";
	}
		
	@PostMapping("/edit/{id}")		//richieste POST del tipo /edit/n
	public String update(
			@Valid @ModelAttribute Pizza formPizza,
			BindingResult bindingResult,
			Model model) {
		
		if (bindingResult.hasErrors())
			return "/edit";
		
//		if (PizzaRepository.findByName(formPizza.getName()).size()>0)
//			System.out.println("Pizza già esistente");
		
		else
			PizzaRepository.save(formPizza);
		
		return "redirect:/";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
	 
	   PizzaRepository.deleteById(id);
	   
	   return "redirect:/";
	}
	
}
