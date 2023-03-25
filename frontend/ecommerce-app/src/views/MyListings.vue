<template>
    <div>
        <h1 class="Headline">Listings</h1>
    </div>
    <div>
        <ProductPool :products="products"/>
      </div>
</template>

<script>

import ProductUtils from '@/utils/ProductUtils';
import ProductPool from '@/components/ProductPool.vue';
export default {
name: `MyListings`,

    data() {
        return {
            products: []
        }
    },

    components: {
        ProductPool
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
.Headline {
    margin-top: 40px;
    align-items: center;
}
</style>

