<template>
    <div class="admin-page" >
        <div class="main-categories">
            <h1>Administrate Main Categories</h1>
            <CategoryPool @selected-category-event="selectCategory"/>
            <button class="delete-button"
            type="button" 
            @click="deleteCategory">Delete Category</button>
        </div>
        <div>
            <h1>Administrate Subcategories</h1>
            <CategoryPool @selected-category-event="selectCategory"/>
            <SubCategories @delete-sub-category="deleteSubCategory" />
        </div>
        
        
    </div>
  </template>
  
  <script>
    import CategoryPool from '@/components/CategoryPool.vue';
    import SubCategories from '@/components/SubCategories.vue';
    import store from '@/store';

  
  export default {
    name: 'AdminCategoriesPage',

    data() {
        return {
            store,
            currentCategoryID: null
        }
    },

    components: {
    CategoryPool,
    SubCategories
    },

    methods: {
        deleteSubCategory(id) {
            console.log("subcategory id to be deleted: " + id)
        },

        deleteCategory() {
            console.log("main category to be deleted: " + this.currentCategoryID)

        },

        selectCategory(id) {
            this.currentCategoryID = id
        }
    }
  }
  </script>
  
  <style>
  .main-categories {
    margin-bottom: 30px;
  }
  
  </style>
  