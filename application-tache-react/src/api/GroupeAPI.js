import {getAuthTokens, request} from "./axiosHelper";
const baseURL = "/groupes";

export function createGroupe(Groupe) {
    return request('POST', baseURL + "/add", Groupe, true)
}

export function joinGroupe(code, user) {
    return request('POST', baseURL + "/join?code="+code+"&userId="+user, null, true)
}

export function getGroupe(groupeid) {
    return request('GET', baseURL + "/get?id="+groupeid, null, true)
}

export function getGroupes(groupesid) {
    return request('GET', baseURL + "/getGroupes?groupesid="+groupesid, null, true)
}

export function getGroupesWithUser(groupesid) {
    return request('GET', baseURL + "/getGroupesWithUser?groupesid="+groupesid, null, true)
}

export function deleteGroupe(groupeid) {
    return request('DELETE', baseURL + "/delete?id="+groupeid, null, true)
}