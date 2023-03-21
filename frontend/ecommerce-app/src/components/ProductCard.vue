<template>
    <div class="product-card">
      <div>
        <img v-if="store.productCard.isInFavourites" class="icon-heart" src="@/assets/icons/heartfilled.png" @click="unfavouriteProduct">
        <img v-else class="icon-heart" src="@/assets/icons/heart.png" @click="favouriteProduct">
      </div>  
      <div class="image-wrapper">
        <img
          src="https://images.finncdn.no/dynamic/1600w/2023/3/vertical-0/20/1/295/606/851_667518091.jpg"
          alt="Product Image"/>
      </div>
      <div class="product-info">
        <div class="location">
            <span> {{ this.location }} </span>
        </div>
        <div>
          <h3>{{ this.title }}</h3>
        </div>
        <div class="product-price">
            <span>Price:</span>
            <span>{{ this.price + " NOK" }}</span>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import '@/assets/style/ProductCardComponent.css'
  import { store } from '@/store/index.js'
  
  export default {
    name: "ProductCard",
  
    data() {
      return {
        price: 2999,
        title: "Completely new Iphone 11",
        location: "Bergen",
        store,
      };
    },

    methods: {
        favouriteProduct() {
            this.store.productCard.isInFavourites = true;
        },

        unfavouriteProduct() {
            this.store.productCard.isInFavourites = false;
        }

    }
  };
  </script>
  
  
  