<template>
    <div class="product-card" @click="goToProductPage">
      <div>
        <img v-if="this.isInFavourites" class="icon-heart" src="@/assets/icons/heartfilled.png" @click="unfavouriteProduct">
        <img v-else class="icon-heart" src="@/assets/icons/heart.png" @click="favouriteProduct">
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
  import { store } from '@/store/index.js'
  
  export default {
    name: "ProductCard",
    props: {
        product: {
            type: Object,
            required: true
        },
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
          alert(`${this.ProductInfo.title} added to favourites!`)
            
        },

        unfavouriteProduct() {
            this.isInFavourites = false;
            alert(`${this.ProductInfo.title} removed from favourites!`)
        },

        goToProductPage() {
          const productId = this.product.product.productId
          router.push({name: "ProductPage", params: {productId}})
        }
    },
    mounted(){
      console.log(this.product.imageList[0])
    }
  };
  </script>
  
  
  