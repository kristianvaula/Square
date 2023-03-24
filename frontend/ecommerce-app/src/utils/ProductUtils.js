import axios from 'axios';

const baseurl = "http://localhost:8081";

export default {
  createProduct(formData) {
    return axios.post(baseurl + "/product/new", formData); 
  }, 
  getProducts() {
    return axios.get(baseurl + "/product/all"); 
  }, 
  getProductById(id) {
    return axios.get(baseurl + `/product/${id}`);
  },
  getProductByCategory(category) {
    return axios.get(baseurl + `/product/${category}`);
  },
  getProductBySubcategory(subcategory) {
    return axios.get(baseurl + `/product/${subcategory}`);
  },
}
