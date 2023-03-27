<template>
    <div class="product-card" @click="goToProductPage">
      <div>
        <img 
          v-if="this.isInFavourites && enableFavouritesProp" 
          class="icon-heart" 
          src="@/assets/icons/heartfilled.png" 
          @click="unfavouriteProduct"
          @click.stop="unfavouriteProduct">
        <img 
          v-else-if="enableFavouritesProp" class="icon-heart" 
          src="@/assets/icons/heart.png" 
          @click="favouriteProduct"
          @click.stop="favouriteProduct">
      </div>  
      <div class="image-wrapper">
        <img :src="product.imageList[0].src" alt=""/>
      </div>
      <div class="product-info" >
        <!--<div class="location">
            <span> {{ this.ProductInfo.location }} </span>
        </div>-->
      
        <div>
          <h3>{{ product.product.title }}</h3>
        </div>
        <div class="product-price">
            <span>Price:</span>
            <span>{{ product.product.price + " NOK" }}</span>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import '@/assets/style/ProductCardComponent.css'
  import router from '@/router';
  import { useTokenStore } from '@/store/token';
  
  export default {
    name: "ProductCard",
    props: {
        product: {
            type: Object,
            required: true
        },
        enableFavouritesProp: {
          type: Boolean, 
          required: false, 
          default: true
        }
    },
    data() {
      return {
        tokenStore : useTokenStore(),
        isInFavourites: false,
      };
    },
    methods: {
        favoriteProduct() {
          this.isInFavourites = true;
          //stored as favorite 
            
        },
        unfavouriteProduct() {
          this.isInFavourites = false;
          
        },
        goToProductPage() {
          const productId = this.product.product.productId
          router.push({name: "ProductPage", params: {productId}})
        }
    }
  };
  </script>
  
  
  