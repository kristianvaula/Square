<template>
  <div class="home">
    <div>
      <CategoryPool/>
      <ProductPool/>
    </div>
  </div>
</template>

<script>
import ProductPool from '@/components/ProductPool.vue';
import CategoryPool from '@/components/CategoryPool.vue';

export default {
  name: 'HomePage',
  components: {
    ProductPool,
    CategoryPool
    
  }

}
</script>
