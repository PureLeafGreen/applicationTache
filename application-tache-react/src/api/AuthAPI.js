import axios from "axios";
import {getAuthTokens, request} from "./axiosHelper";
import {useUserContext} from "../UserContext";
import {toast} from "react-hot-toast";
import {useNavigate} from "react-router-dom";
const baseURL = "/auth";
export  function registerUser(registeringUser) {
    return request('POST', baseURL + "/register", registeringUser, false)
}

export function loginUser(loginInUser) {
    return request('POST', baseURL + "/login", loginInUser, false)
}

export function validateToken() {
    request('POST', baseURL + "/validate", getAuthTokens(), true).then(r => {
        if (r.ok) {
            toast("Vous êtes connecté");
            return r.json();
        } else {
            toast.error("Token invalide");
            throw new Error("Invalid token");
        }
    }).catch(e => {
        localStorage.setItem('user', null);
        localStorage.setItem('auth_token', null);
        window.location.reload();
    })
}
