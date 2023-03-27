<template>
  <div class="wrapper">
    <div id="loginFormContainer">
      <form class="form" @submit.prevent="onSubmit">
        <h1>Login</h1>
        <fieldset>
          <legend>Enter your email</legend>
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
          <legend>Enter your password:</legend>
          <BaseInput
              v-model="password"
              label="Password:"
              placeholder="*******"
              type="password"
              :error="passwordError"   
              data-test="password"
          />
        </fieldset>
        <input class="button" type="submit" value="Login in" @click="signIn" data-test="submitBtn">
        <h5>or</h5>
        <input id="registerButton" type="button" value="Create account" @click="registerProfile">
        <p class="errorMessage" data-test="errorMessage">{{ errorMessage }}</p>
      </form>
    </div>
</div>
</template>

<script>
import { useTokenStore } from "@/store/token.js";
import router from "@/router";
import { useField } from 'vee-validate' 
import '../assets/style/BaseInput.css';
import '../assets/style/FormPage.css';
import ProfileUtils from "@/utils/ProfileUtils";
import { emailValidation } from "@/validations/validations";

export default {
  name: "LoginPage",
  data () {
    return {      
      errorMessage: ''
    }
  },
  setup() {
    const tokenStore = useTokenStore();

    const { value: eMail, errorMessage: eMailError } = useField('eMail', (value) => emailValidation(value))

    const { value: password, errorMessage: passwordError } = useField('password', (value) => {
      if (!value) return 'This field is required'

      return true
    })


    return {
      tokenStore,
      eMail, 
      eMailError,
      password,
      passwordError
    }
  },
  methods: {  
    async signIn () {
      if (!this.eMail || !this.password) {
        this.errorMessage = "Please fill in all filds"
        return;
      } 
      
      if (this.tokenStore.loggedInUser !== null) { 
        this.errorMessage = "Another user is already logged in. Please try again later!"
      }

      try {
        const tokenPromise = await ProfileUtils.getToken(this.eMail, this.password);
        this.tokenStore.setTokenAndLoggedInUser(tokenPromise.data, this.eMail);
        if(this.tokenStore.jwtToken) {
          router.push("/").then(() => location.reload());
        }
      } catch(error) {        
        if (error.response.status === 401) {
          this.errorMessage = "Crendtials is invalid"
        } else {
          this.errorMessage = "Login failed!"
        }
      }
    },
    registerProfile () {
      router.push("/create-profile")
    }
  }
}
</script>
