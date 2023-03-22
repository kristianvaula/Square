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
      title: "Clothing",
      categorySize: 78,
    },
    {
      title: "Clothing",
      categorySize: 78,
    },
    {
      title: "Clothing",
      categorySize: 78,
    },
    {
      title: "Clothing",
      categorySize: 78,
    },
    {
      title: "Clothing",
      categorySize: 78,
    },
    {
      title: "Clothing",
      categorySize: 78,
    },
    {
      title: "Clothing",
      categorySize: 78,
    },
    {
      title: "Clothing",
      categorySize: 78,
    },

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
  },],

  subCategories: [
    {
      name: "Tools",
      id: 0
    },
    { name: "Sport",
      id: 7
    },
    { name: "Sport",
    id: 2
    },
    { name: "Sport",
    id: 3
    },
  ],
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

