<template>
    <div class="under-categories" >
        <ul id="subcategory-list">
            <li v-for="instance in SubCategories" 
            :key="instance.id" 
            :class="{ 'pressed': instance.subCategoryId === selectedCategoryId }" 
            @click="selectSubCategory(instance.subCategoryId)"
            > {{ instance.description }}</li>
        </ul>
    </div>
</template>

<script>
import CategoryUtils from '@/utils/CategoryUtils';
import {store} from '@/store';
export default {

    data() {
        return {
            store,
            selectedCategoryId: null,
            allSubCategories: null,
            subCategoriesShowing: null,
            CategoryUtils
        }
    },

    props: {
      SubCategories: {
        type: Object,
        required: true 
      }
    },

    
    methods: {
      selectSubCategory(id) { 
        this.$emit("subCategorySelected", id) 
        if(this.selectedCategoryId == id) {
          this.selectedCategoryId = null
        } else {
          this.selectedCategoryId = id
        }  
        },   
      }, 
    
}

</script>

<style>
#subcategory-list {
  list-style-type: none;
  margin: 0;
  padding: 0;
  list-style: none;
}
#subcategory-list li {
  margin: 10px 0;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  cursor: pointer;
  display: inline-block; /* change the display property to inline-block */
  margin-right: 10px;
}
#category-list li:hover {
  background-color: #ddd;
  cursor: pointer;
}
/* Styles for the delete button */
.delete-button {
  background-color: #702632;
  color: white;
  border: none;
  border-radius: 4px;
  padding: 10px;
  margin-top: 10px;
  cursor: pointer;
}
.delete-button:hover {
  background-color: #6b232d;
}

.pressed {
    background-color: #6b232d;
  color: white;
}

</style>