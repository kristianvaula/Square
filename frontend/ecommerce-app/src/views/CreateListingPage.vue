<template>
  <div class="create-listing-body">
    <h1>{{ headerText }}</h1>
    <legend>Product Photos</legend>
    <div>
      <ImgCarousel v-if="displayImage" :images="this.images" />
    </div>

    <div class="base-input">
      <input type="file" ref="fileInput" @change="handleFileUpload">
      <button class="button-small" @click="uploadFile" :disabled="!file">Upload</button>
    </div>

    <form @submit="submit">
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
          id="descInput"
          v-model="desc"
          label="Description"
          type="text"
          :textarea = true
          :error="errors.desc"
        />
      </div>
      <div class="category-used-container">
        <div class="category-container">
          <div class="base-input">
            <label for="select">Category</label>
            <select name="select" id="select" v-model="category">
              <option v-for="instance in categories" :value="instance.categoryId" :key="instance.id">{{instance.description}}</option>
            </select>
          </div>
          <div>
            <label>Subcategories</label>
            <SubcategoryList @selected-id-list="handleSelectedId" :categoryId="category"/>
          </div>
        </div> 
        
        <div class="base-input">
          <BaseCheckbox
            v-model="state"
            label="Used"
          />
        </div>
      </div>
      
      <button class="button" type="submit">Create Listing</button>
    </form>
  </div>
</template>

<script>
import { useField, useForm } from 'vee-validate'
import BaseText from '@/components/templates/BaseText.vue';
import BaseCheckbox from '@/components/templates/BaseCheckbox.vue'
import SubcategoryList from '@/components/SubCategoryList.vue';
import ImgCarousel from '@/components/ImgCarouselComponent.vue'
import CategoryUtils from '@/utils/CategoryUtils.js';
import '@/assets/style/CreateListingPage.css'

export default {
  components: {
    BaseText, BaseCheckbox, ImgCarousel, SubcategoryList
  },
  data() {
    return {
      headerText: 'Create a new listing',
      categories: null,
      subcategories: null,
      image: null,
      displayImage: false, 
      images: []
    }
  },
  methods: {
    handleFileUpload() {
      this.file = this.$refs.fileInput.files[0]; 

      const reader = new FileReader(); 
      reader.onload = () => {
        this.images.push(reader.result); 
        this.displayImage = true; 
      }; 
      reader.readAsDataURL(this.file)

    },
    uploadFile() {
      const formData = new formData(); 
      formData.append('image', this.file); 
    },
    handleSelectedId(subcategories) {
      console.log(subcategories.slice())
      this.subcategories = subcategories.slice(); 
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
      desc: textVal
    }
    
    const { handleSubmit, errors } = useForm({validationSchema})

    const {value: title} = useField('title')
    const {value: price} = useField('price')
    const {value: desc} = useField('desc')
    const {value: state} = useField('state')
    const {value: category} = useField('category')

    const submit = handleSubmit(values => {
      console.log(values)
      let listing = {
        title: values.title,
        price: values.price,
        desc: values.desc,
        state: values.state, 
        category: values.category
      }
      sendForm(listing)
    })

    return {
      title,
      price,
      desc,
      state, 
      category,
      errors, 
      submit
    }
  }
}
</script>