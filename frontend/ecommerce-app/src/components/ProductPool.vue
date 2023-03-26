<template>
    <div class="pool">
        <div class="no-products-message" v-if="products.length === 0">
            <h3>There are no products in this category yet</h3>
        </div>
        <div v-else class="product-pool">
        <ProductCard class="item" v-for="instance in this.products" :key="instance.id" :ProductInfo="instance.product" :firstImage="instance.imageList"></ProductCard>
        </div>
    </div>

</template>

<script>
import { store } from '@/store';
import ProductCard from './ProductCard.vue';
import '@/assets/style/Pool.css'
    export default {
        name: "ProductPool",
        components: {
            ProductCard,
        },

        data() {
            return {
                store,
            }
        },

        props: {
            products: {
                type: Object,
                required: true
            }
        },



    }


</script>

<style>
    .product-pool {
        display:flex;
        flex-direction:row;
        flex-wrap:wrap;
        padding: 5px;
    }
</style>