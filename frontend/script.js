// frontend/script.js
document.addEventListener('DOMContentLoaded', () => {
    // Gestione form di registrazione
    const registerForm = document.getElementById('registerForm');
    if(registerForm) {
        registerForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            
            const username = document.getElementById('regUsername').value;
            const email = document.getElementById('regEmail').value;
            const password = document.getElementById('regPassword').value;
            const confirmPassword = document.getElementById('regConfirmPassword').value;

            // Validazione lato client
            if(password !== confirmPassword) {
                showError('Le password non coincidono');
                return;
            }

            try {
                const response = await fetch('/register', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ username, email, password })
                });

                const data = await response.json();
                
                if(response.ok) {
                    window.location.href = '/login.html';
                } else {
                    showError(data.message || 'Errore durante la registrazione');
                }
            } catch (error) {
                showError('Errore di connessione');
            }
        });
    }

    // Gestione form di login
    const loginForm = document.getElementById('loginForm');
    if(loginForm) {
        loginForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            
            const username = document.getElementById('loginUsername').value;
            const password = document.getElementById('loginPassword').value;

            try {
                const response = await fetch('/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ username, password })
                });

                const data = await response.json();
                
                if(response.ok) {
                    // Salva il token JWT (da implementare nel backend)
                    localStorage.setItem('token', data.token);
                    window.location.href = '/dashboard.html';
                } else {
                    showError(data.message || 'Credenziali non valide');
                }
            } catch (error) {
                showError('Errore di connessione');
            }
        });
    }
});

function showError(message) {
    // Crea o aggiorna il messaggio di errore
    let errorDiv = document.querySelector('.error-message');
    if(!errorDiv) {
        errorDiv = document.createElement('div');
        errorDiv.className = 'error-message';
        document.querySelector('form').appendChild(errorDiv);
    }
    errorDiv.textContent = message;
}