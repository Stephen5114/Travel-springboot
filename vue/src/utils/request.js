import axios from "axios";
import {ElMessage} from "element-plus"

const request = axios.create({
    baseURL: 'http://localhost:9999',
    timeout: 30000
})

request.interceptors.request.use(config => {
    config.headers['Content-type'] = 'application/json;charset=utf-8';
    let user = JSON.parse(localStorage.getItem('code_user') || '{}')
    config.headers['token'] = user.token
    return config;
}, error => {
    return Promise.reject(error)
});

request.interceptors.response.use(response => {
    let res = response.data;
    if (typeof res === 'string'){
        res = res ? JSON.parse(res) : res;
    }
    return res;
}, error => {
    if (error.response.status === 404){
        ElMessage.error('not find interface');
    }else if (error.response.status === 500){
        ElMessage.error('System error');
    }else {
        console.error(error.message);
    }
    return Promise.reject(error);
})

export default request;