<template>
  <div class="footer-container">
    <div class="all-rights-reserved">
      © SQUARE 2023 
    </div>
    <div class="square-logo">
        <router-link to="/">
          <img src="@/assets/icons/logo.png" alt="">
        </router-link>
    </div>
    <div class="contact-us">
      <h3>Contact Us:</h3>
      <p>Phone: (+47 900 90 009)</p>
      <p>Email: support@square.org</p>
    </div>
  </div>
    
  </template>
  
  <script>
  import { store } from '@/store/index.js'
  import '@/assets/style/FooterComponent.css'
  
  export default {
    name: 'FooterComponent',
    data() {
      return { 
        store, 
      }
    }
  }
  
  </script>
  
  <style lang="scss" scoped>
  
  </style>