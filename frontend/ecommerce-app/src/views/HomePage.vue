<template>
  <div class="home">
    <div>
      <CategoryPool @selected-category-event="selectCategoryToShow" @deselect-category-event="deSelectCategoryToshow"/>
    </div>
    <div class="product-menu">
      <div class="sub-categories">
        <h3 class="margin-top">SubCategories</h3>
        <SubCategories @sub-category-selected="selectSubCategory" :SubCategories="this.currentSubCategories"/>
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
import SubCategories from '@/components/SubCategories.vue';
import CategoryUtils from '@/utils/CategoryUtils';

export default {
  name: 'HomePage',

  data() {
    return {
      selectedCategory: null,
      currentSubCategories: [],
      selectedSubCategory: null,
    }
  },

  components: {
    ProductPool,
    CategoryPool,
    SubCategories
  },

  methods: {
    async selectCategoryToShow(id) {
      this.selectedCategory = id
      var response = await CategoryUtils.getSubCategories(id)
      this.currentSubCategories = response.data
    },

    deSelectCategoryToshow() {
      this.selectedCategory = null
      this.currentSubCategories = null
    },

    selectSubCategory(id) {
      if(this.selectedSubCategory == id) {
        this.selectedSubCategory = null
      } else {
        this.selectedSubCategory = id
      }
    }
  },

}
</script>

<style>
.product-menu {
  display: flex;
  flex-direction: row;
}

.sub-categories {
  max-width: 200px;
  min-width: 200px;
  margin-top: 100px;
  margin-left: 40px;
  border-radius: 14px;
    box-shadow:  7px 7px 14px #ebebeb,
             -7px -7px 14px #ffffff;
}
</style>
