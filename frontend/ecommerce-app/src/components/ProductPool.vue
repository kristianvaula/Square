<template>
<<<<<<< HEAD
    <div class="product-pool">
        <ProductCard v-for="instance in this.store.productList" 
        :key="instance.id" 
        :ProductInfo="instance" 
        ></ProductCard>
=======
    <h3 class="title"> {{ title }}</h3>
    <div class="pool">
        <ProductCard class="item" v-for="instance in this.store.productList" :key="instance.id" :ProductInfo="instance" ></ProductCard>
>>>>>>> d6d7440f259fbbb0530e444b8587139b1a2a4c1b
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
                store
            }
        }

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