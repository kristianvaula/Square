import { reactive } from 'vue'
import { createStore } from 'vuex'

export const store = reactive({
  user: {
    logInStatus: false, 
    username: undefined

  },
  productCard: {
    isInFavourites: false,

  },

  productList: [
    {
    price: 1000,
    title: "New iphone",
    location: "Trondheim",
    src: "@/assets/images/tools.jpg",
  },
  {
    price: 500,
    title: "Tools barely used",
    location: "Rennesøy",
    src: "@/assets/images/tools.jpg",
  },
  {
    price: 1000,
    title: "New iphone",
    location: "Trondheim",
    src: "@/assets/images/tools.jpg",
  },
  {
    price: 1000,
    title: "New iphone",
    location: "Trondheim",
    src: "@/assets/images/tools.jpg",
  },
  {
    price: 500,
    title: "Tools barely used",
    location: "Rennesøy",
    src: "@/assets/images/tools.jpg",
  },
  {
    price: 500,
    title: "Tools barely used",
    location: "Rennesøy",
    src: "@/assets/images/tools.jpg",
  },
  {
    price: 500,
    title: "Tools barely used",
    location: "Rennesøy",
    src: "@/assets/images/tools.jpg",
  },
  {
    price: 500,
    title: "Tools barely used",
    location: "Rennesøy",
    src: "@/assets/images/tools.jpg",
  },
  {
    price: 500,
    title: "Tools barely used",
    location: "Rennesøy",
    src: "@/assets/images/tools.jpg",
  },
  {
    price: 500,
    title: "Tools barely used",
    location: "Rennesøy",
    src: "@/assets/images/tools.jpg",
  },]
})

export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})

