<template>
    <h3 class="title"> {{ title }}</h3>
    <div class="pool">
        <ProductCard class="item" v-for="instance in this.products" :key="instance.id" :ProductInfo="instance.product" :firstImage="instance.imageList"></ProductCard>
    </div>

</template>

<script>
import ProductUtils from '@/utils/ProductUtils';
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
                products: [],
            }
        },

        mounted () {
            let vm = this
            ProductUtils.getProducts()
                .then((response) => {
                if(response.data) {
                    console.log(response.data)
                    vm.products = response.data
                   
                }
            })
                .catch((err) => {
                console.log(err)
                })
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