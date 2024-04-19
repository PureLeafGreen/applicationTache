import {getAuthTokens, request} from "./axiosHelper";
const baseURL = "/tasks";

export function createTask(task) {
    return request('POST', baseURL + "/add", task, true)
}

export function getTasksByUser(userId) {
    return request('GET', baseURL + `/getAllByUser?userId=${userId}`, null, true)
}

export function getTasksByGroup(groupId) {
    return request('GET', baseURL + `/getAllByGroup?groupId=${groupId}`, null, true)
}

export function createTaskByGroup(task, group) {
    return request('POST', baseURL + `/addWithGroup?groupId=${group}`, task, true)
}