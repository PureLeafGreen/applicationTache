import axios from "axios";

axios.defaults.baseURL = "http://localhost:8080/api";
axios.defaults.headers.post['Content-Type'] = 'application/json';
axios.defaults.headers.get['Content-Type'] = 'application/json';

export const getAuthTokens = () => {
    return window.localStorage.getItem('auth_token');
}

export const setAuthTokens = (token) => {
    window.localStorage.setItem('auth_token', token);
}

export const request = (method, url, data, useToken) => {
    let headers = {};
    if (getAuthTokens() && useToken) {
        headers['Authorization'] = `Bearer ${getAuthTokens()}`;
    }
    return axios({
        method: method,
        headers: headers,
        url: url,
        data: data
    });
}
