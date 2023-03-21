<template>
  <div class="formContainer">
    <form class="form" @submit.prevent="onSubmit">
      <h2>Login</h2>

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
      <input class="button" type="button" value="Create account" @click="registerProfile">

      <p class="errorMessage"></p>
    </form>
  </div>
</template>

<script>
import router from "@/router";
import '../assets/style/BaseInput.css';

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
