// capture the welcome element and modify it so that it says welcome + username
let welcome = document.getElementById('welcome');
	
//capture the userString by accessing the session...
let userString = sessionStorage.getItem('currentUser');

// set up some logic
// IF the user is null ... redirect them to the index.html page ("http://"localhost:8080/EmployeeDBServlets/")
if (userString === null) {
	window.location = "http://localhost:8080/project-1/";
} else {
	let currentUser = JSON.parse(userString);
		
	console.log(currentUser);
		
	if (currentUser != null) {
		welcome.innerHTML = "Welcome Manager " + currentUser.firstName + " " + currentUser.lastName;	
	}
			fetch('http://localhost:8080/project-1/users').then(response => response.json()).then(data => sessionStorage.setItem(`users`, JSON.stringify(data))).then(showPendingTable);
}
	
function logout() {
let xhr = new XMLHttpRequest()
	
	xhr.open("POST","http://localhost:8080/project-1/logout");
	xhr.send();
		
	sessionStorage.removeItem('currentUser');
	sessionStorage.removeItem('employee');
	sessionStorage.removeItem('user');
	sessionStorage.removeItem('pending');
	sessionStorage.removeItem('resolved');
	window.location = "http://localhost:8080/project-1/"
}

function getEmployees() {
	console.log("get employees triggered");

	let wait = sessionStorage.getItem('employee');
	if(wait == null) {
		fetch('http://localhost:8080/project-1/employees').then(response => response.json()).then(data => sessionStorage.setItem(`employee`, JSON.stringify(data))).then(updateTable);
		console.log("I am storing employee");
	}

	console.log("This show table of employees");
}

function updateTable() {
	let wait = sessionStorage.getItem('employee');
			let table = document.getElementById('EmployeeTable');
		let employees = JSON.parse(wait);
		console.log(employees);
		let i;
		for(i = 0; i < employees.length; i++)
		{
			let row = table.insertRow(i + 1);
			row.insertCell(0).innerHTML = employees[i].username;
			row.insertCell(1).innerHTML = employees[i].firstName;
			row.insertCell(2).innerHTML = employees[i].lastName;
			row.insertCell(3).innerHTML = employees[i].email;
		}
}

function getPendingRequest(){
	console.log("get pending triggered");

	let wait = sessionStorage.getItem('pending');
	if(wait == null) {
		fetch('http://localhost:8080/project-1/pending').then(response => response.json()).then(data => sessionStorage.setItem(`pending`, JSON.stringify(data))).then(showPendingTable);
		console.log("I am storing pending requests");
	}
}

function showPendingTable(){
	console.log("Updating resolved table");
	let wait = sessionStorage.getItem('pending');
	let table = document.getElementById('PendingTable');
	let pendingRequest = JSON.parse(wait);
	console.log(pendingRequest);
	let i;
		
	if(pendingRequest != null && pendingRequest != 0) {
		let leng = pendingRequest.length;
		for(i = 0; i < leng; i++)
		{
			let row = table.insertRow(i + 1);
			row.insertCell(0).innerHTML = pendingRequest[i].id;
			row.insertCell(1).innerHTML = pendingRequest[i].amount;
			row.insertCell(2).innerHTML = pendingRequest[i].time_submitted;
			row.insertCell(3).innerHTML = pendingRequest[i].description;
			row.insertCell(4).innerHTML = pendingRequest[i].author_id;
			row.insertCell(5).innerHTML = pendingRequest[i].type_id;
		}
		
		let employee = sessionStorage.getItem('employee');
		let j;
		for(j = 0; j < employee.length; j++) {
			for(i = 0; i < leng; i++) {
				if (table.rows[i + 1].calls[4].value == employee[j].id) {
					table.rows[i + 1].cells[4].innerHTML = employee[j].username;
				}
			}
		}
	} else {
			let childDiv = document.getElementById(`warningPendingText`);
			childDiv.textContent = "There is no pending request";
	}
}

function getResolvedRequest(){
	console.log("get resolved request triggered");

	let wait = sessionStorage.getItem('resolved');
	if(wait == null) {
		fetch('http://localhost:8080/project-1/resolved').then(response => response.json()).then(data => sessionStorage.setItem(`resolved`, JSON.stringify(data))).then(showResolvedTable);
		console.log("I am storing resolved requests");
	}
}

function showResolvedTable(){
	console.log("Updating resolved table");
	let wait = sessionStorage.getItem('resolved');
	let table = document.getElementById('ResolvedTable');
	let resolvedRequest = JSON.parse(wait);
	console.log(resolvedRequest);
	let i;

	if(resolvedRequest != null && resolvedRequest != 0)
	{
		for(i = 0; i < resolvedRequest.length; i++)
		{
			let row = table.insertRow(i + 1);
			row.insertCell(0).innerHTML = resolvedRequest[i].id;
			row.insertCell(1).innerHTML = resolvedRequest[i].amount;
			row.insertCell(2).innerHTML = resolvedRequest[i].time_submitted;
			row.insertCell(3).innerHTML = resolvedRequest[i].time_resolved
			row.insertCell(4).innerHTML = resolvedRequest[i].description;
			row.insertCell(5).innerHTML = resolvedRequest[i].author_id;
			row.insertCell(6).innerHTML = resolvedRequest[i].resolver_id;
			row.insertCell(7).innerHTML = resolvedRequest[i].type_id;
			row.insertCell(8).innerHTML = resolvedRequest[i].status_id;
		}
		
		let user = sessionStorage.getItem('users');
		let j;
		for(j = 0; j < user.length; j++) {
			for(i = 0; i < leng; i++) {
				if (table.rows[i + 1].calls[5].value == user[j].id) {
					table.rows[i + 1].cells[5].innerHTML = user[j].username;
				}
				
				if (table.rows[i + 1].calls[6].value == user[j].id) {
					table.rows[i + 1].cells[6].innerHTML = user[j].username;
				}
			}
		}
		
	} else {
		let childDiv = document.getElementById(`warningResolvedText`);
		childDiv.textContent = "There is no resolved request";
	}
}
