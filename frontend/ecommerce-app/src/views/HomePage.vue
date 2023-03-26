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
        <ProductPool :empty="empty" :products="products"/>
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
import '@/assets/style/HomePage.css'

export default {
  name: 'HomePage',

  data() {
    return {
      selectedCategory: null,
      currentSubCategories: [],
      selectedSubCategory: null,
      products: [], 
      empty: true
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
      ProductUtils.getProductByCategory(id)
        .then((response) => {
          if(response === undefined){
            this.empty = true
          }
          else {
            this.products = response
            this.empty = false
          }
          
        })
        .catch((err) => {
        console.log(err)
        })
    },

    async deSelectCategoryToshow() {
      this.selectedCategory = null
      this.currentSubCategories = null
      ProductUtils.getProducts()
        .then((response) => {
          if(response === undefined){
            this.empty = true
          }
          else {
            this.products = response
            this.empty = false
          }
          
        })
        .catch((err) => {
        console.log(err)
        })
    },

    async selectSubCategory(id) {
      if(this.selectedSubCategory == id) {
        this.selectedSubCategory = null
        ProductUtils.getProductByCategory(this.selectedCategory)
          .then((response) => {
            if(response === undefined){
              this.empty = true
            }
            else {
              this.products = response
              this.empty = false
            }
            
          })
          .catch((err) => {
          console.log(err)
          })
      } else {
        this.selectedSubCategory = id
        ProductUtils.getProductBySubcategory(this.selectedSubCategory)
          .then((response) => {
            if(response === undefined){
              this.empty = true
            }
            else {
              this.products = response
              this.empty = false
            }
            
          })
          .catch((err) => {
          console.log(err)
          })
      }
    }
  },

  mounted () {
    ProductUtils.getProducts()
      .then((response) => {
        if(response === undefined){
          this.empty = true
        }
        else {
          this.products = response
          this.empty = false
        }
        
      })
      .catch((err) => {
      console.log(err)
      })
}, 

}
</script>
