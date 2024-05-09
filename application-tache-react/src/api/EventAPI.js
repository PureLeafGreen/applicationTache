import {getAuthTokens, request} from "./axiosHelper";
const baseURL = "/evenement";

export function createEvent(Event) {
    return request('POST', baseURL + "/add", Event, true)
}

export function createEventWithGroup(Event, group) {
    return request('POST', baseURL + `/addWithGroup?groupid=${group}`, Event, true)
}
export function getEvents() {
    return request('GET', baseURL + "/getAll", null, true)
}

export function getEventById(eventId) {
    return request('GET', baseURL + `/getById?id=${eventId}`, null, true)

}

export function getEventsByUser(userId) {
    return request('GET', baseURL + `/getAllByUser?userid=${userId}`, null, true)
}

export function getEventsByGroup(groupId) {
    return request('GET', baseURL + `/getAllByGroup?groupid=${groupId}`, null, true)
}

export function getAllEventsByDate(dateStr) {
    return request('GET', baseURL + "/getByDate?date="+dateStr, null, true)
}

export function deleteEvent(eventId) {
    return request('DELETE', baseURL + "/delete?id="+eventId, null, true)
}

export function updateEvent(event) {
    return request('PUT', baseURL + "/update", event, true)
}