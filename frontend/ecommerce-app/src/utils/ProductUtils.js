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
  return {
    headers: {
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
    let config = getUserConfig()
    return axios.post(baseurl + "/user/product/new", formData, config); 
  }, 
  favouriteProduct(productId, username){
    const data = new FormData; 
    data.append('productId', productId) 
    data.append('username', username)
    const config = getUserConfig()
    return axios.post(baseurl + "/user/product/favourite",data, config )
  },
  unfavourProduct(productId, username){
    const config = getUserConfig()
    return axios.delete(baseurl + `/user/product/remove/favourite/${productId}/${username}`, config )
  },
  getProducts() {
    return axios.get(baseurl + "/unauthorized/product/all", defaultConfig)
    .then(response => {
      if (response.status === 200) {
        return getProductsWithImages(response);
      } else {
        return undefined; 
      }
    })
    .catch(error => {
      console.error(error);
    });
  },
  getFavourites(id) {
    return axios.get(baseurl + `/user/product/favourite/all/${id}`, defaultConfig)
    .then(response => {
      if (response.status === 200) {
        return getProductsWithImages(response);
      } else {
        return undefined; 
      }
    })
    .catch(error => {
      console.error(error);
    });
  },
  getFavouriteIds(id){
    return axios.get(baseurl + `/user/product/favourite/ids/${id}`, defaultConfig); 
  },
  getProductById(id) {
    return axios.get(baseurl + `/unauthorized/product/${id}`, defaultConfig)
    .then(response => {
      if (response.status === 200) {
        return getProductsWithImages(response);
      } else {
        return undefined; 
      }
    })
    .catch(error => {
      console.error(error);
    });
  },
  getProductByCategory(category) {
    return axios.get(baseurl + `/unauthorized/product/category/${category}`, defaultConfig)
    .then(response => {
      if (response.status === 200) {
        return getProductsWithImages(response);
      } else {
        return undefined; 
      }
    })
    .catch(error => {
      console.error(error);
    });
  },
  getProductBySubcategory(subcategory) {
    return axios.get(baseurl + `/unauthorized/product/subcategory/${subcategory}`, defaultConfig)
    .then(response => {
      if (response.status === 200) {
        return getProductsWithImages(response);
      } else {
        return undefined; 
      }
    })
    .catch(error => {
      console.error(error);
    });
  },
  getProductsBySeller(username) {
    return axios.get(baseurl + `/unauthorized/product/user/${username}`, defaultConfig)
    .then(response => {
      if (response.status === 200) {
        return getProductsWithImages(response);
      } else {
        return undefined; 
      }
    })
    .catch(error => {
      console.error(error);
    });
  },
}
