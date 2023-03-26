<template>
    <h3 class="title"> {{ title }}</h3>
    <div class="pool">
        <ProductCard class="item" v-for="instance in products" :key="instance.id" :product="instance"></ProductCard>
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
                title: "New Products",
                store,
            }
        },
        props: {
            products: {
                type: Array,
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