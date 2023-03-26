<template>
    <div class="product-page">
        <h1>{{ product.title }}</h1>
            <div class="product-pictures">
                <ImgCarouselComponent :loaded="imageLoaded" :images="images"/>
                <h5>Last changed</h5>
                <h5>{{ product.timeCreated }}</h5>
            </div>
            <div class="product-information">
                <div class="product-choices">
                    <div class="price">
                        <h3>Price</h3>
                        <h3 class="price-view">{{ product.price + " NOK" }}</h3>
                    </div>
                    <div class="product-buttons">
                        <h3> Add to favourites</h3>
                            <img v-if="this.isInFavourites" class="favourite-icon" src="@/assets/icons/heartfilled.png" @click="unfavouriteProduct">
                            <img v-else class="favourite-icon" src="@/assets/icons/heart.png" @click="favouriteProduct">
                    </div>
                    <div class="product-buttons">
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
export default {
    name: `ProductPage`,

    data() {
        return {
            store,
            displayImage: false, 
            images: [],
            isInFavourites: false,
            product: Object,
            imageLoaded: false 
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

        contactSeller() {
            router.push('/my-messages')
        }


    },

    mounted () {
        const productId = this.$route.params.productId
            ProductUtils.getProductById(productId)
                .then((response) => {
                if(response) {
                    this.product = response[0].product
                    this.images = response[0].imageList
                    this.imageLoaded = true 
                }
            })
                .catch((err) => {
                console.log(err)
                })
        },  
}


</script>

<style>
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
    cursor: pointer;
    max-width: 60px;
    max-height: 60px;
}

.price {
    margin: 30px;
}

.product-buttons {
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