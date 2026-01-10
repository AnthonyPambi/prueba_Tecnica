import React, { useState } from 'react';
import api from '../services/api';

const Login = () => {
    const [credentials, setCredentials] = useState({ username: '', password: '' });

    const handleChange = (e) => {
        setCredentials({ ...credentials, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            // Se envía a baseURL + '/auth/login' -> http://localhost:8080/api/auth/login
            const response = await api.post('/auth/login', credentials);
            console.log("Respuesta:", response.data);
            alert("¡Login exitoso! Bienvenido " + response.data.username);
        } catch (error) {
            console.error(error);
            alert("Error: Usuario o contraseña incorrectos");
        }
    };

    return (
        <div style={{ display: 'flex', justifyContent: 'center', marginTop: '100px' }}>
            <form onSubmit={handleSubmit} style={{ border: '1px solid #ccc', padding: '20px', borderRadius: '8px' }}>
                <h2>Iniciar Sesión</h2>
                <input name="username" placeholder="Usuario" onChange={handleChange} style={{ display: 'block', margin: '10px 0', padding: '8px' }} />
                <input name="password" type="password" placeholder="Contraseña" onChange={handleChange} style={{ display: 'block', margin: '10px 0', padding: '8px' }} />
                <button type="submit" style={{ width: '100%', padding: '10px', backgroundColor: '#007bff', color: 'white', border: 'none', borderRadius: '4px' }}>
                    Entrar
                </button>
            </form>
        </div>
    );
};

export default Login;