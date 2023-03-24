<template>
    <div class="category-card" @click="handleSelect" :class="{'category-selected': this.CategoryInfo.categoryId === this.store.CurrentCategoryID}">
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
        if(!(this.CategoryInfo.categoryId === this.store.CurrentCategoryID)) {
          this.store.CurrentCategoryID = this.CategoryInfo.categoryId
          this.$emit("selectedCardEvent", this.CategoryInfo)
        } else {
          this.store.CurrentCategoryID = null
          this.$emit("deselectedCardEvent", this.CategoryInfo)
        }

      }
    }
  };
  </script>
  
  
  