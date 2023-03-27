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
                <div class="product-buttons">
                    <h3> Add to favourites</h3>
                        <img v-if="this.isInFavourites" class="favourite-icon" src="@/assets/icons/heartfilled.png" @click="unfavouriteProduct">
                        <img v-else class="favourite-icon" src="@/assets/icons/heart.png" @click="favouriteProduct">
                </div>
                <div v-if="this.sellerId != this.loggedInUserId" class="product-buttons">
                    <h3>Contact seller</h3>
                    <img src="@/assets/icons/message.png" class="favourite-icon" @click="contactSeller">
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
            loggedInUserId: -1,
            sellerId: -1,
            product: Object,
            imageLoaded: false,
            timeCreated: undefined, 
        }
    },
    setup() {
        const tokenStore = useTokenStore();

        return {
            tokenStore
        }
    },



    components: {
        ImgCarouselComponent
    },

    methods: {
        unfavouriteProduct() {
            this.isInFavourites = false
            alert(`removed from favourites!`)

        },

        favouriteProduct() {
            this.isInFavourites = true
            alert(`added to favourites!`)
        },

        async contactSeller() {

            let loggedInUserPromise = await ProfileUtils.getProfileId(this.tokenStore.loggedInUser);
        
            const loggedInUserId = loggedInUserPromise.data;
            const sellerId = this.sellerId;
            const newChat = {
                profile1: loggedInUserId,
                profile2: sellerId
            }

            await ChatUtils.newChat(newChat);
            router.push('/my-messages')
        }


    },

    mounted () {
        const productId = this.$route.params.productId
            ProductUtils.getProductById(productId)
                .then((response) => {
                if(response) {
                    console.log(response)
                    this.product = response[0].product
                    this.images = response[0].imageList
                    this.imageLoaded = true 
                    this.sellerId = response[0].product.sellerId
                    if(response[0].product.timeCreated) {
                        const date = new Date(response[0].product.timeCreated)
                        this.timeCreated = date.toLocaleDateString('en-GB', {day: 'numeric', month: 'long' });
                    }
                }
            })
                .catch((err) => {
                console.log(err)
                })
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