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
export function verifyToken() {
    request('POST', baseURL + "/verify?token="+getAuthTokens(), false)
    .then(r => {
        toast("Votre session est valide");
    }).catch(e => {
        toast.error("Session invalide");
        localStorage.setItem('user', null);
        localStorage.setItem('auth_token', null);
        window.location.href = "/login";
    })
}
