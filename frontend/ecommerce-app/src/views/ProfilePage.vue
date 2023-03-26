<template>
    <div class="profile-page">
        <div>
            <img src="@/assets/images/profileicon.png" alt="Profile Picture">
            <div class="details-card">
                <ProfileDetails />
            </div>
        </div>
        
        <div>
            <router-link to="/my-favorites">
                <Button class="my-button">My Favorites</Button>
            </router-link>
            <router-link to="/my-listings">
                <Button class="my-button">My Listings</Button>
            </router-link>
            <router-link to="/my-purchases">
                <Button class="my-button">My Purchases</Button>
            </router-link>
        </div>
        <div>
            <router-link to="/">
               <Button class="sign-out-button">Sign Out</Button> 
            </router-link>
        </div>
  </div>
  </template>
  
  <script>
  import ProfileDetails from '@/components/ProfileDetails.vue'
  import '../assets/style/ProfilePage.css';
  
  export default {
    name: "ProfilePage",
    components: {ProfileDetails},
    data () {
      return {
        eMail: '',
        password: '',
      }
    },

  }
  </script>
  