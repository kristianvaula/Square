<template>
    <div class="pool">
        <div class="no-products-message" v-if="products.length === 0 || empty">
            <h3 class="lowkey-h3">Your search provided no results</h3>
        </div>
        <div v-else class="product-pool">
            <ProductCard class="item" v-for="instance in products" :enableFavouritesProp="enableFavourites(instance.product)" :key="instance.id" :product="instance"></ProductCard>
        </div>
    </div>

</template>

<script>
import ProductCard from './ProductCard.vue';
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
                tokenStore : useTokenStore() 
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
        },
        methods: {
            enableFavourites(product){
                try {
                    return product.sellerId !== this.userId;
                } catch (error) {
                    return true;
                }
            }
        },
        mounted() {
            if(this.tokenStore.jwtToken){
                ProfileUtils.getProfileId(this.tokenStore.loggedInUser)
                    .then((response) => {
                        this.userId = response.data; 
                    })
            }
            
        }
    }
</script>