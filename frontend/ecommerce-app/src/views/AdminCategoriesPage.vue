<template>
    <div class="admin-page">
        <CategoryPool/>
        <SubCategories
        @delete-sub-category="deleteSubCategory" />
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
            store
        }
    },

    components: {
    CategoryPool,
    SubCategories
    },

    methods: {
        deleteSubCategory(id) {
            console.log("subcategory id to be deleted: " + id)
        }
    }
  }
  </script>
  
  <style>
  
  
  </style>
  