import {getAuthTokens, request} from "./axiosHelper";
const baseURL = "/evenement";

export function createEvent(Event) {
    return request('POST', baseURL + "/add", Event, true)
}
export function getEvents(Event) {
    return request('GET', baseURL + "/get", Event, true)
}

export function getAllEventsByDate(dateStr) {
    return request('GET', baseURL + "/getByDate?date="+dateStr, null, true)
}