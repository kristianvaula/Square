<template>
    <div class="pool">
        <div class="no-products-message" v-if="products.length === 0 || empty">
            <h3>There are no products in this category yet</h3>
        </div>
        <div v-else class="product-pool">
            <ProductCard class="item" v-for="instance in products" :key="instance.id" :product="instance"></ProductCard>
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
                type: Array,
                required: true
            },
            empty : {
                type: Boolean, 
                required: false,
                default: false
            }
        },
    }


</script>