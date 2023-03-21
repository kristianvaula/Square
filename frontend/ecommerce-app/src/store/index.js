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

  CategoryList: [
    {
      title: "Sports & Outdoors",
      categorySize: 38,
    },
    {
      title: "Clothing",
      categorySize: 74,
    },
    {
      title: "Furniture",
      categorySize: 58,
    },
    {
      title: "Electronics",
      categorySize: 25,
    },
    {
      title: "Pets",
      categorySize: 12,
    },
    {
      title: "Home Garden",
      categorySize: 103,
    },
    {
      title: "Tools",
      categorySize: 26,
    },
    {
      title: "Others",
      categorySize: 342,
    }

  ],

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
  }]
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

