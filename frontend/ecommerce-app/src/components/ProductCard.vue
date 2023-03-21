<template>
    <div class="product-card">
      <div>
        <img v-if="this.isInFavourites" class="icon-heart" src="@/assets/icons/heartfilled.png" @click="unfavouriteProduct">
        <img v-else class="icon-heart" src="@/assets/icons/heart.png" @click="favouriteProduct">
      </div>  
      <div class="image-wrapper">
        <img src="@/assets/images/tools.jpg" alt=""/>
      </div>
      <div class="product-info">
        <div class="location">
            <span> {{ this.ProductInfo.location }} </span>
        </div>
        <div>
          <h3>{{ this.ProductInfo.title }}</h3>
        </div>
        <div class="product-price">
            <span>Price:</span>
            <span>{{ this.ProductInfo.price + " NOK" }}</span>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import '@/assets/style/ProductCardComponent.css'
  import { store } from '@/store/index.js'
  
  export default {
    name: "ProductCard",
    props: {
        ProductInfo: {
            type: Object,
            required: true
        }
    },
    data() {
      return {
        store,
        isInFavourites: false,
      };
    },

    methods: {
        favouriteProduct() {
            this.isInFavourites = true;
        },

        unfavouriteProduct() {
            this.isInFavourites = false;
        },
    },
  };
  </script>
  
  
  