<template>
  <div class="create-listing-body">
    <h1>{{ headerText }}</h1>
    <legend>Product Photos</legend>
    <div>
      <ImgCarousel v-if="displayImage" :images="images" />
    </div>

    <form @submit="submit">
      
      <div class="base-input">
        <input type="file" ref="fileInput" @change="handleFileUpload">
        <button class="button-small" @click="resetUploadedImages" :disabled="!file">Reset</button>
      </div>

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
            <SubcategoryList @selected-id-list="rerouteList" :categoryId="category"/>
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
import ProductUtils from '@/utils/ProductUtils.js';
import '@/assets/style/CreateListingPage.css'
import { reactive } from '@vue/reactivity'
import { useTokenStore } from '@/store/token.js'
import router from '@/router';

export default {
  components: {
    BaseText, BaseCheckbox, ImgCarousel, SubcategoryList
  },
  data() {
    return {
      headerText: 'Create a new listing',
      categories: null,
      image: null,
      images: [],
      category: undefined,
      displayImage: false
    }
  },
  methods: {
    handleImage(image){
      this.uploadedImagesuploadedImages = image.slice()
    },
    handleFileUpload() {
      this.file = this.$refs.fileInput.files[0]
      // For previewing purpose
      const readerBase64 = new FileReader();
      readerBase64.onload = () => { 
        this.images.push(readerBase64.result); 
        this.displayImage = true; 
      };
      readerBase64.readAsDataURL(this.file); 

      // For post-purpose
      const readerByte = new FileReader();
      readerByte.onload = () => {
        const arrayBuffer = readerByte.result;  
        const uint8Array = new Uint8Array(arrayBuffer); 
        const blob = new Blob([uint8Array], {type: this.file.type});
        this.uploadedImages.push(blob);
      };
      readerByte.readAsArrayBuffer(this.file);
    },
    rerouteList(list) {
      let arr = JSON.parse(JSON.stringify(list))
      this.updateList(arr)
    },
    resetUploadedImages() {
      this.uploadedImages.splice(0,this.uploadedImages.length)
      this.images = []; 
      this.displayImage = false 
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
    
    let user = () => {
      const tokenStore = useTokenStore();
      if(tokenStore.jwtToken) {
        return tokenStore.loggedInUser
      }
      return null
    } 
    
    const uploadedImages = reactive([])
  
    const list = reactive([]);
    const updateList = (emittedList) => {
      list.splice(0, list.length, ...emittedList);
    };

    let sendForm = (formData) => {
      console.log(formData)
      ProductUtils.createProduct(formData)
          .then((response) => {
            console.log(response)
            alert("Product successfully created")
            router.push("/")
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

    const submit = handleSubmit(values => {
      //Extra checks 
      if(values.state === undefined) values.state= false
      if(list === undefined) return 
      let arr = JSON.parse(JSON.stringify(list))
      if(arr.length == 0) return 
      if(user() === null) return 

      let product = {
          title: values.title,
          description: values.desc,
          price: values.price,
          used: values.state
      }; 

      let username = user();
      let subcategories = list; 

      const formData = new FormData(); 
      formData.append('product', JSON.stringify(product)); 
      formData.append('username', username); 
      formData.append('subcategories', subcategories)
      for (let i = 0; i < uploadedImages.length; i++){
        formData.append('files', uploadedImages[i])
      }  
      sendForm(formData) 
    })

    return {
      title, 
      price,
      desc,
      state, 
      errors,
      submit,
      uploadedImages,
      updateList
    }
  }
}
</script>