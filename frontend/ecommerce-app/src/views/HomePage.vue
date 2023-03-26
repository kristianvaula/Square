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
        <ProductPool :products="products"/>
      </div>
      
    </div>
    
  </div>
</template>

<script>
import ProductPool from '@/components/ProductPool.vue';
import CategoryPool from '@/components/CategoryPool.vue';
import SubCategories from '@/components/SubCategories.vue';
import CategoryUtils from '@/utils/CategoryUtils';
import ProductUtils from '@/utils/ProductUtils';

export default {
  name: 'HomePage',

  data() {
    return {
      selectedCategory: null,
      currentSubCategories: [],
      selectedSubCategory: null,
      products: []
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
      var data = await ProductUtils.getProductByCategory(id)
      this.products = data.data
    },

    async deSelectCategoryToshow() {
      this.selectedCategory = null
      this.currentSubCategories = null
      var data = await ProductUtils.getProducts()
      this.products = data.data
    },

    async selectSubCategory(id) {
      if(this.selectedSubCategory == id) {
        this.selectedSubCategory = null
        var data = await ProductUtils.getProductByCategory(this.selectedCategory)
        this.products = data.data
      } else {
        this.selectedSubCategory = id
        var response = await ProductUtils.getProductBySubcategory(this.selectedSubCategory)
        this.products = response.data
      }
    }
  },

  mounted () {
    ProductUtils.getProducts()
      .then((response) => {
        console.log(response)
        this.products = response
    })
      .catch((err) => {
      console.log(err)
      })
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
