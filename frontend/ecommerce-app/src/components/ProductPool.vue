<template>
    <div class="product-pool">
        <ProductCard v-for="instance in this.store.productList" :key="instance.id" :ProductInfo="instance" ></ProductCard>
    </div>

</template>

<script>
import { store } from '@/store';
import ProductCard from './ProductCard.vue';
    export default {
        name: "ProductPool",
        components: {
            ProductCard,
        },

        data() {
            return {
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