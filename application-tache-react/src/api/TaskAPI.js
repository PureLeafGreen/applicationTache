import {getAuthTokens, request} from "./axiosHelper";
const baseURL = "/tasks";

export function createTask(task) {
    return request('POST', baseURL + "/add", task, true)
}

export function getTasksByUser(userId) {
    return request('GET', baseURL + `/getAllByUser?userId=${userId}`, null, true)
}
