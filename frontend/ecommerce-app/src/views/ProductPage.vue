<template>
  <div class="product-page">
    <div class="product-pictures">
      <ImgCarouselComponent :loaded="imageLoaded" :images="images"/>
      <h5 v-if="this.timeCreated !== undefined">Last edited: {{ this.timeCreated }}</h5>
    </div>
    <h1>{{ product.title }}</h1>
    <div class="product-information">
      <div class="product-choices">
        <div class="price">
          <h3>Condition</h3>
          <h3 v-if="this.product.used" class="price-view">Used</h3>
          <h3 v-else class="price-view">New</h3>
        </div>
        <div class="price">
          <h3>Price</h3>
          <h3 class="price-view">{{ product.price + " NOK" }}</h3>
        </div>
        <div v-if="!isOwner()" class="product-buttons">
          <h3> Add to favourites</h3>
            <img 
              v-if="this.isFavourited()" 
              class="favourite-icon" 
              src="@/assets/icons/heartfilled.png" 
              @click="unfavouriteProduct"
            >
            <img 
              v-else 
              class="favourite-icon" 
              src="@/assets/icons/heart.png" 
              @click="favouriteProduct"
            >
        </div>
        <div 
          v-if="!isOwner()" 
          class="product-buttons"
        >
            <h3>Contact seller</h3>
            <img src="@/assets/icons/message.png" class="favourite-icon" @click="contactSeller">
        </div>
        <div 
          v-if="isOwner()" 
          class="product-buttons"
        >
            <h3>Mark As Sold</h3>
            <img src="@/assets/icons/hammer.png" class="favourite-icon" @click="markAsSold">
        </div>
      </div>
      <div>
        <h3>Description</h3>
        <h5 class="price-view">{{ this.product.description }}</h5>
      </div>
    </div>
  </div>
</template>

<script>
import { store } from '@/store';
import ImgCarouselComponent from '@/components/ImgCarouselComponent.vue';
import router from '@/router';
import ProductUtils from '@/utils/ProductUtils';
import ChatUtils from '@/utils/ChatUtils.js'
import ProfileUtils from '@/utils/ProfileUtils';  
import { useTokenStore } from "@/store/token.js";

export default {
  name: `ProductPage`,
  data() {
    return {
      store,
      displayImage: false, 
      images: [],
      isInFavourites: false,
      product: Object,
      imageLoaded: false,
      timeCreated: undefined,
      favourites: [], 
      user: undefined
    }
  },
  setup() {
    const tokenStore = useTokenStore();
    return {
        tokenStore,
    }
  },
  components: {
    ImgCarouselComponent
  },
methods: {
  isOwner(){ 
    return this.product.sellerId === this.userId; 
  },
  isFavourited(){
      if(this.favourites !== undefined && this.favourites.length > 0){
          return this.favourites.includes(this.product.productId)
      }
  },
  setFavourited(){
    if(this.favourites !== undefined && this.favourites.length > 0){
        this.isInFavourites = this.favourites.includes(this.product.productId)
    }
    else {this.isInFavourited = false }
  },  
  favouriteProduct() {
    if(this.tokenStore.jwtToken){
      ProductUtils.favouriteProduct(this.product.productId,this.tokenStore.loggedInUser)
        .then(() => {
          this.favourites.push(this.product.productId)
          this.setFavourited()
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
  markAsSold(){
    if(confirm("Are you sure you want to mark as sold?") == true){
      ProductUtils.sellProduct(this.product.productId)
      .then(() => alert("Product Sold!")).catch((error) => {
        alert("Product could not be sold, please try again")
        console.log(error)
        })
      }
  },
  unfavouriteProduct() {
    if(this.tokenStore.jwtToken){
      ProductUtils.unfavourProduct(this.product.productId, this.tokenStore.loggedInUser)
        .then(() => {
          this.fetchFavourites()
          this.setFavourited()
          alert("Product removed from favourites"); 
        })
        .catch((error) => {
          console.log(error); 
          alert("There was an error adding the product to favourites")
        })
    }    
  },
  async contactSeller() {
    const newChat = {
        profile1: this.userId,
        profile2: this.product.sellerId
    }
    await ChatUtils.newChat(newChat);
    router.push('/my-messages')
  },
  fetchFavourites(){
      if(this.tokenStore.jwtToken){
          ProductUtils.getFavouriteIds(this.tokenStore.loggedInUser)
              .then((response) => {
                  if(response.data && response.data !== undefined){
                      this.favourites = response.data
                  }
              })
      }   
    }
},
mounted () {
  const productId = this.$route.params.productId
  this.fetchFavourites()
  ProfileUtils.getProfileId(this.tokenStore.loggedInUser).then((response) => this.userId = response.data)
  ProductUtils.getProductById(productId)
    .then((response) => {
      if(response) {
        this.product = response[0].product
        this.images = response[0].imageList
        this.imageLoaded = true 
        if(response[0].product.timeCreated) {
          const date = new Date(response[0].product.timeCreated)
          this.timeCreated = date.toLocaleDateString('en-GB', {day: 'numeric', month: 'long' });
      }
    }})
    .catch((err) => {
    console.log(err)
    })
    this.setFavourited()
  },  
}


</script>

<style>
.product-page{
    width:60%;
    margin:10px;
    padding-bottom:50px; 
    border:1px solid #e4e9ed;
    box-shadow:  7px 7px 14px #ebebeb,
             -7px -7px 14px #ffffff;
}

.product-pictures {
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    flex-direction: column;
    margin: 30px;
    align-content: center
}

.product-choices-container {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    margin-top: 40px;
}

.favourite-icon {
    
    max-width: 60px;
    max-height: 60px;
}

.price {
    margin: 30px;
    opacity: 0.95;
}

.product-buttons {
    cursor: pointer;
    margin: 30px;
} 

.product-choices {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    margin-left: 20px;
    align-items:flex-start;
    justify-content: center;
    margin-bottom: 50px;
}

.product-information {
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
}

.price-view {
    margin-top: 25px;
}

</style>