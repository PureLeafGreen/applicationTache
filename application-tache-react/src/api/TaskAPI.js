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

export function deleteTask(taskId) {
    return request('DELETE', baseURL + `/delete?taskId=${taskId}`, null, true)
}

export function modifyTask(task) {
    return request('PUT', baseURL + `/update`, task, true)
}

export function getTaskById(taskId) {
    return request('GET', baseURL + `/getById?id=${taskId}`, null, true)
}