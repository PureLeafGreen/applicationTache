import {getAuthTokens, request} from "./axiosHelper";
const baseURL = "/groupes";

export function createGroupe(Groupe) {
    return request('POST', baseURL + "/add", Groupe, true)
}

export function joinGroupe(code, user) {
    return request('POST', baseURL + "/join?code="+code+"&userId="+user, null, true)
}