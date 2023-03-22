<template>
  <div class="box" style="height: 200px; overflow-y: auto;">
    <ul>
      <li v-for="item in items" :key="item.id">
        <label>
          <input type="checkbox" :value="item.id" v-model="selectedIds" @change="emitSelectedIds">
          {{ item.description }}
        </label>
      </li>
    </ul>
  </div>
</template>

<script>
import CategoryUtils from '@/utils/CategoryUtils';

export default {
  props: {
    categoryId: {
      type: Object, 
      required: true
    }
  },
  data() {
    return {
      items: null,
      selectedIds: []
    };
  },
  watch : {
    categoryId() {
      let vm = this
      let id = this.categoryId.category
      if(typeof id == 'number') {
        console.log("Success")
        CategoryUtils.getSubCategories(id)
        .then((response) => {
          if(response.data) {
            console.log(response.data)
            vm.items = response.data
          }
        })
        .catch((err) => {
          console.log(err)
        })
      }
    }
  },  
  methods: {
    emitSelectedIds() {
      this.$emit('selectedIdList', this.selectedIds);
    }
  }
};
</script>

<style scoped>
.box {
  border: 1px solid black;
  padding: 10px;
}
</style>
