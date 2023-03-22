<template>
    <div class="under-categories" >
        <ul id="subcategory-list">
            <li v-for="instance in this.store.subCategories" 
            :key="instance.id" 
            :class="{ 'pressed': instance.id === selectedCategoryId }" 
            @click="selectSubCategory(instance.id) "
            > {{ instance.name }}</li>
        </ul>
        <button class="delete-button" 
        type="button" 
        @click="deleteSubCategory">Delete Subcategory</button>
      </div>
</template>

<script>
import {store} from '@/store';
export default {

    data() {
        return {
            store,
            selectedCategoryId: null
        }
    },
    methods: {
        selectSubCategory(id) {  
            if(this.selectedCategoryId == id) {
              this.selectedCategoryId = null;
            } else {
              this.selectedCategoryId = id;
            }
            
            
        },

        deleteSubCategory() {
          if(this.selectedCategoryId != null)
          this.$emit("deleteSubCategory", this.selectedCategoryId)
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