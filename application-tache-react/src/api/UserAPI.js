import {getAuthTokens, request} from "./axiosHelper";
const baseURL = "/utilisateur";

export function getUser(userId) {
    return request('GET', baseURL + "/get?id="+userId, null, true)
}
