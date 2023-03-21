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
          <legend>Type in your email</legend>
          <BaseInput
              v-model="eMail"
              label="E-mail:"
              placeholder="Ola.Nordman@mail.com"
              type="text"
          />
        </fieldset>

        <fieldset>
          <legend>Type in your password:</legend>
          <BaseInput
              v-model="password"
              label="Password:"
              placeholder="*******"
              type="password"
          />
        </fieldset>

        <input class="button" type="submit" value="Login in">
        <input id="registerButton" type="button" value="Create account" @click="registerProfile">

        <p class="errorMessage"></p>
      </form>
    </div>
</div>
</template>

<script>
import router from "@/router";
import '../assets/style/BaseInput.css';
import '../assets/style/FormPage.css';

export default {
  name: "LoginPage",
  data () {
    return {
      eMail: '',
      password: ''
    }
  },
  methods: {
    registerProfile () {
      router.push("/register-profile")
    }
  }
}
</script>
