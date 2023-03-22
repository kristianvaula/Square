import { reactive } from 'vue'
import { createStore } from 'vuex'

export const store = reactive({
  productCard: {
    isInFavourites: false,
  }
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

