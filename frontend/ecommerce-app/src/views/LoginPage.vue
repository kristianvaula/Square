<template>
  <div class="wrapper">
    <!--
    <div id="imageContainer">
      <img id="logoImg" type="image" src="..\assets\icons\logo.png" alt="Picture of logo to the square">
    </div>
    -->
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
          />
        </fieldset>

        <input class="button" type="submit" value="Login in" @click="signIn">
        <h5>or</h5>
        <input id="registerButton" type="button" value="Create account" @click="registerProfile">

        <p class="errorMessage">{{ errorMessage }}</p>
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
import httputils from "@/utils/httputils";

export default {
  name: "LoginPage",
  data () {
    return {      
      errorMessage: ''
    }
  },
  setup() {
    const tokenStore = useTokenStore();

    const { value: eMail, errorMessage: eMailError } = useField('eMail', (value) => {
      if (!value) return 'This field is required'

      const regex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      if (!regex.test(String(value).toLowerCase())) return 'Please enter a valid email address'

      return true
    })

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
      let tokenPromise;
      if (!this.eMail || !this.password) {
          this.errorMessage = "Please fill in all filds"
      } else if (this.tokenStore.loggedInUser === null) { 
        try {
          tokenPromise = await httputils.getToken(this.eMail, this.password);
          console.log(tokenPromise)
        } catch(error) {        
          if (error.response.status === 401) {
            this.errorMessage = "Crendtials is invalid"
          }
        }
        await this.tokenStore.setTokenAndLoggedInUser(tokenPromise.data, this.eMail);
        if(this.tokenStore.jwtToken){
          router.push("/");
        } else {
          this.errorMessage = "Login failed!"
        }
      } else {
        this.errorMessage = "Another user is already logged in. Please try again later!"
      }
    },
    registerProfile () {
      router.push("/create-profile")
    }
  }
}
</script>
