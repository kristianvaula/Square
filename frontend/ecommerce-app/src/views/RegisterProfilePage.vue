<template>
  <div class="formContainer">
    <form class="form" @submit.prevent="onSubmit">
      <h2>Register profile</h2>
      <fieldset>
        <legend>Type in your firstname</legend>
        <BaseInput
            v-model="firstName"
            label="Firstname:"
            placeholder="Ola"
            type="text"
        />
      </fieldset>

      <fieldset>
        <legend>Type in your lastname</legend>
        <BaseInput
            v-model="lastName"
            label="Lastname:"
            placeholder="Norman"
            type="text"
        />
      </fieldset>

      <fieldset>
        <legend>Type in your e-mail</legend>
        <BaseInput
            v-model="eMail"
            label="E-mail:"
            placeholder="Ola.Nordman@mail.com"
            type="text"
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
            v-model="location.city"
            label="City:"
            placeholder="Oslo"
            type="text"
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
            placeholder="*******"
            type="password"
        />
      </fieldset>
      <input class="button" type="button" value="Register" @click="register">
    </form>
  </div>
</template>

<script>
import httputils from "@/utils/httputils";

export default {
  name: "NewProfilePage",
  data () {
    return {
      firstName: '',
      lastName: '',
      eMail: '',
      location: {
        county: '',
        city: '',
        address: ''
      },
      password: ''
    }
  },
  setup () {
    // todo: load in counties from database
    const counties = httputils.getCounties();
    return { counties };
  },
  methods: {
    register () {
      //todo: push to database and sign in
    }
  }
}
</script>
