<template>
  <div class="category-form-container">
    <form class="category-form" @submit="submit">
      <h1>Add a new category</h1>
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
import '@/assets/style/CreateListingPage.css'
import '@/assets/style/CategoryForm.css'
import BaseText from './templates/BaseText.vue';
import { useField, useForm } from 'vee-validate'
import CategoryUtils from '@/utils/CategoryUtils.js'

export default {
  components: {
    BaseText
  }, 
  setup () {
    let sendForm = (category) => {
      CategoryUtils.createCategory(category)
          .then((response) => {
            console.log(response)
            alert("Category successfully created")
          })
          .catch((err) => {
            console.log(err)
          })
      this.$emit("newCategoryEvent")
    }

    const textVal = value => {
      const regex = /^[A-Za-z0-9",/\-+.#\s]+$/;
      if(!regex.test(String(value)) || value === undefined || String(value).length == 0){
        return 'Input invalid, only letters, numbers and simple symbols.'; 
      }
      
      return true
    }

    const validationSchema = { desc: textVal }
    
    const { handleSubmit, errors } = useForm({validationSchema})

    const {value: desc} = useField('desc')

    const submit = handleSubmit(values => {
      console.log(values)
      let category = {description: values.desc}
      sendForm(category)
    })

    return {desc, errors, submit
    }
  }, 

}
</script>
