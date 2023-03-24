import axios from 'axios';

const baseurl = "http://localhost:8081";
/**const config = {
    headers: {
        "Content-type": "application/json"
    },
};*/

export default {
  createProduct(formData) {
    return axios.post(baseurl + "/product/new", formData); 
  }
}
