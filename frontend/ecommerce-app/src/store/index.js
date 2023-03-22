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
      categoryId: 1
    },
    {
      title: "Clothing",
      categorySize: 74,
      categoryId: 2
    },
    {
      title: "Furniture",
      categorySize: 58,
      categoryId: 3
    },
    {
      title: "Electronics",
      categorySize: 25,
      categoryId: 4
    },
    {
      title: "Pets",
      categorySize: 12,
      categoryId: 5
    },
    {
      title: "Home Garden",
      categorySize: 103,
      categoryId: 6
    },
    {
      title: "Tools",
      categorySize: 26,
      categoryId: 17
    },
    {
      title: "Others",
      categorySize: 342,
      categoryId: 18
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

