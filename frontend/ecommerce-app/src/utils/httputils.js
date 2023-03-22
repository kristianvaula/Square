import axios from 'axios';

const baseurl = "http://localhost:8081";
const config = {
    headers: {
        "Content-type": "application/json"
    },
};

export default {
    getCounties() {
        return axios.get(baseurl + "/counties", config);
    }, 
    getCategories() {
        return axios.get(baseurl + "/category/", config)
    },
    createCategory(category) {
        return axios.post(baseurl + "/category/new", category)
    },
    removeCategory(id) {
        return axios.delete(`${baseurl}/category/delete/${id}`)
    }, 
    getSubCategories() {
        return axios.get(baseurl + "/sub-category/", config)
    },
    createSubCategory(category) {
        return axios.post(baseurl + "/sub-category/new", category)
    },
    removeSubCategory(id) {
        return axios.delete(`${baseurl}/sub-category/delete/${id}`)
    }
}
