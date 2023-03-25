<template>
    <div class="admin-page" >
        <div class="main-categories">
            <h1>Administrate Main Categories</h1>
            <div class="category-components">
                <div>
                    <CategoryPool @selected-category-event="selectCategoryToDelete" @deselect-category-event="deSelectCategoryToDelete"/>
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
                        <CategoryPool @selected-category-event="selectCategory"
                        @deselect-category-event="deselectCategory"/>
                    </div>
                    <div>
                        <SubCategoryForm/>
                    </div>
                </div>
                    <SubCategories @sub-category-selected="selectSubCategory" :SubCategories="currentSubCategories" />
                    <button class="delete-button" 
                    type="button" 
                    @click="deleteSubCategory"
                    v-if="this.currentSubCategories != null">Delete Subcategory</button>
                </div>
    </div>
  </template>
  
  <script>
    import CategoryUtils from '@/utils/CategoryUtils';
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
            selectedCategory: null,
            currentSubCategories: [],
            selectedSubCategory: null,
        }
    },

    components: {
    CategoryPool,
    SubCategories,
    CategoryForm,
    SubCategoryForm
    },

    methods: {
        deleteSubCategory() {
            if(this.selectedSubCategory != null) {
                if(confirm(`Do you really want to delete Subcategory with id: ${this.selectedSubCategory}?`)){
                CategoryUtils.removeSubCategory(this.selectedSubCategory)
                }
            }
        },

        selectSubCategory(id) {
            if(this.selectedSubCategory == id) {
                this.selectedSubCategory = null
            } else {
                this.selectedSubCategory = id
            }
        },

        deleteCategory() {
                if(confirm(`Do you really want to delete category with id: ${this.selectedCategory}?`)){
                CategoryUtils.removeCategory(this.selectedCategory)
                }
        },

        async selectCategory(id) { 
            this.selectedCategory = id
            var response = await CategoryUtils.getSubCategories(id)
            this.currentSubCategories = response.data
        },

        async deselectCategory() {
            this.selectedCategory = null
            var response = await CategoryUtils.getAllSubCategories()
            this.currentSubCategories = response.data

        },

        selectCategoryToDelete(id) {
            this.selectedCategory = id
        },

        deSelectCategoryToDelete(id) {
            this.selectedCategory = id
        }
    },

    mounted () {
            let vm = this
            CategoryUtils.getAllSubCategories()
                .then((response) => {
                if(response.data) {
                    console.log(response.data)
                    vm.currentSubCategories = response.data
                }
            })
                .catch((err) => {
                console.log(err)
                })
        },  
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
  