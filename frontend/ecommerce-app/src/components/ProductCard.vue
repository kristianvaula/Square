<template>
    <div class="product-card" @click="goToProductPage">
      <div>
        <img 
          v-if="isInFavourites && enableFavouritesProp" 
          class="icon-heart" 
          src="@/assets/icons/heartfilled.png" 
          @click.stop="unfavouriteProduct">
        <img 
          v-else-if="enableFavouritesProp" class="icon-heart" 
          src="@/assets/icons/heart.png" 
          @click.stop="favouriteProduct">
      </div>  
      <div class="image-wrapper">
        <img :src="product.imageList[0].src" alt=""/>
      </div>
      <div class="product-info" >
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
  import ProductUtils from '@/utils/ProductUtils';
  
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
        },
        isInFavourites: {
          type: Boolean, 
          required: false, 
          default: false
        }
    },
    data() {
      return {
        tokenStore : useTokenStore(),
      };
    },
    methods: {
        favouriteProduct() {
          console.log("favourcall")
          if(this.tokenStore.jwtToken){
            console.log(this.product.product)
            ProductUtils.favouriteProduct(this.product.product.productId, this.tokenStore.loggedInUser)
              .then(() => {
                this.$emit("favouritedEvent", this.product)
                alert("Product added to favourites"); 
              })
              .catch((error) => {
                console.log(error); 
                alert("There was an error adding the product to favourites")
              })
          }
          else{
            alert("You have to log in to favourite a product."); 
          }
            
        },
        unfavouriteProduct() {
          console.log("Unfavour call")
          if(this.tokenStore.jwtToken){
            ProductUtils.unfavourProduct(this.product.product.productId, this.tokenStore.loggedInUser)
              .then(() => {
                this.$emit("unfavouredEvent", this.product)
                alert("Product removed from favourites"); 
              })
              .catch((error) => {
                console.log(error); 
                alert("There was an error adding the product to favourites")
              })
          }
          else{
            alert("You have to log in to favourite a product."); 
          }
          
        },
        goToProductPage() {
          const productId = this.product.product.productId
          router.push({name: "ProductPage", params: {productId}})
        }
    }
  };
  </script>
  
  
  