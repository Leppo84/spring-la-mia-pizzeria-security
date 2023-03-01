const URLParams = new URLSearchParams(window.location.search);
const pizzaId = URLParams.get('id');

axios.get(`http://localhost:8080/api/${pizzaId}`).then((res) => {
	console.log('richiesta ok', res);
	document.querySelector('#pizza_show').innerHTML += `
		<div class="col-md-4">
			<img src="${res.data.photo}" class="img-fluid" alt="Foto della pizza">
		</div>
		<div class="col-md-8">
			<h5 class="">${res.data.name}</h5>
			<p class="">${res.data.description}</p>
			<h6>Ingredienti</h6>
			<p class="">${res.data.price} €</p>
		</div>
    `;
	res.data.ingredient.forEach(ingredient => {
		console.log(ingredient);
		document.querySelector('#pizza_show').innerHTML += `
		<span class="col-4">${ingredient.name}</span>
		<span> </span>
	`
	});
	document.querySelector('#pizza_show').innerHTML += `
    	<div class="row text-center">
			<h3>Offerte</h3>
			<div class="col-12">
				<table class="table">
					<thead>
						<tr>
							<th>Id</th>
							<th>Inizio</th>
							<th>Fine offerta</th>
							<th>Nome</th>
						</tr>
					</thead>
					<tbody>
	`;
	res.data.offers.forEach(offer => {
		console.log(offer);
		document.querySelector('#pizza_show').innerHTML += `
						<tr>
							<td>${offer.id}</td>
							<td>${offer.offerBeginDate}</td>
							<td>${offer.offerEndDate}</td>
							<td>${offer.title}</td>
						</tr>
	`
	});
	document.querySelector('#pizza_show').innerHTML += `
					</tbody>
				</table>
			</div>
		</div>
	`;
}).catch((res) => {
	console.error('errore nella richiesta', res);
	alert('Errore durante la richiesta!');
});