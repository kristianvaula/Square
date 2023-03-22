<template>
  <div class="category-form-container">
    <form class="category-form" @submit="submit">
      <h1>Add a new sub category</h1>
      <div>
        <div class="base-input">
        <label for="select">Category</label>
        <select name="select" id="select" v-model="category">
          <option v-for="instance in categories" :value="instance.categoryId" :key="instance.id">{{instance.description}}</option>
        </select>
        </div>
      </div>
      <div class="base-input center">
        <BaseText
          id="descInput"
          v-model="desc"
          label="Description"
          ref="descField"
          type="text"
          :error="errors.desc"
        />
      </div>
      <button class="button-small" type="submit">Create</button>
    </form>
  </div>
</template>

<script>
import '@/assets/style/CategoryForm.css'
import '@/assets/style/CreateListingPage.css'
import BaseText from './templates/BaseText.vue';
import { useField, useForm } from 'vee-validate'
import CategoryUtils from '@/utils/CategoryUtils.js'

export default {
  components: {
    BaseText
  }, 
  data () {
    return {
      categories: null
    }
  },
  mounted () {
    let vm = this
    CategoryUtils.getCategories()
      .then((response) => {
        if(response.data) {
          console.log(response.data)
          vm.categories = response.data
        }
      })
      .catch((err) => {
        console.log(err)
      })
  },  
  setup () {
    let sendForm = (category) => {
      CategoryUtils.createSubCategory(category)
          .then((response) => {
            console.log(response)
            alert("Subcategory successfully created")
          })
          .catch((err) => {
            console.log(err)
          }) 
    }

    const textVal = value => {
      const regex = /^[A-Za-z0-9",/\-+.#\s]+$/;
      if(!regex.test(String(value)) || value === undefined || String(value).length == 0){
        return 'Input invalid, only letters, numbers and simple symbols.'; 
      }
      return true
    }

    const selectVal = value => {
      const regex = /^[0-9]+(\.[0-9]+)?$/;
      if(!regex.test(String(value)) || value === undefined || String(value).length == 0){
        return 'Input invalid, only letters, numbers and simple symbols.'; 
      }
      return true
    }

    const validationSchema = { desc: textVal, category: selectVal}
    
    const { handleSubmit, errors } = useForm({validationSchema})

    const {value: desc} = useField('desc')
    const {value: category} = useField('category') 

    const submit = handleSubmit(values => {
      let category = {
        description: values.desc,
        categoryId: values.category
      }
      sendForm(category)
    })

    return {desc, errors, category, submit
    }
  }
}
</script>
