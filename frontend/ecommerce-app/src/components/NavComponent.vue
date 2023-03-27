<template>
  <nav class="navbar">
    <div class="logo-search-container">
      <div class="logo">
        <router-link to="/">
          <img src="@/assets/icons/logo.png" class="logo" alt="">
        </router-link>
      </div>
    
      <div class="search-bar-container">
        <input placeholder="Search..." class="search-bar" type="text" v-model="searchString">
        <button class="search-button" @click="search">Go</button>
      </div>
    </div>
    
    <div class="nav-links">

      <li v-if="user">
        <router-link to="/create-listing">
          <img class="icon-medium" src="@/assets/icons/plus.png" alt="">
          <h1 class="title-medium">New Listing</h1>
        </router-link>    
      </li>

      <li v-if="!user">
        <router-link to="/not-logged-in">
          <img class="icon-medium" src="@/assets/icons/plus.png" alt="">
          <h1 class="title-medium">New Listing</h1>
        </router-link>    
      </li>

      <li v-if="user">
        <router-link to="/my-messages">
          <img class="icon-medium" src="@/assets/icons/message.png" alt="">
          <h1 class="title-medium">Messages</h1>
        </router-link>  
      </li>

      <li v-if="!user">
        <router-link to="/not-logged-in">
          <img class="icon-medium" src="@/assets/icons/message.png" alt="">
          <h1 class="title-medium">Messages</h1>
        </router-link>  
      </li>

      <li v-if="!user">
        <router-link to="/login">
          <img class="icon-medium" src="@/assets/images/profileicon.png" alt="">
          <h1 class="button-medium">Log In</h1>
        </router-link>
      </li>

      <li class="dropdown-container" v-if="user">
        <router-link to="/my-profile">
          <img class="icon-medium" src="@/assets/images/profileicon.png" alt="">
          <h1> My profile</h1>
        </router-link>
        <ul class="dropdown">
          <li><router-link to="/my-profile">My Profile</router-link></li>
          <li><router-link to="/my-favorites">My Favorites</router-link></li>
          <li><router-link to="/my-listings">My Listings</router-link></li>
          <li><router-link to="/my-purchases">My Purchases</router-link></li>
          <li><a @click="signOut">Sign Out</a></li>
        </ul>
      </li>
    </div>
  </nav>
</template>

<script>
import { useTokenStore } from '@/store/token.js'
import router from "@/router";
import '@/assets/style/NavComponent.css'

export default {
  name: 'NavComponent',
  setup() {
    const tokenStore = useTokenStore();
    return { tokenStore, searchString: '' };
  },
  mounted () {  

    if(this.tokenStore.jwtToken) {
      this.user = this.tokenStore.loggedInUser
    }
  },
  data() {
    return {  
      user: ''
    }
  },
  methods: {
    signOut() {
      const tokenStore = useTokenStore();
      tokenStore.jwtToken = null;
      tokenStore.loggedInUser = null;
      router.push("/").then(() => location.reload());
    },
    search() {
      if (this.searchString.length < 3) {
        return;
      }
      const searchString = this.searchString 
      router.push({name: "SearchResults", params: {searchString}}).catch(error => {
        console.error(error);
      });
    }
  }
}

</script>