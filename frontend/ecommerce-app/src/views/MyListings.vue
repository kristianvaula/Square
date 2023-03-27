<template>
  <div>
    <h1 class="Headline">My Listings</h1>
  </div>
  <div class="flex-centered">
    <div class="sixty-pool">
      <ProductPool :products="products"/>
    </div>
  </div>
</template>

<script>

import ProductUtils from '@/utils/ProductUtils';
import ProductPool from '@/components/ProductPool.vue';
import { useTokenStore } from '@/store/token';
import router from '@/router';

export default {
name: `MyListings`,
  data() {
    return {
      products: [],
      store : useTokenStore()
    }
  },
  components: {
    ProductPool
  },
  mounted () {
    if(this.store.jwtToken){
      ProductUtils.getProductsBySeller(this.store.loggedInUser)
        .then((response) => {
        if(response ) {
            this.products = response            
        }
        })
        .catch((err) => {
        console.log(err)
        })
    }else {
      router.push("/")
    }
  }
    
}
</script>

<style>
.Headline {
    margin-top: 40px;
}
</style>

