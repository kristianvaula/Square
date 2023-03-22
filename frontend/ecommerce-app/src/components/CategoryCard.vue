<template>
<<<<<<< HEAD
    <div class="category-card" @click="handleSelect" :class="{'category-selected': isSelected}">
      <div class="image-wrapper">
        <img
          src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDHBuwV8He2Ctns0kRe5afnUl8p8KXpytmDvk9V2HX8xjkdyyJhV7wQfbRN9zL8ZU3gxA&usqp=CAU"
          alt="Category Logo"/>
      </div>
=======
    <div class="category-card">
>>>>>>> d6d7440f259fbbb0530e444b8587139b1a2a4c1b
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
          this.$emit("deselectedCardEvent")
        }

      }
    }
  };
  </script>
  
  
  