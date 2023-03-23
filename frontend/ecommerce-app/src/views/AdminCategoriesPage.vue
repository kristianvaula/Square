<template>
    <div class="admin-page" >
        <div class="main-categories">
            <h1>Administrate Main Categories</h1>
            <div class="category-components">
                <div>
                    <CategoryPool @selected-category-event="selectCategory"/>
                    <button class="delete-button"
                    type="button" 
                    @click="deleteCategory">Delete Category</button>
                </div>
                <div>
                    <CategoryForm/>
                </div>
            </div>
        </div>
            <div class="main-categories">
                <h1>Administrate Subcategories</h1>
                <div class="category-components">
                    <div>
                        <CategoryPool @selected-category-event="selectCategory"/>
                    </div>
                    <div>
                        <SubCategoryForm/>
                    </div>
                    
                </div>
                <SubCategories @delete-sub-category="deleteSubCategory" />
            </div>
    </div>
  </template>
  
  <script>
     import CategoryForm from '@/components/CategoryForm.vue';
    import SubCategoryForm from '@/components/SubCategoryForm.vue';
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
    SubCategories,
    CategoryForm,
    SubCategoryForm
    },

    methods: {
        deleteSubCategory(id) {
            console.log("subcategory id to be deleted: " + id)
        },

        deleteCategory() {
            if(confirm(`Do you really want to delete category with id: ${this.currentCategoryID}?`)){
                console.log("main category to be deleted: " + this.currentCategoryID)
            }

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

  .category-components {
    display: flex;
    flex-direction: row;

  }

  .main-categories {
    margin: 20px;
    margin-bottom: 40px;
  }
  
  </style>
  