<template>
    <div class="category-card" @click="handleSelect" :class="{'category-selected': isSelected}">
      <div class="category-info">
        <div class="category-title">
          <h3>{{ this.CategoryInfo.description }}</h3>
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
        isSelected: false
      };
    },

    methods: {
      handleSelect() {
        if(!this.isSelected) {
          this.isSelected = true
          this.$emit("selectedCardEvent", this.CategoryInfo)
        } else {
          this.isSelected = false
          this.$emit("deselectedCardEvent", this.CategoryInfo)
        }

      }
    }
  };
  </script>
  
  
  