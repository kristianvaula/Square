<template>
    <div class="footer-container">
        <router-link to="/">
        <img src="@/assets/icons/logo.png" class="logo" alt="">
        </router-link>
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