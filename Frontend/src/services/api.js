import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/api' // Asegúrate que esta ruta coincida con tu backend
});

export default api;