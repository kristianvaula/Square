<template>
  <div id="registerFormContainer">
    <form class="form" @submit.prevent="onSubmit">
      <h1>Register profile</h1>
      <fieldset>
        <legend>Type in your firstname</legend>
        <BaseInput
            v-model="firstName"
            label="Firstname:"
            placeholder="Ola"
            type="text"
            :error="firstNameError"
        />
      </fieldset>

      <fieldset>
        <legend>Type in your lastname</legend>
        <BaseInput
            v-model="lastName"
            label="Lastname:"
            placeholder="Norman"
            type="text"   
            :error="lastNameError"   
        />
      </fieldset>

      <fieldset>
        <legend>Type in your e-mail</legend>
        <BaseInput
            v-model="eMail"
            label="E-mail:"
            placeholder="Ola.Nordman@mail.com"
            type="text"
            :error="eMailError"
        />
      </fieldset>

      <fieldset>
        <legend>Which county are you from?</legend>
        <BaseSelect
            v-model="location.county"
            :options="counties"      
            label="County:"
        />
      </fieldset>
    
      <fieldset>
        <legend>Type in your home city</legend>
        <BaseInput
            v-model=" city"
            label="City:"
            placeholder="Oslo"
            type="text"
            :error="cityError"
        />
      </fieldset>

      <fieldset>
        <legend></legend>
        <BaseInput
            v-model="location.address"
            label="Address:"
            placeholder="Olav tryggvasons gate 24"
            type="text"
        />
      </fieldset>

      <fieldset>
        <legend>Type in your password:</legend>
        <BaseInput
            v-model="password"
            label="Password:"
            placeholder="**********"
            type="password"
            :error="passwordError"

        />
      </fieldset>
      <input class="button" type="button" value="Register" @click="register">
      <p class="errorMessage" v-if="errorMessage"> {{ errorMessage }}</p>
    </form>
  </div>
</template>

<script>
import httputils from "@/utils/httputils";
import "../assets/style/RegisterProfilePage.css"
import "../assets/style/BaseInput.css"
import { useField } from 'vee-validate' 
import { useTokenStore } from "@/store/token.js";
import router from "@/router/index"

export default {
  name: "RegsiterProfilePage",
  data () {
    return {
      location: {
        county: '',
        address: ''
      },
      counties: [],
      errorMessage: ''
    }
  },
  async mounted () {
    let countiesPromise = await httputils.getCounties();

    countiesPromise.data.forEach(conty => {
      this.counties.push(conty.countyName)
    });
  },
  setup() {

    const tokenStore = useTokenStore();

    function textValidation(value) {      
      if (!value) return 'This field is required'
      const regex = /^[a-z ,.'-]+$/i
      if (!regex.test((String(value)))) {
        return 'Please enter a valid name'
      }
      return true
    }

    function passwordValidation(value) {
      if(!value) {
        return "This field is required.";
      }
      
      const isWhitespace = /^(?=.*\s)/;
      if (isWhitespace.test(value)) {
        return "Password must not contain whitespaces.";
      }
  
      const isContainsUppercase = /^(?=.*[A-Z])/;
      if (!isContainsUppercase.test(value)) {
        return "Password must have at least one uppercase character.";
      }
  
      const isContainsLowercase = /^(?=.*[a-z])/;
      if (!isContainsLowercase.test(value)) {
        return "Password must have at least one lowercase character.";
      }
  
      const isContainsNumber = /^(?=.*[0-9])/;
      if (!isContainsNumber.test(value)) {
        return "Password must contain at least one digit.";
      }
  
      const isContainsSymbol = /^(?=.*[~`!@#$%^&*()--+={}[\]|\\:;"'<>,.?/_₹])/;
      if (!isContainsSymbol.test(value)) {
        return "Password must contain at least one special character.";
      }
  
      const isValidLength = /^.{8,16}$/;
      if (!isValidLength.test(value)) {
        return "Password must be 8-16 characters long.";
      }

      return true
    }

    const { value: eMail, errorMessage: eMailError } = useField('eMail', (value) => {
      if (!value) return 'This field is required'

      const regex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      if (!regex.test(String(value).toLowerCase())) return 'Please enter a valid email address'

      return true
    })
    
    const { value: firstName, errorMessage: firstNameError } = useField('firstName', (value) => textValidation(value));
    const { value: lastName, errorMessage: lastNameError } = useField('lastName', (value) => textValidation(value));
    const { value: city, errorMessage: cityError } = useField('city', (value) => textValidation(value));
    const { value: password, errorMessage: passwordError} = useField('password', (value) => passwordValidation(value));
    
    return {
      eMail, 
      eMailError,
      firstName, 
      firstNameError,
      lastName,
      lastNameError,
      city,
      cityError,
      password,
      passwordError,
      tokenStore
    }
  },
  methods: {
    async register () {
      let profile = {
        firstName: this.firstName,
        lastName: this.lastName,
        eMail: this.eMail,
        county: this.location.county,
        city: this.city,
        address: this.location.address,
        password: this.password
      };

      if (this.hasAllFiledsInput) {    
        let profilePromise;
        try {
          profilePromise = await httputils.createUser(profile);          
        } catch (error) {          
          if (error.response.status === 409) {
            this.errorMessage = "It already exist a user with e-mail: " + this.eMail;
          }
        }      

        await this.tokenStore.getTokenAndSaveInStore(profilePromise.data);
        if(this.tokenStore.jwtToken){
            router.push("/");
        }
      } else {
        this.errorMessage = "Make sure that all filed is filed in properly"
      }
    }
  },
  computed: {
    hasAllFiledsInput () {
      return this.firstName && this.lastName && this.eMail && this.location.county && this.city && this.location.address && this.password;
    }
  }
}
</script>
