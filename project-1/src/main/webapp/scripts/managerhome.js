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
}
	
function logout() {
let xhr = new XMLHttpRequest()
	
	xhr.open("POST","http://localhost:8080/project-1/logout");
	xhr.send();
		
	sessionStorage.removeItem('currentUser');
	sessionStorage.removeItem('placeholder');
	window.location = "http://localhost:8080/project-1/"
}

function getEmployees() {
	console.log("get employees triggered");

	let wait = sessionStorage.getItem('placeholder');
	if(wait == null) {
		fetch('http://localhost:8080/project-1/employees').then(response => response.json()).then(data => sessionStorage.setItem(`placeholder`, JSON.stringify(data))).then(updateTable);
		console.log("I am storing placeholder");
	}

	console.log("This show table of employees");
}

function updateTable() {
	let wait = sessionStorage.getItem('placeholder');
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