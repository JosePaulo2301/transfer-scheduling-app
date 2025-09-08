import axios from 'aixios';

export const api = axios.create({
    baseURL: 'http://localhost:8080/api/v1/'
})