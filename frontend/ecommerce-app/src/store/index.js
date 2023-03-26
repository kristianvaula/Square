import { reactive } from 'vue'
import { createStore } from 'vuex'

export const store = reactive({

  CurrentProductId: 1, 
  CurrentCategoryID: null,
  
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

