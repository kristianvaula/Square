import { reactive } from 'vue'
import { createStore } from 'vuex'

export const store = reactive({
  user: {
    logInStatus: false, 
    username: undefined 
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

