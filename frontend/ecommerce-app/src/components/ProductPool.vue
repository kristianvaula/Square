<template>
    <div class="pool">
        <div class="no-products-message" v-if="products.length === 0 || empty">
            <h3 class="lowkey-h3">Your search provided no results</h3>
        </div>
        <div v-else class="product-pool">
            <ProductCard 
                class="item" 
                v-for="instance in products" 
                :isInFavourites="isFavourited(instance.product)"
                :enableFavouritesProp="enableFavourites(instance.product)" 
                :key="instance.id" 
                :product="instance"
                @favourited-event="favouritedEventHandler(instance.product)"
                @unfavoured-event="unfavouredEventHandler"
            ></ProductCard>
        </div>
    </div>

</template>

<script>
import ProductCard from './ProductCard.vue';
import ProductUtils from '@/utils/ProductUtils';
import ProfileUtils from '@/utils/ProfileUtils';
import { useTokenStore } from '@/store/token';
import '@/assets/style/Pool.css'
import '@/assets/style/style.css'

    export default {
        name: "ProductPool",
        components: {
            ProductCard,
        },
        data(){
            return{
                userId: null,
                tokenStore : useTokenStore(),
                favourites: undefined
            }
        },  
        props: {
            products: {
                type: Array,
                required: true
            },
            empty : {
                type: Boolean, 
                required: false,
                default: false
            },
            passFavouriteEvent: {
                type: Boolean, 
                required: false,
                default: false
            },
        },
        methods: {
            enableFavourites(product){
                try {
                    return product.sellerId !== this.userId;
                } catch (error) {
                    return true;
                }
            }, 
            isFavourited(product){
                if(this.favourites !== undefined && this.favourites.length > 0){
                    return this.favourites.includes(product.productId)
                }
            },
            favouritedEventHandler(product){
                console.log("FavouritedHandler")
                this.favourites.push(product.productId)
                console.log(this.favourites)
                if(this.passFavouriteEvent){
                    this.$emit("favouredEvent")
                }
            },
            unfavouredEventHandler(){
                console.log("unfavouredHandler")
                this.fetchFavourites(); 
                console.log(this.favourites)
                if(this.passFavouriteEvent){
                    this.$emit("unfavouredEvent")
                }
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
        mounted() {
            this.fetchFavourites()
            ProfileUtils.getProfileId(this.tokenStore.loggedInUser).then((response) => this.userId = response.data)
        }
    }
</script>