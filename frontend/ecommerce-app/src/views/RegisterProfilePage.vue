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
            data-test="firstName"
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
            data-test="lastName"
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
            data-test="eMail"
        />
      </fieldset>

      <fieldset>
        <legend>Which county are you from?</legend>
        <BaseSelect
            v-model="location.county"
            :options="counties"      
            label="County:"
            data-test="county"
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
            data-test="city"
        />
      </fieldset>

      <fieldset>
        <legend></legend>
        <BaseInput
            v-model="location.address"
            label="Address:"
            placeholder="Olav tryggvasons gate 24"
            type="text"
            data-test="address"
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
            data-test="password"
        />
      </fieldset>
      <input class="button" type="button" value="Register" @click="register" data-test="registerBtn">
      <p class="errorMessage" v-if="errorMessage" data-test="errorMessage"> {{ errorMessage }}</p>
    </form>
  </div>
</template>

<script>
import ProfileUtils from "@/utils/ProfileUtils";
import "../assets/style/RegisterProfilePage.css"
import "../assets/style/BaseInput.css"
import { useField } from 'vee-validate' 
import { useTokenStore } from "@/store/token.js";
import router from "@/router/index"
import { emailValidation, textValidation, passwordValidation } from "@/validations/validations";

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
    let countiesPromise = await ProfileUtils.getCounties();

    countiesPromise.data.forEach(conty => {
      this.counties.push(conty.countyName)
    });
  },
  setup() {

    const tokenStore = useTokenStore();

    const { value: firstName, errorMessage: firstNameError } = useField('firstName', (value) => textValidation(value));
    const { value: lastName, errorMessage: lastNameError } = useField('lastName', (value) => textValidation(value));
    const { value: eMail, errorMessage: eMailError } = useField('eMail', (value) => emailValidation(value));
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
          profilePromise = await ProfileUtils.createUser(profile);          
        } catch (error) {          
          if (error.response.status === 409) {
            this.errorMessage = "It already exist a user with e-mail: " + this.eMail;
          }
        }      

        await this.tokenStore.getTokenAndSaveInStore(profilePromise.data);
        if(this.tokenStore.jwtToken){
            router.push("/").then(() => location.reload());
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
