<template>
  <nav class="navbar">
    <div class="logo-search-container">
      <div class="logo">
        <router-link to="/">
          <img src="@/assets/icons/logo.png" class="logo" alt="">
        </router-link>
      </div>
    
      <div class="search-bar-container">
        <input placeholder="Search..." class="search-bar" type="text">
        <button class="search-button" >Go</button>
      </div>
    </div>
    
    <div class="nav-links">

      <li>
        <!--TODO: added user in url-->
        <router-link to="/user/create-listing">
          <img class="icon-medium" src="@/assets/icons/plus.png" alt="">
          <h1 class="title-medium">New Listing</h1>
        </router-link>
      </li>

      <li>
        <router-link to="/my-messages">
          <img class="icon-medium" src="@/assets/icons/message.png" alt="">
          <h1 class="title-medium">Messages</h1>
        </router-link>  
      </li>

      <li v-if="!user">
        <router-link to="/login">
          <img class="icon-medium" src="@/assets/icons/profile.png" alt="">
          <h1 class="button-medium">Log In</h1>
        </router-link>
      </li>

      <li class="dropdown-container" v-if="user">
        <router-link to="/profile-page">
          <img class="icon-medium" src="@/assets/icons/profile.png" alt="">
          <h1>{{ user.firstName }}</h1>
        </router-link>
        <ul class="dropdown">
          <li><router-link to="/my-profile">My Profile</router-link></li>
          <li><router-link to="/my-favorites">My Favorites</router-link></li>
          <li><router-link to="/my-listings">My Listings</router-link></li>
          <li><router-link to="/my-purchases">My Purchases</router-link></li>
          <li><a href="/user/sign-out">Sign Out</a></li>
        </ul>
      </li>
    </div>
  </nav>
</template>

<script>
import { useTokenStore } from '@/store/token.js'
import '@/assets/style/NavComponent.css'
//import httputils from '@/utils/httputils.js'

export default {
  name: 'NavComponent',
  setup() {
    const tokenStore = useTokenStore();
    return { tokenStore };
  },
  mounted () {
    //todo: remove async

    if(this.tokenStore.jwtToken) {
      this.user = this.tokenStore.loggedInUser
      /*
      let response = null;
      try {
        console.log("loggedInUser: " + this.tokenStore.loggedInUser + " token: " + this.tokenStore.jwtToken)
        response = await httputils.getProfile(this.tokenStore.loggedInUser, this.tokenStore.jwtToken);
        this.loggedInUser = response.data;
      } catch (err) {
        if (err.response.status === 403) { //error code 403 is forbidden access. Therefor setting token an loggedIn User to null when this error occurs
          this.tokenStore.jwtToken = null;
          this.tokenStore.loggedInUser = null;
        }
      }
      */
    }
  },
  data() {
    return {  
      user: ''
    }
  }
}

</script>