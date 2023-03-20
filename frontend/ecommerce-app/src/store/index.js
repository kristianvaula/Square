import { createStore } from 'vuex'

/**
 * Mocking a log in state for development
 */
export default createStore({
  state: {
    user: {
      loggedInStatus: false,
      username: ""
    }
  },
  getters: {
    getStatus(){
      return this.user.loggedInStatus;
    },
    getUsername() {
      return this.user.username;
    }
  },
  mutations: {
  },
  actions: {
    logIn(username) {
      this.state.loggedInStatus = true;
      this.state.username = username;
    },
    logOut() {
      this.state.loggedInStatus = false;
      this.state.username = "";
    }
  },
  modules: {
  }
})
