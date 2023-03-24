import { reactive } from 'vue'
import { createStore } from 'vuex'

export const store = reactive({

  CurrentProductId: 1, 

  CurrentCategoryID: null,
  
  ChatList: [
    {
      title: "User 1",
      lastMessage: "Im fine thank you. I would like to purchase the item you are listing for the price o...",
      lastMessageTime: "11.37"
    },
    {
      title: "User 2",
      lastMessage: "Im fine thank y...",
      lastMessageTime: "13.35"
    },
    {
      title: "User 3",
      lastMessage: "Im fine thank y...",
      lastMessageTime: "19.48"
    },
    {
      title: "User 4",
      lastMessage: "Im fine thank y...",
      lastMessageTime: "20.33"
    },
    {
      title: "User 5",
      lastMessage: "Im fine thank y...",
      lastMessageTime: "22.78"
    },
    {
      title: "User 1",
      lastMessage: "Im fine thank y...",
      lastMessageTime: "11.37"
    },
    {
      title: "User 2",
      lastMessage: "Im fine thank y...",
      lastMessageTime: "13.35"
    },
    {
      title: "User 3",
      lastMessage: "Im fine thank y...",
      lastMessageTime: "19.48"
    },
    {
      title: "User 4",
      lastMessage: "Im fine thank y...",
      lastMessageTime: "20.33"
    },
    {
      title: "User 5",
      lastMessage: "Im fine thank y...",
      lastMessageTime: "22.78"
    }
  ],
  
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

