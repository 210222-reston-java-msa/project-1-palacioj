function sendLogin() {
	console.log("send login triggered");

	// save some variable here
	let uName = document.getElementById('uName').value;
	// do the same thing with pWord
	let pWord = document.getElementById('pWord').value;

	console.log(`Username: ${uName}`);
	console.log(`Password: ${pWord}`);

	// building an obj literal with the user credentials
	let loginTemplate = {
		username: uName,
		password: pWord
	}

	// begin some AJAX workflow...

	// 1. get the XMLHttpRequest Object i.e. ... let xhr = ...
	let xhr = new XMLHttpRequest();

	// 2. xhr.onreadystatechange
	xhr.onreadystatechange = function() {
		if(this.readyState === 4 && this.status === 200) {
			console.log("success");

			sessionStorage.setItem(`currentUser`, this.responseText);
			
			userString = sessionStorage.getItem('currentUser');
			currentUser = JSON.parse(userString);
			if(currentUser.role_id == 1) {
				window.location = "http://localhost:8080/project-1/home.html?";
			}
			else {
				window.location = "http://localhost:8080/project-1/managerhome.html?";
			}
			console.log(sessionStorage.getItem(`currentUser`));
		}

		if (this.readyState === 4 && this.status === 204) { // 204 means NO CONTEXT FOUND
			console.log("failed to find user");

			let childDiv = document.getElementById(`warningText`);
			childDiv.textContent = "Failed to login! Username or Password is incorrect"
		}
	}

	// 3. xhr.open("POST", "http://localhost:8080/EmployeeDBServlet/url for the loginServer")
	xhr.open("POST", "http://localhost:8080/project-1/login");

	// 4. xhr.send();
	xhr.send(JSON.stringify(loginTemplate));
	console.log("This show information of User");
}