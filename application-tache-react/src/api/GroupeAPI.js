import {getAuthTokens, request} from "./axiosHelper";
const baseURL = "/groupes";

export function createGroupe(Groupe) {
    return request('POST', baseURL + "/add", Groupe, true)
}
