// capture the welcome element and modify it so that it says welcome + username
let welcome = document.getElementById('welcome');
	
//capture the userString by accessing the session...
let userString = sessionStorage.getItem('currentUser');

// IF the user is null ... redirect them to the index.html page ("http://"localhost:8080/project-1/")
if (userString === null) {
	window.location = "http://localhost:8080/project-1/";
} else {
	let currentUser = JSON.parse(userString);
	
	console.log(currentUser);
	
	if (currentUser != null) {
		welcome.innerHTML = "Welcome " + currentUser.firstName + " " + currentUser.lastName;
	}
}

function logout() {
	let xhr = new XMLHttpRequest()
	
	xhr.open("POST","http://localhost:8080/project-1/logout");
	xhr.send();
	
	sessionStorage.removeItem('currentUser');
	sessionStorage.removeItem('pending');
	sessionStorage.removeItem('resolved');
	window.location = "http://localhost:8080/project-1/"
}

function sendRequest() {
	console.log("send request");
	let cost = document.getElementById("Amount").value;
	let kind = document.getElementById("Type").value;
	let describe = document.getElementById("Description").value;
	
	let user = sessionStorage.getItem('currentUser');
	let userId = (JSON.parse(userString)).id;

	let requestSend = {
		amount:parseInt(cost),
		time_submitted:new Date(),
		description:describe,
		author_id:userId,
		status_id:1,
		type_id:parseInt(kind)
	};
	
	let xhr = new XMLHttpRequest();

	xhr.open("POST", "http://localhost:8080/project-1/reimbursement");

	// 4. xhr.send();
	xhr.send(JSON.stringify(requestSend));
}

function limitText(limitField, limitCount, limitNum) {
	if (limitField.value.length > limitNum) {
		limitField.value = limitField.value.substring(0, limitNum);
	} else {
		limitCount.value = limitNum - limitField.value.length;
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
		for(i = 0; i < pendingRequest.length; i++)
		{
			let row = table.insertRow(i + 1);
			row.insertCell(0).innerHTML = pendingRequest[i].id;
			row.insertCell(1).innerHTML = employees[i].amount;
			row.insertCell(2).innerHTML = employees[i].date_submitted;
			row.insertCell(3).innerHTML = employees[i].description;
			row.insertCell(4).innerHTML = employees[i].type_id;
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
			row.insertCell(2).innerHTML = resolvedRequest[i].date_submitted;
			row.insertCell(3).innerHTML = resolvedRequest[i].date_resolved
			row.insertCell(4).innerHTML = resolvedRequest[i].description;
			row.insertCell(5).innerHTML = resolvedRequest[i].type_id;
			row.insertCell(6).innerHTML = resolvedRequest[i].status_id;
		}
	} else {
		let childDiv = document.getElementById(`warningResolvedText`);
		childDiv.textContent = "There is no resolved request";
	}
}

function getAccountInfo() {
	console.log("Account info requested");
	let user = sessionStorage.getItem('currentUser');
	let account = JSON.parse(user);
	
	document.getElementById('uName').textContent += account.username
	document.getElementById('pWord').textContent += account.password
	document.getElementById('first').textContent += account.firstName
	document.getElementById('last').textContent += account.lastName
	document.getElementById('email').textContent += account.email
	
	document.getElementById('accountInfoRequest').disabled = true;
}