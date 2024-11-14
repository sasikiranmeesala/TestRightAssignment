// Function to toggle between register and login forms
function toggleForm(formId) {
    document.getElementById('registerForm').style.display = 'none';
    document.getElementById('loginForm').style.display = 'none';
    document.getElementById(formId).style.display = 'block';
}

// Function to validate the registration form
function validateRegisterForm() {
    const username = document.getElementById('regUsername').value;
    const password = document.getElementById('regPassword').value;
    const email = document.getElementById('regEmail').value;

    if (!username || !password || !email) {
        alert('All fields are required!');
        return false;
    }
    return true;
}

// Function to register a new user
async function register() {
    if (!validateRegisterForm()) {
        return; // Do not proceed if validation fails
    }

    const username = document.getElementById('regUsername').value;
    const password = document.getElementById('regPassword').value;
    const email = document.getElementById('regEmail').value;

    const response = await fetch('/api/users/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password, email }),
    });

    const result = await response.json();
    if (response.ok) {
        document.getElementById('registerMessage').textContent = 'You have registered successfully, please login.';
    } else {
        document.getElementById('registerMessage').textContent = 'Registration failed.';
    }
}

// Function to validate the login form
function validateLoginForm() {
    const username = document.getElementById('loginUsername').value;
    const password = document.getElementById('loginPassword').value;

    if (!username || !password) {
        alert('Both username and password are required!');
        return false;
    }
    return true;
}

// Function to log in an existing user
async function login() {
    if (!validateLoginForm()) {
        return; // Do not proceed if validation fails
    }

    const username = document.getElementById('loginUsername').value;
    const password = document.getElementById('loginPassword').value;

    const response = await fetch('/api/users/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
    });

    const result = await response.json();
    if (response.ok) {
        window.location.href = 'chat.html'; // Redirect to chat page on successful login
    } else {
        alert('Invalid username or password!');
    }
}
