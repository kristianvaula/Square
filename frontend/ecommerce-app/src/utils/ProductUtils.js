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
      "Content-type": "application/json",
      "Authorization": "Bearer " + tokenStore.jwtToken
  },
  }
}

const getMimeType = fileName => {
  const extension = fileName.split(".").pop();
  switch (extension) {
    case "jpg":
    case "jpeg":
      return "image/jpeg";
    case "png":
      return "image/png";
    case "gif":
      return "image/gif";
    default:
      return "";
  }
};

const getImageSrc = image => {
  const mimeType = getMimeType(image.name);
  return `data:${mimeType};base64,${image.image}`;
};

const getProductsWithImages = response => response.data.map(productResponse => ({
  product: productResponse.product,
  imageList: productResponse.imageList.map(image => ({
    name: image.name,
    src: getImageSrc(image)
  }))
}));

export default {
  
  createProduct(formData) {
    return axios.post(baseurl + "/unauthorized/product/new", formData, getUserConfig()); 
  }, 
  getProducts() {
    let config = getUserConfig()
    return axios.get(baseurl + "/unauthorized/product/all", config)
    .then(getProductsWithImages)
  },
  getProductById(id) {
    return axios.get(baseurl + `/unauthorized/product/${id}`, defaultConfig)
    .then(getProductsWithImages)
  },
  getProductByCategory(category) {
    return axios.get(baseurl + `/unauthorized/product/category/${category}`, defaultConfig)
    .then(getProductsWithImages)
  },
  getProductBySubcategory(subcategory) {
    return axios.get(baseurl + `/unauthorized/product/subcategory/${subcategory}`, defaultConfig)
    .then(getProductsWithImages)
  },
}
