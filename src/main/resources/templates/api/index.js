
pizzaList();

function pizzaList() {
	axios.get('http://localhost:8080/api').then((res) => {
		//codice da eseguire se la richiesta è andata a buon fine
		console.log('richiesta ok', res);
		document.querySelector('#pizza_table').innerHTML = '';
		res.data.forEach(pizza => {
			console.log(pizza);
			document.querySelector('#pizza_table').innerHTML += `
            <tr>
				<td scope="col">${pizza.id}</td>
				<td>
                    <a href="./show.html?id=${pizza.id}">${pizza.name}</a>
                </td>
				<td scope="col">${pizza.description}</td>
				<td scope="col">${pizza.price}</td>
				<td scope="col">
					<img src="${pizza.photo}" class="col-5 img-fluid img-thumbnail">
				</td>
                <td>
                    <a class="btn btn-primary" href="./edit.html?id=${pizza.id}">
                        <i class="fa-solid fa-pen-to-square"> </i>
                    </a>
                </td>     
                <td>
                    <a class="btn btn-primary" onclick="deleteBook(${pizza.id})">
                        <i class="fa-solid fa-trash-can"> </i>
                    </a>
                </td>      
							<th scope="col">TO DO</th>   
            </tr>
            `
		});
	}).catch((res) => {
		//codice da eseguire se la richiesta non è andata a buon fine
		console.error('errore nella richiesta', res);
		alert('Errore durante la richiesta!');
	})
}


//function deleteBook(bookId) {
//    const risposta=confirm('Sei sicuro?');
//
//    if (risposta) {
//        axios.delete('http://localhost:8080/books/'+bookId).then((res) => {
//            //ok => ricarico l'elenco dei libri
//            bookList();
//        }).catch((res) => {
//            console.error('errore nella richiesta', res);
//            alert('Errore durante la richiesta!');
//        });
//    }
//
//}