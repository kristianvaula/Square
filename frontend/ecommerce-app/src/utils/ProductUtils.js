import axios from 'axios';

const baseurl = "http://localhost:8081";

export default {
  createProduct(formData) {
    return axios.post(baseurl + "/product/new", formData); 
  }, 
  getProducts() {
    return axios.get(baseurl + "/product/all"); 
  }
}
