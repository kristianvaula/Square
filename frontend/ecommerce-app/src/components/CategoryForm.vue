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
      <button class="button-small" type="submit">Create Listing</button>
    </form>
  </div>
</template>

<script>
import '@/assets/style/CategoryForm.css'
import BaseText from './templates/BaseText.vue';
import { useField, useForm } from 'vee-validate'
import HttpUtils from '@/utils/httputils.js'

export default {
  components: {
    BaseText
  }, 
  setup () {
    let sendForm = (category) => {
      HttpUtils.createCategory(category)
          .then((response) => {
            console.log(response)
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

    const validationSchema = { desc: textVal }
    
    const { handleSubmit, errors } = useForm({validationSchema})

    const {value: desc} = useField('desc')

    const submit = handleSubmit(values => {
      console.log(values)
      let category = {desc: values.desc}
      sendForm(category)
    })

    return {desc, errors, submit
    }
  }
}
</script>
