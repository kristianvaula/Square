import axios from 'axios';
import { useTokenStore } from '@/store/token';

const baseurl = "http://localhost:8081";

const defaultConfig = {
    headers: {
        "Content-type" : "application/json"
    }
}
  
function getUserConfig(){
    let tokenStore = useTokenStore();
    console.log(tokenStore.jwtToken) 
    return {
      headers: {
        "Content-type" : "application/json",
        "Authorization": "Bearer " + tokenStore.jwtToken
    },
    }
  }

export default {
    getCategories() {
        return axios.get(baseurl + "/unauthorized/category", defaultConfig)
    },
    createCategory(category) {
        let config = getUserConfig()
        return axios.post(baseurl + "/admin/category/new",category, config)
    },
    removeCategory(id) {
        let config = getUserConfig()
        return axios.delete(`${baseurl}/admin/category/delete/${id}`, config)
    }, 
    getAllSubCategories() {
        return axios.get(baseurl + "/unauthorized/sub-category", defaultConfig)
    },
    getSubCategories(id) {
        return axios.get(`${baseurl}/unauthorized/sub-category/${id}`, defaultConfig)
    },
    createSubCategory(category) {
        let config = getUserConfig()
        return axios.post(baseurl + "/admin/sub-category/new", category, config)
    },
    removeSubCategory(id) {
        let config = getUserConfig()
        return axios.delete(`${baseurl}/admin/sub-category/delete/${id}`, config)
    }
}
