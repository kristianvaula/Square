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
        <router-link to="/create-listing">
          <img class="icon-medium" src="@/assets/icons/plus.png" alt="">
          <h1 class="title-medium">New Listing</h1>
        </router-link>
      </li>

      <li>
        <router-link to="/messages">
          <img class="icon-medium" src="@/assets/icons/message.png" alt="">
          <h1 class="title-medium">Messages</h1>
        </router-link>  
      </li>

      <li v-if="!store.user.logInStatus">
        <router-link to="/login">
          <img class="icon-medium" src="@/assets/icons/profile.png" alt="">
          <h1 class="button-medium">Log In</h1>
        </router-link>
      </li>

      <li class="dropdown-container" v-if="store.user.logInStatus">
        <img class="icon-medium" src="@/assets/icons/profile.png" alt="">
        <h1>{{ username }}</h1>
        <ul class="dropdown">
          <li><router-link to="/favorites">Favorites</router-link></li>
          <li><router-link to="/profile-page">My Profile</router-link></li>
          <li><router-link to="/my-listings">My Listings</router-link></li>
          <li><a href="/user/sign-out">Sign out</a></li>
        </ul>
      </li>
    </div>
  </nav>
</template>

<script>
import { store } from '@/store/index.js'
import '@/assets/style/NavComponent.css'

export default {
  name: 'NavComponent',
  data() {
    return { 
      store, 
      username : store.user.username
    }
  }
}

</script>

<style lang="scss" scoped>

</style>