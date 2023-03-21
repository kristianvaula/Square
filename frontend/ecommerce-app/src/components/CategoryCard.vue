<template>
    <div class="category-card">
      <div class="image-wrapper">
        <img
          src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDHBuwV8He2Ctns0kRe5afnUl8p8KXpytmDvk9V2HX8xjkdyyJhV7wQfbRN9zL8ZU3gxA&usqp=CAU"
          alt="Category Logo"/>
      </div>
      <div class="category-info">
        <div class="category-title">
          <h3>{{ this.title }}</h3>
        </div>
        <div class="category-size">
            <span>{{ "(" + this.categorySize + ")" }}</span>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import '@/assets/style/CategoryCard.css'
  import { store } from '@/store/index.js'
  
  export default {
    name: "CategoryCard",
  
    data() {
      return {
        title: "Clothing",
        categorySize: 78,
        store,
      };
    },
  };
  </script>
  
  
  