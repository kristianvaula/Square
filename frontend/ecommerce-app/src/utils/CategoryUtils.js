import axios from 'axios';
import { useTokenStore } from '@/store/token';

const baseurl = "http://localhost:8081";

const defaultConfig = {
    headers: {
        "Content-type" : "application/json"
    }
}
  
const getUserConfig = () => {
let tokenStore = useTokenStore(); 

return {
    headers: {
    "Content-type" : "application/json",
    "Authorization" : "Bearer " + tokenStore.jwtToken,
    }
}
}

export default {
    getCategories() {
        return axios.get(baseurl + "/unauthorized/category", defaultConfig)
    },
    createCategory(category) {
        return axios.post(baseurl + "/unauthorized/category/new",category, getUserConfig)
    },
    removeCategory(id) {
        return axios.delete(`${baseurl}/unauthorized/category/delete/${id}`, getUserConfig)
    }, 
    getAllSubCategories() {
        return axios.get(baseurl + "/unauthorized/sub-category", defaultConfig)
    },
    getSubCategories(id) {
        return axios.get(`${baseurl}/unauthorized/sub-category/${id}`, defaultConfig)
    },
    createSubCategory(category) {
        return axios.post(baseurl + "/unauthorized/sub-category/new", category, getUserConfig)
    },
    removeSubCategory(id) {
        return axios.delete(`${baseurl}/unauthorized/sub-category/delete/${id}`, getUserConfig)
    }
}
