<template>
    <div class="category-card">
      <div class="category-info">
        <div class="category-title">
          <h3>{{ this.CategoryInfo.title }}</h3>
        </div>
        <div class="category-size">
            <span>{{ "(" + this.CategoryInfo.categorySize + ")" }}</span>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import '@/assets/style/CategoryCard.css'
  import { store } from '@/store/index.js'
  
  export default {
    name: "CategoryCard",

    props: {
        CategoryInfo: {
            type: Object,
            required: true
        }
    },
  
    data() {
      return {
        store,
      };
    },
  };
  </script>
  
  
  