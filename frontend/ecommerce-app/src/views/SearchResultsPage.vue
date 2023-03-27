<template>
  <div>
    <div>
      <button @click="goBack" class="button-small floating-button">Home</button>
    </div>
    <h1 class="Headline">Search Results</h1>
  </div>
  <div class="flex-centered">
    <div class="eighty-pool">
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
  name: `SearchResults`,
  data() {
    return {
      products: [],
      store : useTokenStore(),
      searchString: ''
    }
  },
  components: {
    ProductPool
  },
  watch: {
    '$route.params.searchString': function(newValue) {
      this.searchString = newValue;
      this.fetchResults();
    }
  },
  methods: {
    fetchResults() {
      if (this.searchString !== undefined && this.searchString !== null) {
        ProductUtils.getProductsBySearch(this.searchString)
          .then((response) => {
            if (response) {
              if (response.length > 0) {
                this.products = response;
                return;
              }
            }
            this.products = [];
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        router.push("/");
      }
    },
    goBack() {
      router.push("/");
    }
  },
  
  mounted () {
    this.searchString = this.$route.params.searchString;
    this.fetchResults(); 
  }
}
</script>

<style>
.Headline {
    margin-top: 40px;
}
.floating-button {
  position: relative;
  top: 20px;
  left: 10%;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  z-index: 999;
}
</style>

