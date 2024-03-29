import {getAuthTokens, request} from "./axiosHelper";
const baseURL = "/evenement";

export function createEvent(Event) {
    return request('POST', baseURL + "/add", Event, true)
}
export function getEvents(Event) {
    return request('GET', baseURL + "/get", Event, true)
}