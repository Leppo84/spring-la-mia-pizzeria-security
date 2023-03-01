package la.mia.pizzeria.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String title;
	
	@NotNull
	private LocalDate offerBeginDate;
	
	@NotNull
	private LocalDate offerEndDate;
	
	@JsonBackReference
	@ManyToOne
	private Pizza pizza;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getOfferBeginDate() {
		return offerBeginDate;
	}

	public void setOfferBeginDate(LocalDate offerBeginDate) {
		this.offerBeginDate = offerBeginDate;
	}

	public LocalDate getOfferEndDate() {
		return offerEndDate;
	}

	public void setOfferEndDate(LocalDate offerEndDate) {
		this.offerEndDate = offerEndDate;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	
	
}
