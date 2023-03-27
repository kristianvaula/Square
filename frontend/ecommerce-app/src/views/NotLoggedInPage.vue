<template>
    <div class="info">
        <div class="container">
        <h3>You need to log in to access this feature:</h3>
        <router-link to="/login">
            <Button class="log-in-button">Log In Now</Button>
        </router-link>
        </div>
    </div>
    
  </template>
  
  <script>
  import '@/assets/style/NotLoggedInPage.css'

  export default {
    name: `NotLoggedIn`,
  }

  </script>
  