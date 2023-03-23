<template>
  <div class="home">
    <div>
      <CategoryPool @selected-category-event="selectCategoryToShow" @deselect-category-event="deSelectCategoryToshow"/>
    </div>
    <div class="product-menu">
      <div>
        <h3 class="margin-top">SubCategories</h3>
        <SubCategoryListVue :categoryId="this.currentCategory"/>
      </div>
      <div>
        <ProductPool/>
      </div>
    </div>
    
  </div>
</template>

<script>
import ProductPool from '@/components/ProductPool.vue';
import CategoryPool from '@/components/CategoryPool.vue';
import SubCategoryListVue from '@/components/SubCategoryList.vue';

export default {
  name: 'HomePage',

  data() {
    return {
      currentCategory: null
    }
  },

  components: {
    ProductPool,
    CategoryPool,
    SubCategoryListVue
  },

  methods: {
    selectCategoryToShow(id) {
      this.currentCategory = id
    },

    deSelectCategoryToshow() {
      this.currentCategory = 0
    }
  },

}
</script>

<style>
.product-menu {
  display: flex;
  flex-direction: row;
  margin-top: 20px;
  padding: 20px;
  
}




</style>
