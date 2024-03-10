import axios from "axios";
import {request} from "./axiosHelper";
import {useUserContext} from "../UserContext";
const baseURL = "/auth";

export  function registerUser(registeringUser) {
    return request('POST', baseURL + "/register", registeringUser, false)
}

export function loginUser(loginInUser) {
    return request('POST', baseURL + "/login", loginInUser, false)
}
