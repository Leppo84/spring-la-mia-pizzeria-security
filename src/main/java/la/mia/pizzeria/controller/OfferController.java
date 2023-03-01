package la.mia.pizzeria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import la.mia.pizzeria.model.Offer;
import la.mia.pizzeria.model.Pizza;
import la.mia.pizzeria.repository.OfferRepository;
import la.mia.pizzeria.repository.PizzaRepository;

@Controller
@RequestMapping("/offers")
public class OfferController {

	@Autowired
	PizzaRepository PizzaRepository;

	@Autowired
	OfferRepository OfferRepository;
	
	//gestirà le richieste GET di tipo /borrowings/create?bookId=xxx

	@GetMapping("/create")
	public String create(@RequestParam(name = "id", required = true) Integer id, Model model)
			throws Exception {

		Offer offer = new Offer();

		 Optional<Pizza> res=PizzaRepository.findById(id); if (res.isPresent()) {
		 offer.setPizza(res.get()); } else throw new
		 Exception("Pizza non trovata. Id="+ id);

		model.addAttribute("offer", offer);

		return "offers/create";
	}

	@PostMapping("/create") // gestirà le richieste di tipo POST di tipo /offers/create
	public String store(@Valid @ModelAttribute("offer") Offer formOffer, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors())
			return "offers/create";

//		Pizza pizza = formOffer.getPizza();
		OfferRepository.save(formOffer);

		return "redirect:/offers"; // genera un altro get

	}

	@GetMapping()
	public String index(Model model) {
		List<Offer> offerList = OfferRepository.findAll();
		model.addAttribute("elencoOfferte", offerList);

		return "offers/index";
	}

}
