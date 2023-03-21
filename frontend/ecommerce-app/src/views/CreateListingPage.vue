<template>
  <div class="create-listing-body">
    <h1>{{ headerText }}</h1>
    <legend>Product Photos</legend>
    <div>
      <ImgCarousel :images="this.images" />
    </div>

    <div class="base-input">
      <input type="file" ref="fileInput" @change="handleFileUpload">
      <button class="button-small" @click="uploadFile" :disabled="!file">Upload</button>
    </div>

    <form class="new-listing-form" @submit="submit">
      <legend>Product Information</legend>
      <div class="base-input center">
        <BaseText
          id="titleInput"
          v-model="title"
          label="Title"
          ref="titleField"
          type="text"
          :error="errors.title"
        />
      </div>
      <div class="base-input center">
        <BaseText
          id="priceInput"
          v-model="price"
          ref="priceField"
          label="Price"
          type="text"
          :error="errors.price"
        />
      </div>
      <div class="base-input center">
        <BaseText
          id="briedDescInput"
          v-model="briefDesc"
          label="Brief Description"
          type="text"
          :error="errors.briefDesc"
        />
      </div>
      <div class="base-input center">
        <BaseText
          id="descInput"
          v-model="desc"
          label="Description"
          type="text"
          :textarea = true
          :error="errors.desc"
        />
      </div>
      <div>
        <div class="base-input">
        <BaseSelect
          v-model="category"
          :options="categories"
          label="County"
          :error="errors.category"
        />
        </div>
      </div>
      
      <div class="base-input">
        <BaseCheckbox
          v-model="state"
          label="Used"
        />
      </div>
      <button class="button" type="submit">Create Listing</button>
    </form>
  </div>
</template>

<script>
import { useField, useForm } from 'vee-validate'
import BaseText from '@/components/templates/BaseText.vue';
import BaseSelect from '@/components/templates/BaseSelect.vue'
import BaseCheckbox from '@/components/templates/BaseCheckbox.vue'
import ImgCarousel from '@/components/image_carousel/ImgCarouselComponent.vue'
import '@/assets/style/CreateListingPage.css'

export default {
  components: {
    BaseText, BaseSelect, BaseCheckbox, ImgCarousel
  },
  data() {
    return {
      headerText: 'Create a new listing',
      categories: [
        "Sports & Outdoors",
        "Clothing", 
        "Furniture", 
        "Electronics", 
        "Pets", 
        "Home & Garden",
        "Tools", 
        "Others"
      ],
      image: null, 
      images: []
    }
  },
  methods: {
    handleFileUpload() {
      this.file = this.$refs.fileInput.files[0]; 

      const reader = new FileReader(); 
      reader.onload = () => {
        this.images.push(reader.result); 
      }; 
      reader.readAsDataURL(this.file)

    },
    uploadFile() {
      const formData = new formData(); 
      formData.append('image', this.file); 
    }
  },  
  setup () {
    let sendForm = (listing) => {
      console.log("MOCK SENDING "+ listing); 
    }

    const textVal = value => {
      const regex = /^[A-Za-z0-9",/\-+.#\s]+$/;
      if(!regex.test(String(value)) || value === undefined || String(value).length == 0){
        return 'Input invalid, only letters, numbers and simple symbols.'; 
      }
      
      return true
    }

    const priceVal = value => {
      const regex = /^\d+(\.\d{1,2})?$/;
      if(!regex.test(String(value)) || value === undefined || String(value).length == 0){
        return 'Input invalid, only letters, numbers and simple symbols.'; 
      }
      return true
    }

    const validationSchema = {
      title: textVal,
      price: priceVal,
      briefDesc: textVal, 
      desc: textVal
    }
    
    const { handleSubmit, errors } = useForm({validationSchema})

    const {value: title} = useField('title')
    const {value: price} = useField('price')
    const {value: briefDesc} = useField('briefDesc')
    const {value: desc} = useField('desc')
    const {value: state} = useField('state')
    const {value: category} = useField('category')

    const submit = handleSubmit(values => {
      console.log(values)
      let listing = {
        title: values.title,
        price: values.price,
        briefDesc: values.briefDesc, 
        desc: values.desc,
        state: values.state, 
        category: values.category
      }
      sendForm(listing)
    })

    return {
      title,
      price,
      briefDesc,
      desc,
      state, 
      category,
      errors, 
      submit
    }
  }
}
</script>